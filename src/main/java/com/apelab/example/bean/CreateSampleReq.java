package com.apelab.example.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TODO REPLACE_ME 示例服务
 */
//@Immutable
public class CreateSampleReq {
  @JsonProperty("name")
  private String name;

  @JsonCreator
  public CreateSampleReq(@JsonProperty(value = "name", required = true) String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
