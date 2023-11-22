package com.dpyl.match.odd.app.repository;

import com.dpyl.match.odd.app.entity.MatchOdds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchOddsRepository extends JpaRepository<MatchOdds,Long> {
}
