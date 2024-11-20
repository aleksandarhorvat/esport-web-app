package com.levi9.esport_web_app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PlayerRequest {

	@NotBlank(message = "Nickname cannot be empty")
	private String nickname;
}