package com.apelab.example.exception;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by yaoyaolei on 2020/5/16 17:26
 */
@Getter
public class ExceptionResponse implements Serializable {

  private Long timestamp;
  private int status;
  private String error;
  private String message;
  private String path;
  private String serviceCode;

  @Builder
  private ExceptionResponse
      (
          int status,
          String error,
          String message,
          String path
      ) {
    this.timestamp = System.currentTimeMillis();
    this.status = status;
    this.error = error;
    this.message = message;
    this.path = path;
  }

  @Builder
  private ExceptionResponse
      (
          int status,
          String error,
          String message,
          String path,
          String serviceCode
      ) {
    this.timestamp = System.currentTimeMillis();
    this.status = status;
    this.error = error;
    this.message = message;
    this.path = path;
    this.serviceCode = serviceCode;
  }
}
