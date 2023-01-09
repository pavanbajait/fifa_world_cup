package com.iqnext_pvt_ltd.assessment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iqnext_pvt_ltd.assessment.model.PlayerDefence;

@Repository
public interface PlayerDefenceDAO extends JpaRepository<PlayerDefence, Integer> {
	
	
	@Query("select p from PlayerDefence p where (:position is null or p.position=:position) and (:team is null or p.team=:team) and (:tackles is null or p.tackles=:tackles) and (:tackles_won is null or p.tackles_won=:tackles_won)")
	public List<PlayerDefence> searchPlayersByFilters(String position, String team, Long tackles, Long tackles_won); 

}
