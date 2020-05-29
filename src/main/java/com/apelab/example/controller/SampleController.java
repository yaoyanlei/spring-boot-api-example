package com.apelab.example.controller;

import com.apelab.example.bean.CreateSampleReq;
import com.apelab.example.bean.SampleVo;
import com.apelab.example.repository.domain.SampleDomain;
import com.apelab.example.service.SampleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO REPLACE_ME 示例服务
 */
@RestController
@RequestMapping("/v1/samples")
@Api(description = "Sample管理")
public class SampleController {

  @Autowired
  private SampleService sampleService;

  @ApiOperation(value = "根据ID获取Sample")
  @GetMapping("/{id}")
  public SampleVo getSample(@PathVariable Long id) {
    return sampleService.getSample(id);
  }

  @ApiOperation(value = "创建Sample")
  @PostMapping
  public Long createSample(@RequestBody CreateSampleReq createSampleReq) {
    return sampleService.createSample(new SampleDomain(createSampleReq.getName()));
  }

  @ApiOperation(value = "删除Sample")
  @DeleteMapping("/{id}")
  public void deleteSample(@PathVariable Long id) {
    sampleService.deleteSample(id);
  }
}
