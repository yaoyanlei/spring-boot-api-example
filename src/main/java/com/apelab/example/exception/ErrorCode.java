package com.apelab.example.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * TODO REPLACE_ME 替换ERROR_CODE
 */
@RequiredArgsConstructor
public enum ErrorCode {

  // ERROR CODE格式: <appid>__<业务错误代码>
  INVALID_PARAMETER("SAMPLE_INVALID_PARAMETER", "Invalid parameters");

  @Getter
  private final String code;
  @Getter
  private final String description;

}