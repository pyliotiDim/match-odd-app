package com.dpyl.match.odd.app.service.impl;

import com.dpyl.match.odd.app.dto.MatchDto;
import com.dpyl.match.odd.app.dto.MatchOddsDto;
import com.dpyl.match.odd.app.entity.Match;
import com.dpyl.match.odd.app.entity.MatchOdds;
import com.dpyl.match.odd.app.service.MatchFacade;
import com.dpyl.match.odd.app.service.MatchOddService;
import com.dpyl.match.odd.app.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchFacadeImpl implements MatchFacade {

    private final MatchService matchService;
    private final MatchOddService matchOddService;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public MatchDto createMatch(MatchDto matchDto) {
        return modelMapper.map(matchService.createMatch(matchDto), MatchDto.class);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public MatchDto getMatchById(Long id) {
        return new ModelMapper().map(matchService.findMatch(id), MatchDto.class);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<MatchDto> getAllMatches() {
        List<MatchDto> matchDtoList = matchService.getAll().stream()
                .map(match -> modelMapper.map(match, MatchDto.class))
                .collect(Collectors.toList());

        Type type = new TypeToken<List<MatchDto>>() {}.getType();
        return modelMapper.map(matchDtoList, type);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public MatchDto updateMatch(MatchDto matchDto) {
        Match match = matchService.findMatch(matchDto.getId());
        return modelMapper.map(matchService.updateMatch(matchDto, match.getMatchOdds()), MatchDto.class);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteMatch(Long id) {
        matchService.deleteMatch(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public MatchOddsDto createMatchOdd(MatchOddsDto matchOddsDto) {
        Match match = matchService.findMatch(matchOddsDto.getMatchId());
        return modelMapper.map(matchOddService.createMatchOdd(matchOddsDto, match), MatchOddsDto.class);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public MatchOddsDto getMatchOddById(Long id) {
        return modelMapper.map(matchOddService.findMatchOdd(id), MatchOddsDto.class);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public MatchOddsDto updateMatchOdd(MatchOddsDto matchOddsDto) {
        Match match = null;
        MatchOdds matchOdd = matchOddService.findMatchOdd(matchOddsDto.getId());
        if (!matchOdd.getMatch().getId().equals(matchOddsDto.getMatchId())) {
            match = matchService.findMatch(matchOddsDto.getMatchId());
            match.getMatchOdds().add(matchOdd);
        }
        return modelMapper.map(matchOddService.updateMatchOdd(matchOddsDto, match), MatchOddsDto.class);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteMatchOdd(Long id) {
        matchOddService.deleteMatchOdd(id);
    }
}
