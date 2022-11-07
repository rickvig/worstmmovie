package com.vignando.worstmovie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

interface MovieRepository extends JpaRepository<Movie, Long> {

	List<Movie> findByProducers(String producers);
	
	List<Movie> findByWinner(Boolean winner);

}