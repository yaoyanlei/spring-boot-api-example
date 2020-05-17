package com.apelab.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yaoyaolei on 2020/5/16 17:26
 */
@RestController
@Api(description = "国际化示例")
public class I18nSampleController {

  @Autowired
  private MessageSource messageSource;

  @ApiOperation(value = "国际化示例")
  @GetMapping(value = "/i18n")
  public String sample() {
    return messageSource.getMessage("key.hello",null, LocaleContextHolder.getLocale());
  }

}
