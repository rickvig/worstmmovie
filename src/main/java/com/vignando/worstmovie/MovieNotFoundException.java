package com.vignando.worstmovie;

public class MovieNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MovieNotFoundException(Long id) {
		super("Could not find movie " + id);
	}


}
