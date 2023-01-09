package com.iqnext_pvt_ltd.assessment.controller;

import java.util.concurrent.CompletableFuture;

import javax.security.auth.login.LoginContext;

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
import com.iqnext_pvt_ltd.assessment.validator.Validator;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/fifa/api/v1/defence")
public class DefenceController extends BaseController{

	  private static final Logger logger = LoggerFactory.getLogger(DefenceController.class);
	  
	  @Autowired
	  private DefenceService defenceService;
	  
	  @Autowired
	  private Validator validator;
	  
	  
	 @GetMapping(value = "/get-all-details", produces = MediaType.APPLICATION_JSON_VALUE)
	  public DeferredResult<ResponseEntity<?>> getAllDetails(HttpServletRequest request) {
	    DeferredResult<ResponseEntity<?>> df = new DeferredResult<ResponseEntity<?>>();
	    
	    try {
	      logger.debug("Received Get All Player Defence Details request" );
	      CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<ResponseEntity<?>>();
	      String apiEndPoint = "/fifa/api/v1/defence/get-all-details";
	      long startTime = System.currentTimeMillis();
	      defenceService.getAllDetails(cf);
	      processDeferredResult(df, cf, apiEndPoint, startTime);
	    } catch (Exception e) {
	      logger.error("Get All Player Defence Details request failed by ", StringUtils.printStackTrace(e));
	      ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse.setCode(ErrorConstants.UNKNOWN_ERROR_CODE);
	        errorResponse.setMessage(ErrorMessages.UNKNOWN_ERROR_MESSAGE);
	      
	      df.setErrorResult(ResponseEntity.ok(errorResponse));
	    }
	    return df;
	  }
	 
	 @GetMapping(value = "/get-by-id/{playerId}")
	  public DeferredResult<ResponseEntity<?>> getDefencePlayerById(@PathVariable("playerId") Integer playerId,
	      HttpServletRequest request) {
	    DeferredResult<ResponseEntity<?>> df = new DeferredResult<ResponseEntity<?>>();
	    String apiEndPoint = "/fifa/api/v1/defence/get-by-id/{playerId}";
	    long startTime = System.currentTimeMillis();
	    try {
	      logger.debug("Received Defence Player request for playerId: {}", 
	          playerId);
	      CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<ResponseEntity<?>>();
	      ErrorResponse errorResponse = validator.validatePlayerId(playerId);
	      if (errorResponse != null) {
	        cf.complete(ResponseEntity.ok(errorResponse));
	      } else {
	        defenceService.getPlayerById(playerId, cf);
	      }
	      processDeferredResult(df, cf, apiEndPoint, startTime);
	     
	    } catch (Exception e) {
	      logger.error("get defence Player request failed for playerId: {} due to {}", playerId,
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
	      @RequestParam(name = "position", required = false) String position,
	      @RequestParam(name = "team", required = false) String team,
	      @RequestParam(name = "tackles", required = false) Long tackles,
	      @RequestParam(name = "tacklesWon", required = false) Long tacklesWon,
	      HttpServletRequest request) {
	    DeferredResult<ResponseEntity<?>> df = new DeferredResult<ResponseEntity<?>>();
	    String apiEndPoint = "/fifa/api/v1/defence/apply-filter";
	    long startTime = System.currentTimeMillis();
	    try {
	      
	      logger.debug("Received Get Players request for according to filter applied");
	      CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<ResponseEntity<?>>();
	      ErrorResponse errorResponse;
	      if(tackles!=null && tackles<=0) {
	    	  errorResponse = new ErrorResponse(ResponseError.INVALID_TACKLES_COUNT.getCode(),
	    	          ResponseError.INVALID_TACKLES_COUNT.getMessage());
	      }
	      defenceService.searchplayers(position, team, tackles, tacklesWon, cf);
	      processDeferredResult(df, cf, apiEndPoint, startTime);
	    } catch (Exception e) {
		      logger.error("Get All Player defence Details request failed by ", StringUtils.printStackTrace(e));
		      ErrorResponse errorResponse = new ErrorResponse();
		        errorResponse.setCode(ErrorConstants.UNKNOWN_ERROR_CODE);
		        errorResponse.setMessage(ErrorMessages.UNKNOWN_ERROR_MESSAGE);
		      
		      df.setErrorResult(ResponseEntity.ok(errorResponse));
		    }
		   return df;
		}

}
