package com.levi9.esport_web_app.dto;

import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class TeamResponse {

    private UUID id;
    private String teamName;
    private List<PlayerResponse> players;
}