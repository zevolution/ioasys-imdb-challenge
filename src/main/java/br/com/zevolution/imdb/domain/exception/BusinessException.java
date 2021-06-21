package br.com.zevolution.imdb.domain.exception;

public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 7802632176041338165L;

	public BusinessException(String message) {
		super(message);
	}

}
