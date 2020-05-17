package com.apelab.example.exception;

import lombok.Getter;

/**
 * Created by yaoyaolei on 2020/5/16 17:26
 */
public class AppException extends RuntimeException {

  @Getter
  private String serviceCode;

  public AppException(String message, String serviceCode) {
    super(message);
    this.serviceCode = serviceCode;
  }

  public AppException(String message) {
    super(message);
  }

  public AppException() {
    super();
  }
}
