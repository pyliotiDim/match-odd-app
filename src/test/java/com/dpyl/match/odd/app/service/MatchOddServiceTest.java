package com.dpyl.match.odd.app.service;

import com.dpyl.match.odd.app.dto.MatchOddsDto;
import com.dpyl.match.odd.app.entity.Match;
import com.dpyl.match.odd.app.entity.MatchOdds;
import com.dpyl.match.odd.app.entity.Sport;
import com.dpyl.match.odd.app.exception.EntityNotFoundException;
import com.dpyl.match.odd.app.repository.MatchOddsRepository;
import com.dpyl.match.odd.app.service.impl.MatchOddServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchOddServiceTest {

    @Mock
    private MatchOddsRepository matchOddsRepository;

    @InjectMocks
    private MatchOddServiceImpl matchOddService;

    private MatchOddsDto matchOddsDto;

    @BeforeEach
    void setUp() {
        matchOddsDto = new MatchOddsDto();
        matchOddsDto.setId(1L);
        matchOddsDto.setSpecifier("x");
        matchOddsDto.setOdd(1.5F);
        matchOddsDto.setMatchId(1L);
    }

    @Test
    void test_findMatchOdd() {
        Long existingId = 1L;
        MatchOdds matchOdds = new MatchOdds();
        when(matchOddsRepository.findById(existingId)).thenReturn(Optional.of(matchOdds));

        MatchOdds result = matchOddService.findMatchOdd(existingId);

        assertNotNull(result);
        assertEquals(matchOdds, result);
    }

    @Test
    void test_findMatchOdd_ThrowEntityNotFoundException() {
        Long nonExistingId = 2L;
        when(matchOddsRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> matchOddService.findMatchOdd(nonExistingId));
    }

    @Test
    void test_createMatchOdd() {
        MatchOdds matchOdds = new MatchOdds();
        when(matchOddsRepository.save(any(MatchOdds.class))).thenReturn(matchOdds);

        MatchOdds result = matchOddService.createMatchOdd(matchOddsDto, createMatch(1L));

        assertNotNull(result);
        assertEquals(matchOdds, result);
        verify(matchOddsRepository, times(1)).save(any(MatchOdds.class));
    }

    private Match createMatch(Long matchId) {
        Match match = new Match();
        match.setId(matchId);
        match.setDescription("new match");
        match.setTeamA("OSFP");
        match.setTeamB("PAOK");
        match.setSport(Sport.FOOTBALL);
        return match;
    }

    private MatchOdds creatMatchOdd(Long matchId) {
        MatchOdds matchOdds = new MatchOdds();
        matchOdds.setId(1L);
        matchOdds.setMatch(createMatch(matchId));
        matchOdds.setOdd(2F);
        matchOdds.setSpecifier("x");
        return matchOdds;
    }
}
