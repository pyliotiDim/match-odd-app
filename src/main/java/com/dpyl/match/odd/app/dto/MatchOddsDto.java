package com.dpyl.match.odd.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MatchOddsDto {

    private Long id;

    private String specifier;

    private Float odd;

    private Long matchId;

}
