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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO REPLACE_ME 示例服务
 */
@RestController
@ResponseBody
@RequestMapping("/v1/samples")
@Api(description = "Sample管理")
public class SampleController {

  @Autowired
  private SampleService sampleService;


  @ApiOperation(value = "根据ID获取Sample")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "成功"),
      @ApiResponse(code = 400, message = "参数错误或业务异常"),
      @ApiResponse(code = 401, message = "认证失败"),
      @ApiResponse(code = 403, message = "访问拒绝"),
      @ApiResponse(code = 404, message = "资源不存在"),
      @ApiResponse(code = 405, message = "方发不支持"),
      @ApiResponse(code = 500, message = "系统内部错误")})
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public SampleVo getSample(@PathVariable Long id) {
    SampleDomain sample = sampleService.getSample(id);
    return sample != null ? new SampleVo(sample.getId(), sample.getName()) : null;
  }

  @ApiOperation(value = "创建Sample")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "成功"),
      @ApiResponse(code = 400, message = "参数错误或业务异常"),
      @ApiResponse(code = 401, message = "认证失败"),
      @ApiResponse(code = 403, message = "访问拒绝"),
      @ApiResponse(code = 404, message = "资源不存在"),
      @ApiResponse(code = 405, message = "方发不支持"),
      @ApiResponse(code = 500, message = "系统内部错误")})
  @RequestMapping(value = "", method = RequestMethod.POST)
  public Long createSample(@RequestBody CreateSampleReq createSampleReq) {
    return sampleService.createSample(new SampleDomain(createSampleReq.getName()));
  }

  @ApiOperation(value = "删除Sample")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "成功"),
      @ApiResponse(code = 400, message = "参数错误或业务异常"),
      @ApiResponse(code = 401, message = "认证失败"),
      @ApiResponse(code = 403, message = "访问拒绝"),
      @ApiResponse(code = 404, message = "资源不存在"),
      @ApiResponse(code = 405, message = "方发不支持"),
      @ApiResponse(code = 500, message = "系统内部错误")})
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteSample(@PathVariable Long id) {
    sampleService.deleteSample(id);
  }

}
