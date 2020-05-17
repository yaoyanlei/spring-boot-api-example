package com.apelab.example.exception;

import com.apelab.example.exception.AccessException.UserNotAuthorizedException;
import com.google.common.collect.Lists;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by yaoyaolei on 2020/5/16 17:26
 */
@RestControllerAdvice
@Slf4j
public class GlobalCtrlExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity handleException
      (
          Exception e,
          HttpServletRequest request
      ) {
    return handle(e, "服务器出错了，请呼叫程序员小哥~~!", request, HttpStatus.INTERNAL_SERVER_ERROR);
  }


  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity handleEntityNotFound
      (
          EntityNotFoundException ex,
          HttpServletRequest request
      ) {
    return handle(ex, "未找到指定资源", request, HttpStatus.ACCEPTED);
  }



  @ExceptionHandler(AccessException.class)
  public ResponseEntity accessException
      (
          AccessException ex,
          HttpServletRequest request
      ) {
    return handle(ex, "权限不足", request, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(UserNotAuthorizedException.class)
  public ResponseEntity handUserNotAuthorized
      (
          UserNotAuthorizedException ex,
          HttpServletRequest request
      ) {
    return handle(ex, "请先登录，再操作！", request, HttpStatus.UNAUTHORIZED);
  }

  /**
   * restfu接口参数不合法时触发
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity handleMethodArgumentNotValidException
  (
      MethodArgumentNotValidException ex,
      HttpServletRequest request
  ) {
    List<FieldError> errors = ex.getBindingResult().getFieldErrors();
    StringBuffer buf = new StringBuffer();
    for (int i = 0; i < errors.size(); i++) {
      if (i != 0) {
        buf.append(", ");
      }
      buf.append(errors.get(i).getDefaultMessage());
    }
    return handle(ex, buf.toString(), request, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity handleHttpMessageNotReadableException
      (
          HttpMessageNotReadableException e,
          HttpServletRequest request
      ) {
    return handle(e, "无法正确解析客户端Json数据", request, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity httpMediaTypeNotSupportedException
      (
          HttpMessageNotReadableException e,
          HttpServletRequest request
      ) {
    return handle(e, "仅支持application/json;charset=UTF-8格式数据", request, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity constraintViolationException
      (
          ConstraintViolationException e,
          HttpServletRequest request
      ) {
    StringBuffer buf = new StringBuffer();
    String messsageAble = e.getMessage();
    String[] messageItem = messsageAble.split(",");
    for (String message : messageItem) {
      String[] fieldItem = message.split(":");
      buf.append(fieldItem[1] + ",");
    }
    String errorMessage = buf.delete(buf.toString().length() - 1, buf.toString().length())
        .toString();
    return handle(e, errorMessage.trim(), request, HttpStatus.BAD_REQUEST);
  }



  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity handleMissingServletRequestParameterException
      (
          MissingServletRequestParameterException e,
          HttpServletRequest request
      ) {
    return handle(e, request, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(BindException.class)
  public ResponseEntity handleBindException
      (
          BindException e,
          HttpServletRequest request
      ) {
    List<String> errorMsgs = Lists.newArrayList();
    e.getAllErrors().forEach(objectError -> errorMsgs.add(objectError.getDefaultMessage()));
    return handle
        (
            e,
            String.join(",", errorMsgs),
            request,
            HttpStatus.BAD_REQUEST
        );
  }

  private ResponseEntity handle
      (
          Exception e,
          HttpServletRequest request,
          HttpStatus httpStatus
      ) {
    return handle(e, null, request, httpStatus);
  }

  private ResponseEntity handle
      (
          Exception e,
          String preferredMessage,
          HttpServletRequest request,
          HttpStatus httpStatus
      ) {
    String message = preferredMessage;
    if (StringUtils.isEmpty(message)) {
      message = e.getMessage();
    }

    ExceptionResponse.ExceptionResponseBuilder builder = ExceptionResponse.builder();
    builder.message(message);
    builder.status(httpStatus.value());
    builder.error(httpStatus.getReasonPhrase());
    builder.path(request.getRequestURI());

    if (e instanceof AppException
        || e instanceof MethodArgumentNotValidException
        || e instanceof ConstraintViolationException) {
      log.warn("处理请求：【{}】/【method: {}】, 出错：【{}】，返回应答：【{}】",
          request.getRequestURI(),
          request.getMethod(),
          e.getMessage(),
          httpStatus
      );
      if (e instanceof AppException) {
        AppException appException = (AppException) e;
        builder.serviceCode(appException.getServiceCode());
      }

    } else {
      log.error("处理请求：【{}】/【method: {}】, 出错：【{}】，返回应答：【{}】",
          request.getRequestURI(),
          request.getMethod(),
          ExceptionUtils.getStackTrace(e),
          httpStatus
      );
    }
    return new ResponseEntity(builder.build(), httpStatus);
  }

}
