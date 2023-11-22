package com.dpyl.match.odd.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "match_odds")
public class MatchOdds {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_odds_id_seq")
    @SequenceGenerator(name = "match_odds_id_seq", sequenceName = "match_odds_id_seq", allocationSize = 1)
    @NotNull
    private Long id;

    @Column(name = "specifier")
    private String specifier;

    @Column(name = "odd")
    private Float odd;

    @ManyToOne
    @JoinColumn(name="match_id", referencedColumnName = "id", nullable = false)
    Match match;

}
