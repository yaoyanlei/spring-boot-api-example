package com.apelab.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yaoyaolei on 2020/5/16 17:26
 */
@RestController
@Api(description = "健康检测")
public class HealthController {

  @ApiOperation(value = "健康检测")
  @GetMapping(value = "/healthcheck")
  public String healthCheck() {
    return "SUCCESS";
  }
}
