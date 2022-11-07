package com.vignando.worstmovie;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql("classpath:insert-movies.sql")
class MovieRepositoryTest {
	
	@Autowired
	private MovieRepository movieRepository;
	

	@Test
	void findsMoviesByProducar() {
		List<Movie> movies = movieRepository.findByProducers("Producer 1");
		assertThat(movies).isNotNull();
	}
	
	
	@Test
	void findsMoviesByWinners() {
		List<Movie> movies = movieRepository.findByWinner(true);
		assertThat(movies).isNotNull();
		assertThat(movies).hasSize(8);
	}
	
}
