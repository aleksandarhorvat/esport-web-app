package com.levi9.esport_web_app.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.levi9.esport_web_app.model.Team;

public interface TeamRepository extends JpaRepository<Team, UUID> {

	boolean existsByTeamName(String teamName);

}