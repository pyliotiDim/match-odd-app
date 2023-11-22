package com.dpyl.match.odd.app.service;

import com.dpyl.match.odd.app.dto.MatchDto;
import com.dpyl.match.odd.app.dto.MatchOddsDto;

import java.util.List;

public interface MatchFacade {

    MatchDto createMatch(MatchDto matchDto);

    MatchDto getMatchById(Long id);

    List<MatchDto> getAllMatches();

    MatchDto updateMatch(MatchDto matchDto);

    void deleteMatch(Long id);

    //operations for match odd

    MatchOddsDto createMatchOdd(MatchOddsDto matchOddsDto);

    MatchOddsDto getMatchOddById(Long id);

    MatchOddsDto updateMatchOdd(MatchOddsDto matchOddsDto);

    void deleteMatchOdd(Long id);
}
