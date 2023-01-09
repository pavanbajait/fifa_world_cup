package com.iqnext_pvt_ltd.assessment.model;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@jakarta.persistence.Entity
public class PlayerDefence {
	
	  @Id
	  private Integer id;
	  private String player;
	  private String position;
	  private String team;
	  private String age;
	  private Integer birth_year;
	  private Double minutes_90s;
	  private Integer tackles;
	  private Integer tackles_won;
	  private Integer tackles_def_3rd;
	  private Integer tackles_mid_3rd;
	  private Integer tackles_att_3rd;
	  private Integer dribble_tackles;
	  private Integer dribbles_vs;
	  private Double dribble_tackles_pct;
	  private Integer dribbled_past;
	  private Integer blocks;
	  private Integer blocked_shots;
	  private Integer blocked_passes;
	  private Integer interceptions;
	  private Integer tackles_interceptions;
	  private Integer clearances;
	  private Integer errors;

}
