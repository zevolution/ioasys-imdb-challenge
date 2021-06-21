package br.com.zevolution.imdb.domain.exception;

public class EntityNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -5655691770269449970L;

	public EntityNotFoundException(String message) {
		super(message);
	}

}
