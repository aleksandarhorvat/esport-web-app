package com.levi9.esport_web_app.dto;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TeamRequest {

	@NotBlank(message = "Team name cannot be empty")
	private String teamName;

	@NotEmpty(message = "Players list cannot be empty")
	private List<UUID> players;

}