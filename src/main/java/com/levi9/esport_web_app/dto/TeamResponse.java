package com.levi9.esport_web_app.dto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.levi9.esport_web_app.model.Team;

import lombok.Data;

@Data
public class TeamResponse {

	private UUID id;
	private String teamName;
	private List<PlayerResponse> players;

	public TeamResponse(Team team) {
		this.id = team.getId();
		this.teamName = team.getTeamName();
		this.players = team.getPlayers().stream().map(player -> new PlayerResponse(player)) // Ensure this is done
				.collect(Collectors.toList());
	}

}