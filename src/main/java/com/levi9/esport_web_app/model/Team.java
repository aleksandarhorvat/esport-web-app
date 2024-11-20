package com.levi9.esport_web_app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public UUID id;

	public String teamName;

	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private List<Player> players = new ArrayList<>();

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}