package com.dpyl.match.odd.app.controller;

import com.dpyl.match.odd.app.dto.MatchDto;
import com.dpyl.match.odd.app.service.MatchFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Match controller.
 */
@Slf4j
@RestController
@RequestMapping("/match")
@AllArgsConstructor
public class MatchController {

    private final MatchFacade matchFacade;

    /**
     * Create match response entity.
     *
     * @param matchDto the match dto
     * @return the response entity
     */
    @Operation(summary = "API create new match")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Successfully saved",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MatchDto.class))),
                    @ApiResponse(
                            responseCode = "404", description = "You set invalid data on request body")
            }
    )
    @PostMapping
    public ResponseEntity<MatchDto> createMatch(@RequestBody MatchDto matchDto) {
        log.info("Entering MatchController.createMatch");
        return new ResponseEntity<>(matchFacade.createMatch(matchDto), HttpStatus.CREATED);
    }

    /**
     * Gets match by id.
     *
     * @param id the id
     * @return the match by id
     */
    @Operation(summary = "API returns match with specific id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully saved",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MatchDto.class))),
                    @ApiResponse(
                            responseCode = "404", description = "You set invalid data on request body")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<MatchDto> getMatchById(@PathVariable Long id) {
        log.info("Entering MatchController.getMatchById");
        return new ResponseEntity<>(matchFacade.getMatchById(id), HttpStatus.OK);
    }

    /**
     * Gets all matches.
     *
     * @return the all matches
     */
    @Operation(summary = "API returns all matches")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully saved",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = MatchDto.class)))),
                    @ApiResponse(
                            responseCode = "404", description = "You set invalid data on request body")
            }
    )
    @GetMapping("/")
    public ResponseEntity<List<MatchDto>> getAllMatches() {
        log.info("Entering MatchController.getAllMatches");
        return new ResponseEntity<>(matchFacade.getAllMatches(), HttpStatus.OK);
    }

    /**
     * Update match response entity.
     *
     * @param matchDto the match dto
     * @return the response entity
     */
    @Operation(summary = "API update an existing match")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MatchDto.class))),
                    @ApiResponse(
                            responseCode = "404", description = "You set invalid data on request body")
            }
    )
    @PutMapping
    public ResponseEntity<MatchDto> updateMatch(@RequestBody MatchDto matchDto) {
        log.info("Entering MatchController.updateMatch");
        return new ResponseEntity<>(matchFacade.updateMatch(matchDto), HttpStatus.OK);
    }

    /**
     * Delete match.
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
    public void deleteMatch(@PathVariable Long id) {
        log.info("Entering MatchController.deleteMatch");
        log.info("Delete match with id {} :", id);
        matchFacade.deleteMatchOdd(id);
    }
}
