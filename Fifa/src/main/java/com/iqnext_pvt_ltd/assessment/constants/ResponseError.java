package com.iqnext_pvt_ltd.assessment.constants;

public enum ResponseError {
	INVALID_GAME_COUNT("Games Count should be valid or not less than zero", 1402),INVALID_TACKLES_COUNT("Tackles can be less than zero",1405),  PLAYER_NOT_FOUND("Player not found", 1447), PLAYER_ID_IS_REQUIRED( "Player id cannot be null", 1448);                                                                                                                               

  private final String message;
  private final Integer code;

  ResponseError(String message, Integer code) {

    this.message = message;
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public Integer getCode() {
    return code;
  }

}
