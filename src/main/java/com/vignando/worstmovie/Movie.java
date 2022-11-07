package com.vignando.worstmovie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class Movie {

	private @Id @GeneratedValue Long id;

	private Integer year_;
	private String title;
	private String studios;
	private String producers;
	private Boolean winner;

	Movie() {
	}

	Movie(Integer year, String title, String studios, String producers, Boolean winner) {
		this.year_ = year;
		this.title = title;
		this.studios = studios;
		this.producers = producers;
		this.winner = winner;

	}
	
	public Movie(int year, String producer) {
		this.year_ = year;
		this.producers = producer;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getYear() {
		return year_;
	}

	public void setYear(Integer year) {
		this.year_ = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStudios() {
		return studios;
	}

	public void setStudios(String studios) {
		this.studios = studios;
	}

	public String getProducers() {
		return producers;
	}

	public void setProducers(String producers) {
		this.producers = producers;
	}

	public Boolean getWinner() {
		return winner;
	}

	public void setWinner(Boolean winner) {
		this.winner = winner;
	}

	@Override
	public String toString() {
		return "Movie{" + 
//				"id=" + this.id + 
				"producers='" + this.producers + '\'' +
				", year='" + this.year_ + '\'' + 
//				", title='" + this.title + '\'' + 
//				", winner='" + this.winner + '\'' + 
				'}';
	}
}