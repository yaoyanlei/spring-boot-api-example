package com.apelab.example.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Created by yaoyaolei on 2020/5/16 17:26
 */
public class HandlerMethodInterceptorAdapter extends HandlerInterceptorAdapter {

  @Override
  public final boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler)
      throws Exception {
    if (isHandlerMethod(handler)) {
      return preMethodHandle(request, response, HandlerMethod.class.cast(handler));
    } else {
      return true;
    }
  }

  protected boolean preMethodHandle(HttpServletRequest request, HttpServletResponse response,
      HandlerMethod cast) {
    return true;
  }

  @Override
  public final void postHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler,
      @Nullable ModelAndView modelAndView) throws Exception {
    if (isHandlerMethod(handler)) {
      postMethodHandle(request, response, HandlerMethod.class.cast(handler), modelAndView);
    }
  }

  protected void postMethodHandle(HttpServletRequest request, HttpServletResponse response,
      HandlerMethod cast, ModelAndView modelAndView) {
  }

  @Override
  public final void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler,
      @Nullable Exception ex) throws Exception {
    if (isHandlerMethod(handler)) {
      afterMethodCompletion(request, response, HandlerMethod.class.cast(handler), ex);
    }
  }

  protected void afterMethodCompletion(HttpServletRequest request, HttpServletResponse response,
      HandlerMethod cast, Exception ex) {
  }

  @Override
  public final void afterConcurrentHandlingStarted(HttpServletRequest request,
      HttpServletResponse response,
      Object handler) throws Exception {
    if (isHandlerMethod(handler)) {
      afterMethodConcurrentHandlingStarted(request, response, HandlerMethod.class.cast(handler));
    }
  }

  protected void afterMethodConcurrentHandlingStarted(HttpServletRequest request,
      HttpServletResponse response, HandlerMethod cast) {
  }

  private boolean isHandlerMethod(Object handler) {
    boolean isHandlerMethod = true;
    try {
      HandlerMethod.class.cast(handler);
    } catch (ClassCastException e) {
      isHandlerMethod = false;
    }
    return isHandlerMethod;
  }
}
