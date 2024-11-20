package com.levi9.esport_web_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.levi9.esport_web_app.model.Match;
import com.levi9.esport_web_app.model.MatchId;

public interface MatchRepository extends JpaRepository<Match, MatchId> {

}