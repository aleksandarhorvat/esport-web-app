package com.levi9.esport_web_app.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public UUID id;

	public String nickname;

	public int wins;

	public int losses;

	public int elo;

	public int hoursPlayed;

	@ManyToOne
	@JoinColumn(name = "team_id")
	public Team team;

	public int ratingAdjustment;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getElo() {
		return elo;
	}

	public void setElo(int elo) {
		this.elo = elo;
	}

	public int getHoursPlayed() {
		return hoursPlayed;
	}

	public void setHoursPlayed(int hoursPlayed) {
		this.hoursPlayed = hoursPlayed;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getRatingAdjustment() {
		return ratingAdjustment;
	}

	public void setRatingAdjustment(int ratingAdjustment) {
		this.ratingAdjustment = ratingAdjustment;
	}

}