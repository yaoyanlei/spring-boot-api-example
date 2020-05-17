package com.apelab.example.exception;

/**
 * Created by yaoyaolei on 2020/5/16 17:26
 */
public class AccessException extends AppException {

  public AccessException() {
    super();
  }

  public AccessException(String message) {
    super(message);
  }

  /**
   * Created by chenyanbin on 22/10/2018.
   */
  public static class UserNotAuthorizedException extends AppException {

  }
}
