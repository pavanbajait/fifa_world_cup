package com.iqnext_pvt_ltd.assessment.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.iqnext_pvt_ltd.assessment.config.StringUtils;
import com.iqnext_pvt_ltd.assessment.constants.ErrorConstants;
import com.iqnext_pvt_ltd.assessment.constants.ErrorMessages;
import com.iqnext_pvt_ltd.assessment.constants.ErrorResponse;
import com.iqnext_pvt_ltd.assessment.constants.OkResponseDO;
import com.iqnext_pvt_ltd.assessment.constants.ResponseError;
import com.iqnext_pvt_ltd.assessment.dao.PlayerSatisticsDAO;
import com.iqnext_pvt_ltd.assessment.model.PlayerStats;

@Service
public class StatisticsService {

private static final Logger logger = LoggerFactory.getLogger(StatisticsService.class);
	
	@Autowired
	private PlayerSatisticsDAO statisticsDAO;
	
	@Async("asyncExecutor")
	public void getAllDetails(CompletableFuture<ResponseEntity<?>> cf) {
		ErrorResponse errorResponse;
	    try {
	      List<PlayerStats> playersStatistics = statisticsDAO.findAll();
	      
	      cf.complete(ResponseEntity.ok(new OkResponseDO<>(playersStatistics)));
	    } catch (Exception e) {
	      logger.error("Exception while getting all Statistics players by ",
	          StringUtils.printStackTrace(e));
	      errorResponse =
	          new ErrorResponse(ErrorConstants.UNKNOWN_ERROR_CODE, ErrorMessages.UNKNOWN_ERROR_MESSAGE);
	      cf.complete(ResponseEntity.ok(errorResponse));
	    }
	}

	 @Async("asyncExecutor")
	  public void getPlayerById(Integer playerId, CompletableFuture<ResponseEntity<?>> resp) {
	    ErrorResponse errorResponse;
	    try {
	    	Optional<PlayerStats> playerStats = statisticsDAO.findById(playerId);
	      if (playerStats == null) {
	        errorResponse = new ErrorResponse(ResponseError.PLAYER_NOT_FOUND.getCode(),
	            ResponseError.PLAYER_NOT_FOUND.getMessage());
	        resp.complete(ResponseEntity.ok(errorResponse));
	        return;
	      }
	      resp.complete(ResponseEntity.ok(new OkResponseDO<>(playerStats)));
	    } catch (Exception e) {
	      logger.error("Exception while getting player for playerId: {} due to {}", playerId,
	          StringUtils.printStackTrace(e));
	      errorResponse =
	          new ErrorResponse(ErrorConstants.UNKNOWN_ERROR_CODE, ErrorMessages.UNKNOWN_ERROR_MESSAGE);
	      resp.complete(ResponseEntity.ok(errorResponse));
	    }
	  }

	 @Async("asyncExecutor")
	 public void searchplayers(Long games, Long minutes, Long goals, Long birthYear,
			CompletableFuture<ResponseEntity<?>> cf) {
		ErrorResponse errorResponse;
	    try {
	    	 List<PlayerStats> playersStatistics = statisticsDAO.searchPlayersByFilters(games, minutes, goals, birthYear);

	      cf.complete(ResponseEntity.ok(new OkResponseDO<>(playersStatistics)));
	    } catch (Exception e) {
		      logger.error("Exception while getting all Statistics players by ",
			          StringUtils.printStackTrace(e));
			      errorResponse =
			          new ErrorResponse(ErrorConstants.UNKNOWN_ERROR_CODE, ErrorMessages.UNKNOWN_ERROR_MESSAGE);
			      cf.complete(ResponseEntity.ok(errorResponse));
		}
	}
}
