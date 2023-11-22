package com.dpyl.match.odd.app.service;

import com.dpyl.match.odd.app.dto.MatchDto;
import com.dpyl.match.odd.app.entity.Match;
import com.dpyl.match.odd.app.entity.MatchOdds;

import java.util.List;

public interface MatchService {

    Match createMatch(MatchDto matchDto);

    Match findMatch(Long id);

    Match updateMatch(MatchDto matchDto, List<MatchOdds> matchOdds);

    void deleteMatch(Long id);

    List<Match> getAll();

    Match saveMatch(Match match);
}
