package com.dpyl.match.odd.app.controller;

import com.dpyl.match.odd.app.dto.MatchOddsDto;
import com.dpyl.match.odd.app.service.MatchFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Match odds controller.
 */
@Slf4j
@RestController
@RequestMapping("/match/odds")
@AllArgsConstructor
public class MatchOddsController {

    private final MatchFacade matchFacade;

    /**
     * Create match odd response entity.
     *
     * @param matchOddsDto the match odds dto
     * @return the response entity
     */
    @Operation(summary = "API create new match odd for a specific match")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Successfully saved",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MatchOddsDto.class))),
                    @ApiResponse(
                            responseCode = "404", description = "You set invalid data on request body")
            }
    )
    @PostMapping
    public ResponseEntity<MatchOddsDto> createMatchOdd(@RequestBody MatchOddsDto matchOddsDto) {
        log.info("Entering MatchOddsController.createMatchOdd");
        return new ResponseEntity<>(matchFacade.createMatchOdd(matchOddsDto), HttpStatus.CREATED);
    }

    /**
     * Gets match odd by id.
     *
     * @param id the id
     * @return the match odd by id
     */
    @Operation(summary = "API returns match odd with specific id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully saved",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MatchOddsDto.class))),
                    @ApiResponse(
                            responseCode = "404", description = "You set invalid data on request body")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<MatchOddsDto> getMatchOddById(@PathVariable Long id) {
        log.info("Entering MatchOddsController.getMatchOddById");
        return new ResponseEntity<>(matchFacade.getMatchOddById(id), HttpStatus.OK);
    }

    /**
     * Update match odd response entity.
     *
     * @param matchOddsDto the match odds dto
     * @return the response entity
     */
    @Operation(summary = "API update an existing match odd")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MatchOddsDto.class))),
                    @ApiResponse(
                            responseCode = "404", description = "You set invalid data on request body")
            }
    )
    @PutMapping
    public ResponseEntity<MatchOddsDto> updateMatchOdd(@RequestBody MatchOddsDto matchOddsDto) {
        log.info("Entering MatchOddsController.updateMatchOdd");
        return new ResponseEntity<>(matchFacade.updateMatchOdd(matchOddsDto), HttpStatus.OK);
    }

    /**
     * Delete match odd.
     *
     * @param id the id
     */
    @Operation(summary = "API delete an existing match")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully deleted"),
                    @ApiResponse(
                            responseCode = "404", description = "You set invalid data on request body")
            }
    )
    @DeleteMapping("/{id}")
    public void deleteMatchOdd(@PathVariable Long id) {
        log.info("Entering MatchOddsController.deleteMatchOdd");
        log.info("Delete match odd with id {} :", id);
        matchFacade.deleteMatchOdd(id);
    }
}
