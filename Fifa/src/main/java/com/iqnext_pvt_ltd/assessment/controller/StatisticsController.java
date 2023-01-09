package com.iqnext_pvt_ltd.assessment.controller;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.iqnext_pvt_ltd.assessment.config.StringUtils;
import com.iqnext_pvt_ltd.assessment.constants.ErrorConstants;
import com.iqnext_pvt_ltd.assessment.constants.ErrorMessages;
import com.iqnext_pvt_ltd.assessment.constants.ErrorResponse;
import com.iqnext_pvt_ltd.assessment.constants.ResponseError;
import com.iqnext_pvt_ltd.assessment.service.DefenceService;
import com.iqnext_pvt_ltd.assessment.service.StatisticsService;
import com.iqnext_pvt_ltd.assessment.validator.Validator;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/fifa/api/v1/statistics")
public class StatisticsController extends BaseController{

	  private static final Logger logger = LoggerFactory.getLogger(DefenceController.class);
	  
	  @Autowired
	  private StatisticsService statisticsService;
	  
	  @Autowired
	  private Validator validator;
	  
	  
	 @GetMapping(value = "/get-all-details", produces = MediaType.APPLICATION_JSON_VALUE)
	  public DeferredResult<ResponseEntity<?>> getAllDetails(HttpServletRequest request) {
	    DeferredResult<ResponseEntity<?>> df = new DeferredResult<ResponseEntity<?>>();
	    String apiEndPoint = "/fifa/api/v1/statistics/get-all-details";
	      long startTime = System.currentTimeMillis();
	    try {
	      logger.debug("Received Get All Player statistics Details request" );
	      CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<ResponseEntity<?>>();

	      statisticsService.getAllDetails(cf);
	      processDeferredResult(df, cf, apiEndPoint, startTime);
	    
	    } catch (Exception e) {
	      logger.error("Get All Player statistics Details request failed by ", StringUtils.printStackTrace(e));
	      ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse.setCode(ErrorConstants.UNKNOWN_ERROR_CODE);
	        errorResponse.setMessage(ErrorMessages.UNKNOWN_ERROR_MESSAGE);
	      
	      df.setErrorResult(ResponseEntity.ok(errorResponse));
	    }
	    return df;
	  }
	 
	 @GetMapping(value = "/get-by-id/{playerId}")
	  public DeferredResult<ResponseEntity<?>> getStatisticsPlayerById(@PathVariable("playerId") Integer playerId,
	      HttpServletRequest request) {
	    DeferredResult<ResponseEntity<?>> df = new DeferredResult<ResponseEntity<?>>();
	    String apiEndPoint = "/fifa/api/v1/statistics/get-by-id/{playerId}";
	      long startTime = System.currentTimeMillis();
	    try {
	      logger.debug("Received statistics Player request for playerId: {}", 
	          playerId);
	      CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<ResponseEntity<?>>();
	      ErrorResponse errorResponse = validator.validatePlayerId(playerId);
	      if (errorResponse != null) {
	        cf.complete(ResponseEntity.ok(errorResponse));
	      } else {
	    	  statisticsService.getPlayerById(playerId, cf);
	      }
	      processDeferredResult(df, cf, apiEndPoint, startTime);
	    } catch (Exception e) {
	      logger.error("get statistics Player request failed for playerId: {} due to {}", playerId,
	          StringUtils.printStackTrace(e));
	      ErrorResponse errorResponse = new ErrorResponse();
	      
	        errorResponse.setCode(ErrorConstants.UNKNOWN_ERROR_CODE);
	        errorResponse.setMessage(ErrorMessages.UNKNOWN_ERROR_MESSAGE);
	     
	      df.setResult(ResponseEntity.ok(errorResponse));
	    }
	    return df;
	  }
	 
	 @GetMapping(value = "/apply-filter", produces = MediaType.APPLICATION_JSON_VALUE)
	  public DeferredResult<ResponseEntity<?>> getAllPlayersByFilter(
	      @RequestParam(name = "games", required = false) Long games,
	      @RequestParam(name = "minutes", required = false) Long minutes,
	      @RequestParam(name = "goals", required = false) Long goals,
	      @RequestParam(name = "birthYear", required = false) Long birthYear,
	      HttpServletRequest request) {
	    DeferredResult<ResponseEntity<?>> df = new DeferredResult<ResponseEntity<?>>();
	    String apiEndPoint = "/fifa/api/v1/statistics/apply-filter";
	      long startTime = System.currentTimeMillis();
	    try {
	      
	      logger.debug("Received Get Players request for according to filter applied");
	      CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<ResponseEntity<?>>();
	      ErrorResponse errorResponse;
	      if(games!=null && games<=0) {
	    	  errorResponse = new ErrorResponse(ResponseError.INVALID_GAME_COUNT.getCode(),
	    	          ResponseError.INVALID_GAME_COUNT.getMessage());
	      }
	      statisticsService.searchplayers(games, minutes, goals, birthYear, cf);
	      processDeferredResult(df, cf, apiEndPoint, startTime);
	    } catch (Exception e) {
		      logger.error("Get All Player statistics Details request failed by ", StringUtils.printStackTrace(e));
		      ErrorResponse errorResponse = new ErrorResponse();
		        errorResponse.setCode(ErrorConstants.UNKNOWN_ERROR_CODE);
		        errorResponse.setMessage(ErrorMessages.UNKNOWN_ERROR_MESSAGE);
		      
		      df.setErrorResult(ResponseEntity.ok(errorResponse));
		    }
		   return df;
		}
}
