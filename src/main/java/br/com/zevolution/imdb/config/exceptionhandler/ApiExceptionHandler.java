package br.com.zevolution.imdb.config.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.zevolution.imdb.domain.exception.AlreadyExistsEntityException;
import br.com.zevolution.imdb.domain.exception.BusinessException;
import br.com.zevolution.imdb.domain.exception.EntityNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final String MSG_GENERIC_ERROR_FINAL_USER = "Oops! There was an internal error processing the request, please contact the API administrator";
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String messageErrorDetail = "";
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		Optional<FieldError> fieldErrorOptional = fieldErrors.stream().findFirst();
		if (fieldErrorOptional.isPresent()) {
			FieldError fieldError = fieldErrorOptional.get();
			if ("password".equalsIgnoreCase(fieldError.getField())) {
				messageErrorDetail = String.format("The property '%s' %s", fieldError.getField(), 
						fieldError.getDefaultMessage());	
			} else {
				messageErrorDetail = String.format("The property '%s' was given the value '%s', however, %s", 
						fieldError.getField(), fieldError.getRejectedValue(),  fieldError.getDefaultMessage());
			}
		}
		
		ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(LocalDateTime.now(), status.value(), 
				status.getReasonPhrase(), messageErrorDetail, this.getPath(request));

		return handleExceptionInternal(ex, errorDetails, headers, status, request);
	};
	
	@ExceptionHandler(AlreadyExistsEntityException.class)
    public final ResponseEntity<Object> handleEntityAlreadyExists(AlreadyExistsEntityException ex, WebRequest request) {
		ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(LocalDateTime.now(), HttpStatus.CONFLICT.value(), 
				HttpStatus.CONFLICT.getReasonPhrase(), ex.getMessage(), this.getPath(request));
		
        return buildResponseEntity(errorDetails);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
    	ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), 
				HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage(), this.getPath(request));
    	
        return buildResponseEntity(errorDetails);
    }

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<Object> handleBusinessException(EntityNotFoundException ex, WebRequest request) {
    	ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(), 
				HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), ex.getMessage(), this.getPath(request));
    	
        return buildResponseEntity(errorDetails);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
    	ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), 
				HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage(), this.getPath(request));
    	
        return buildResponseEntity(errorDetails);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), 
				HttpStatus.BAD_REQUEST.getReasonPhrase(), error, this.getPath(request));
        
        return buildResponseEntity(errorDetails);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder errorBuilder = new StringBuilder();
        errorBuilder.append(ex.getContentType()).append(" media type is not supported.");
        
        String supportedMediaTypes = ex.getSupportedMediaTypes().stream()
        		.map(MediaType::toString).collect(Collectors.joining(", "));
        if (StringUtils.isNotBlank(supportedMediaTypes)) {
        	errorBuilder.append(" Supported media types are ").append(supportedMediaTypes);
        }
        
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(LocalDateTime.now(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), 
				HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase(), errorBuilder.toString(), this.getPath(request));
        
        return buildResponseEntity(errorDetails);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
        		LocalDateTime.now(),
        		HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getConstraintViolations()
                        .stream()
                        .map(constraintViolation -> constraintViolation.getPropertyPath() + " - " + constraintViolation.getMessage())
                        .collect(Collectors.joining()),
                this.getPath(request));
        
        return buildResponseEntity(errorDetails);
    }

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if (rootCause instanceof AccessDeniedException) {
			return handleAccessDeniedException((AccessDeniedException) rootCause, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
		}
		
		if (rootCause instanceof AuthenticationException) {
			return handleAuthenticationException((AuthenticationException) rootCause, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
		}
		
		ex.printStackTrace();
        
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), 
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), MSG_GENERIC_ERROR_FINAL_USER, this.getPath(request));
        
        return buildResponseEntity(errorDetails);
	}
	
	private ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(LocalDateTime.now(), status.value(), 
				status.getReasonPhrase(), ex.getMessage(), this.getPath(request));
        
		return buildResponseEntity(errorDetails);
	}
	
	private ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(LocalDateTime.now(), status.value(), 
				status.getReasonPhrase(), ex.getMessage(), this.getPath(request));
		
		return buildResponseEntity(errorDetails);
	}
	
    private ResponseEntity<Object> buildResponseEntity(ErrorDetailsResponse error) {
        return new ResponseEntity<>(error, HttpStatus.valueOf(error.getStatus()));
    }
    
	private String getPath(WebRequest request) {
		return ServletWebRequest.class.cast(request).getRequest().getRequestURI();
	}
	
}
