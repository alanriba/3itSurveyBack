package com.it.survey.demo.dto;

import lombok.Data;

@Data
public class SurveyDTO {
	private String id;
	private String email;
	private String music;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMusic() {
		return music;
	}

	public void setMusic(String music) {
		this.music = music;
	}

}
