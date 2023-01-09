package com.iqnext_pvt_ltd.assessment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iqnext_pvt_ltd.assessment.model.PlayerDefence;
import com.iqnext_pvt_ltd.assessment.model.PlayerStats;

@Repository
public interface PlayerSatisticsDAO extends JpaRepository<PlayerStats, Integer>{

	@Query("select s from PlayerStats s where (:games is null or s.games=:games) and (:minutes is null or s.minutes=:minutes) and (:goals is null or s.goals=:goals) and (:birth_year is null or s.birth_year=:birth_year)")
	public List<PlayerStats> searchPlayersByFilters(Long games, Long minutes, Long goals, Long birth_year);

	
	
}
