package com.dpyl.match.odd.app.service;

import com.dpyl.match.odd.app.dto.MatchOddsDto;
import com.dpyl.match.odd.app.entity.Match;
import com.dpyl.match.odd.app.entity.MatchOdds;

public interface MatchOddService {

    MatchOdds createMatchOdd(MatchOddsDto matchOddsDto, Match match);

    MatchOdds findMatchOdd(Long id);

    MatchOdds updateMatchOdd(MatchOddsDto matchOddsDto, Match match);

    void deleteMatchOdd(Long id);
}
