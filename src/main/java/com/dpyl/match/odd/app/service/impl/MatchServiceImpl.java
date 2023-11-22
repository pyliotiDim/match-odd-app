package com.dpyl.match.odd.app.service.impl;

import com.dpyl.match.odd.app.dto.MatchDto;
import com.dpyl.match.odd.app.entity.Match;
import com.dpyl.match.odd.app.entity.MatchOdds;
import com.dpyl.match.odd.app.exception.EntityNotFoundException;
import com.dpyl.match.odd.app.repository.MatchRepository;
import com.dpyl.match.odd.app.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Match createMatch(MatchDto matchDto) {
        Match match = modelMapper.map(matchDto, Match.class);
        return matchRepository.save(match);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Match findMatch(Long id) {
        return matchRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException(String.format("Match with ID: '%s' not found", id)));
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Match updateMatch(MatchDto matchDto, List<MatchOdds> matchOdds) {
        Match match = modelMapper.map(matchDto, Match.class);
        match.setMatchOdds(matchOdds);
        return matchRepository.save(match);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteMatch(Long id) {
        Match match = findMatch(id);
        matchRepository.delete(match);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Match> getAll() {
        return matchRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }
}
