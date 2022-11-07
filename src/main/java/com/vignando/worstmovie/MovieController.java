package com.vignando.worstmovie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

	private final MovieService service;

	MovieController(MovieService service) {
		this.service = service;
	}

	@GetMapping("/movies")
	List<Movie> all() {
		return service.findAll();
	}

	@PostMapping("/movies")
	Movie newMovie(@RequestBody Movie newMovie) {
		return service.save(newMovie);
	}

	@GetMapping("/movies/{id}")
	Movie one(@PathVariable Long id) {
		return service.findById(id).get();
	}

	@PutMapping("/movies/{id}")
	Movie replaceEmployee(@RequestBody Movie newMovie, @PathVariable Long id) {

		return service.findById(id).map(employee -> {
			employee.setTitle(newMovie.getTitle());
			employee.setProducers(newMovie.getProducers());
			return service.save(employee);
		}).orElseGet(() -> {
			newMovie.setId(id);
			return service.save(newMovie);
		});
	}

	@DeleteMapping("/movies/{id}")
	void deleteMovie(@PathVariable Long id) {
		service.deleteById(id);
	}

	@GetMapping("/interval-awards")
	ProducerResponse intervalAwards() {
		List<ProducerInterval> producers = service.winMinMax();

		ProducerResponse producerResponse = new ProducerResponse();
		producerResponse.setMin(producers.stream()
									.filter(producer -> "min".equals(producer.getType()))
									.toList());
		
		producerResponse.setMax(producers.stream()
									.filter(producer -> "max".equals(producer.getType()))
									.toList());

		return producerResponse;
	}

	class ProducerResponse {
		List<ProducerInterval> min = new ArrayList<ProducerInterval>();

		public List<ProducerInterval> getMin() {
			return min;
		}

		public void setMin(List<ProducerInterval> min) {
			this.min = min;
		}

		public List<ProducerInterval> getMax() {
			return max;
		}

		public void setMax(List<ProducerInterval> max) {
			this.max = max;
		}

		List<ProducerInterval> max = new ArrayList<ProducerInterval>();
	}

}
