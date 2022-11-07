package com.vignando.worstmovie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class MovieServiceTest {

	@Mock
	private MovieRepository repository;

	MovieService movieService;

	@BeforeEach
	void initUseCase() {
		movieService = new MovieService(repository);
	}
	
	@Test
	public void testMovie() {
		List<Movie> movies = new ArrayList<Movie>();

		movies.add(new Movie(2008, "Producer 1"));
		movies.add(new Movie(2009, "Producer 1"));
		movies.add(new Movie(1900, "Producer 1"));
		movies.add(new Movie(1999, "Producer 1"));
		movies.add(new Movie(2005, "Producer 1"));
		movies.add(new Movie(2018, "Producer 2"));
		movies.add(new Movie(2019, "Producer 2"));
		movies.add(new Movie(2000, "Producer 2"));
		movies.add(new Movie(2099, "Producer 2"));
		movies.add(new Movie(2006, "Producer 2"));

		when(repository.findByWinner(any(Boolean.class))).thenReturn(movies);
		
		assertThat(movieService.winMinMax())
			.isNotEmpty()
			.hasSize(4)
			.extracting(ProducerInterval::getInterval)
			.containsExactlyInAnyOrder(1, 99, 99, 1); 
	}

}
