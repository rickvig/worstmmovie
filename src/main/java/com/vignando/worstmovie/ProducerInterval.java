package com.vignando.worstmovie;

public class ProducerInterval {

	String producer;
	String type;
	Integer previousWin;
	Integer followingWin;
	Integer interval = 0;

	ProducerInterval() {
	}

	public ProducerInterval(String producer, String type) {
		this.producer = producer;
		this.type = type;
	}

	public ProducerInterval(String producer, String type, int previousWin, int followingWin, int interval) {
		this.producer = producer;
		this.type = type;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
		this.interval = interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;Integer previousWin;
		Integer followingWin;
	}

	public void setPreviousWin(Integer previousWin) {
		this.previousWin = previousWin;
	}

	public void setFollowingWin(Integer followingWin) {
		this.followingWin = followingWin;
	}

	public int getInterval() {
		return this.interval;
	}

	public String getProducer() {
		return producer;
	}

	public Integer getPreviousWin() {
		return previousWin;
	}

	public Integer getFollowingWin() {
		return followingWin;
	}

	public String getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "ProducerResponse [producer=" + producer + ", type=" + type + ", previousWin=" + previousWin
				+ ", followingWin=" + followingWin + ", interval=" + interval + "]";
	}


}
