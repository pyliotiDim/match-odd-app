package com.dpyl.match.odd.app.service.impl;

import com.dpyl.match.odd.app.dto.MatchOddsDto;
import com.dpyl.match.odd.app.entity.Match;
import com.dpyl.match.odd.app.entity.MatchOdds;
import com.dpyl.match.odd.app.exception.EntityNotFoundException;
import com.dpyl.match.odd.app.repository.MatchOddsRepository;
import com.dpyl.match.odd.app.service.MatchOddService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MatchOddServiceImpl implements MatchOddService {

    private final MatchOddsRepository matchOddsRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public MatchOdds createMatchOdd(MatchOddsDto matchOddsDto, Match match) {
        MatchOdds matchOdds = modelMapper.map(matchOddsDto, MatchOdds.class);
        matchOdds.setMatch(match);
        return matchOddsRepository.save(matchOdds);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public MatchOdds findMatchOdd(Long id) {
        return matchOddsRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException(String.format("Match odd with ID: '%s' not found", id)));
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public MatchOdds updateMatchOdd(MatchOddsDto matchOddsDto, Match match) {
        MatchOdds matchOdd = modelMapper.map(matchOddsDto, MatchOdds.class);
        matchOdd.setMatch(match);

        return matchOddsRepository.save(matchOdd);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteMatchOdd(Long id) {
        MatchOdds matchOdd = findMatchOdd(id);
        matchOddsRepository.delete(matchOdd);
    }
}
