package com.it.survey.demo.dto;

import lombok.Data;

@Data
public class SurveyStadisticsDTO {
	private String music;
	private Long total;
	private Long universe;

	public String getMusic() {
		return music;
	}

	public void setMusic(String music) {
		this.music = music;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	} 
	
	public Long getUniverse() {
		return universe;
	}

	public void setUniverse(Long universe) {
		this.universe = universe;
	} 
}