package com.dpyl.match.odd.app.dto;

import com.dpyl.match.odd.app.entity.Sport;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class MatchDto {

    private Long id;

    private String description;

    private LocalDate matchDate;

    @Schema(type = "String", pattern = "HH:mm:SS")
    private LocalTime matchTime;

    private String teamA;

    private String teamB;

    private Sport sport;

}
