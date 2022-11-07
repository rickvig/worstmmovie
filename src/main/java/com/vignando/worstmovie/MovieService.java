package com.vignando.worstmovie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class MovieService {
	
	private MovieRepository repository;
	
	public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

	public List<ProducerInterval> winMinMax() {
		List<Movie> movies = repository.findByWinner(true);

		Set<String> uniqueProducers = new HashSet<String>();
		for (Movie movie : movies) { uniqueProducers.add(movie.getProducers()); }
		
//		uniqueProducers.forEach(System.out::println);
//		System.out.println("\n--- ");
		
		List<ProducerInterval> producers = new ArrayList<ProducerInterval>();
		
		for (String producer : uniqueProducers) {
			List<Movie> moviesOfProducer = movies.stream()
					.filter(movie -> producer.equals(movie.getProducers()))
					.sorted(Comparator.comparingInt(Movie::getYear))
					.toList();
			
			if (moviesOfProducer.size() == 1)
				continue;
			
			ProducerInterval produtorMaxResponse = new ProducerInterval(producer, "max");
			ProducerInterval produtorMinResponse = new ProducerInterval(producer, "min");
			produtorMinResponse.setInterval(99999);
			
			moviesOfProducer.forEach(movie1 -> {
				Movie lastMovie = moviesOfProducer.get(0);
				
				for (Movie movie2 : moviesOfProducer) {
					if (movie2.getYear() != movie1.getYear()) {
						int diff = movie2.getYear() - movie1.getYear();
						int diffLast = movie2.getYear() - lastMovie.getYear();
						
						if (diffLast < produtorMaxResponse.getInterval())
							continue;
						
						if (diff >= produtorMaxResponse.getInterval() && diffLast >= diff) {
							produtorMaxResponse.setInterval(diffLast);
							produtorMaxResponse.setPreviousWin(lastMovie.getYear());
							produtorMaxResponse.setFollowingWin(movie2.getYear());
						}
						else if (diff >= produtorMaxResponse.getInterval()) {
							produtorMaxResponse.setInterval(diff);  
							produtorMaxResponse.setPreviousWin(movie1.getYear());
							produtorMaxResponse.setFollowingWin(movie2.getYear());
						}
					}
					lastMovie = movie2;
				}
				moviesOfProducer.forEach(movie2 -> {
					
				});
			});
			
			
			moviesOfProducer.forEach(movie1 -> {
				System.out.println(" \t" + movie1);
				
				Movie lastMovie = moviesOfProducer.get(0);
				
				for (Movie movie2 : moviesOfProducer) {
					if (movie2.getYear() != movie1.getYear()) {
						int diff = movie2.getYear() - movie1.getYear();
						int diffLast = movie2.getYear() - lastMovie.getYear();
						
						System.out.println(" \t\t" + movie2.getYear() + " - " + movie1.getYear() + " diff:" + diff);
						System.out.println(" \t\t\t" + movie2.getYear() + " - " + lastMovie.getYear() + " diffLast:" + diffLast);
						
						if (diffLast > produtorMinResponse.getInterval() || diff <= 0 || diffLast <= 0)
							continue;
						
						if (diffLast <= produtorMinResponse.getInterval() && diffLast <= diff) {
							produtorMinResponse.setInterval(diffLast);
							produtorMinResponse.setPreviousWin(lastMovie.getYear());
							produtorMinResponse.setFollowingWin(movie2.getYear());
						}
						else if (diff <= produtorMinResponse.getInterval()) {
							produtorMinResponse.setInterval(diff);  
							produtorMinResponse.setPreviousWin(movie1.getYear());
							produtorMinResponse.setFollowingWin(movie2.getYear());
						}
					}
					lastMovie = movie2;
				}
				moviesOfProducer.forEach(movie2 -> {
					
				});
			});
			
			producers.add(produtorMinResponse);
			producers.add(produtorMaxResponse);
			System.out.println("## " + produtorMaxResponse);
			System.out.println("## " + produtorMinResponse);
			System.out.println("----------------------------------------------- \n");
		}
		return producers;
	}

	public List<Movie> findAll() {
		return repository.findAll();
	}

	public Movie save(Movie newMovie) {
		return repository.save(newMovie);
	}

	public Optional<Movie> findById(Long id) {
		return repository.findById(id);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
