package com.dpyl.match.odd.app.service;

import com.dpyl.match.odd.app.dto.MatchDto;
import com.dpyl.match.odd.app.entity.Match;
import com.dpyl.match.odd.app.entity.MatchOdds;
import com.dpyl.match.odd.app.entity.Sport;
import com.dpyl.match.odd.app.exception.EntityNotFoundException;
import com.dpyl.match.odd.app.repository.MatchRepository;
import com.dpyl.match.odd.app.service.impl.MatchServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchServiceTest {

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private MatchServiceImpl matchService;

    private MatchDto matchDto;
    private List<MatchOdds> matchOdds;

    @BeforeEach
    void setUp() {
        matchDto = new MatchDto();
        matchOdds = Collections.singletonList(creatMatchOdd(1L));

    }

    @Test
    void test_createMatch() {
        Match match = new Match();
        when(matchRepository.save(any())).thenReturn(match);

        Match result = matchService.createMatch(matchDto);

        assertNotNull(result);
        assertEquals(match, result);
        verify(matchRepository, times(1)).save(any(Match.class));
    }

    @Test
    void test_findMatch() {
        Long existingId = 1L;
        Match match = new Match();
        when(matchRepository.findById(existingId)).thenReturn(Optional.of(match));

        Match result = matchService.findMatch(existingId);

        assertNotNull(result);
        assertEquals(match, result);
    }

    @Test
    void test_findMatch_ThrowEntityNotFoundException() {
        Long nonExistingId = 2L;
        when(matchRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> matchService.findMatch(nonExistingId));
    }

    @Test
    void test_updateMatch() {
        Match match = new Match();
        when(matchRepository.save(any())).thenReturn(match);

        Match result = matchService.updateMatch(matchDto, matchOdds);

        assertNotNull(result);
        assertEquals(match, result);
        verify(matchRepository, times(1)).save(any(Match.class));
    }

    @Test
    void test_deleteMatch() {
        Long existingId = 1L;
        Match match = createMatch(1L);
        when(matchRepository.findById(existingId)).thenReturn(Optional.of(match));

        assertDoesNotThrow(() -> matchService.deleteMatch(existingId));
        verify(matchRepository, times(1)).delete(any(Match.class));
    }

    @Test
    void test_getAll() {
        List<Match> matches = Collections.singletonList(new Match());
        when(matchRepository.findAll()).thenReturn(matches);

        List<Match> result = matchService.getAll();

        assertNotNull(result);
        assertEquals(matches, result);
        assertEquals(matches.size(), result.size());
    }

    @Test
    void test_saveMatch() {
        Match match = createMatch(1L);
        when(matchRepository.save(match)).thenReturn(match);

        Match result = matchService.saveMatch(match);

        assertNotNull(result);
        assertEquals(match, result);
        assertEquals(match.getTeamA(), result.getTeamA());
        verify(matchRepository, times(1)).save(any(Match.class));
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
