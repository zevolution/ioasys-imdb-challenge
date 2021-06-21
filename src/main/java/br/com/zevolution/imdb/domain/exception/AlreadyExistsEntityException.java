package br.com.zevolution.imdb.domain.exception;

public class AlreadyExistsEntityException extends RuntimeException {
	
	private static final long serialVersionUID = 1205283544561365596L;

	public AlreadyExistsEntityException(String message) {
		super(message);
	}

}
