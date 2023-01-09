package com.iqnext_pvt_ltd.assessment.constants;

public enum ResponseSuccess {
  SUCCESS_PLAYER_CREATION("Create Player Successful");
                                     
  private final String message;

  ResponseSuccess(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
