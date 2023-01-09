package com.iqnext_pvt_ltd.assessment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PlayerStats {
	
	 @Id
	 private Integer id;
	  
	  private String player;
	  private String position;
	  private String team;
	  private String age;
	  private String club;
	  private Integer birth_year;
	  private Integer games;
	  private Integer games_starts;
	  private Integer minutes;
	  private Double minutes_90s;
	  private Integer goals;
	  private Integer assists;
	  private Integer goals_pens;
	  private Integer pens_made;
	  private Integer pens_att;
	  private Integer cards_yellow;
	  private Integer card_red;
	  private Double goals_per90;
	  private Double assists_per90;
	  private Double goals_assists_per90;
	  private Double goals_pens_per90;
	  private Double goals_assists_pens_per90;
	  private Double xg;
	  private Double npxg;
	  private Double xg_assist;
	  private Double npxg_xg_assist;
	  private Double xg_per90;
	  private Double xg_assist_per90;
	  private Double xg_xg_assist_per90;
	  private Double npxg_per90;
	  private Double npxg_xg_assist_per90; 

}
