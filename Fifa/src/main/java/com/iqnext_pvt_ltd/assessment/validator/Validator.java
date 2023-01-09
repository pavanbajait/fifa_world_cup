package com.iqnext_pvt_ltd.assessment.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.iqnext_pvt_ltd.assessment.constants.ErrorResponse;
import com.iqnext_pvt_ltd.assessment.constants.ResponseError;

@Service
public class Validator {

	private static final Logger logger = LoggerFactory.getLogger(Validator.class);

	  public ErrorResponse validatePlayerId(Integer playerId) {
	    if (playerId == null || playerId < 1) {
	      return new ErrorResponse(ResponseError.PLAYER_ID_IS_REQUIRED.getCode(),
	          ResponseError.PLAYER_ID_IS_REQUIRED.getMessage());
	    }
	    return null;
	  }
}
