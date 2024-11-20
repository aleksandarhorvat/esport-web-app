package com.levi9.esport_web_app.dto;

import jakarta.validation.constraints.NotBlank;

public class PlayerRequest {

	@NotBlank(message = "Nickname cannot be empty")
	private String nickname;

	// Getters and Setters
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}