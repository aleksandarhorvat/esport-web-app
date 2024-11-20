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
import lombok.Data;

@Data
@Entity
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public UUID id;

	public String teamName;

	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private List<Player> players = new ArrayList<>();

}