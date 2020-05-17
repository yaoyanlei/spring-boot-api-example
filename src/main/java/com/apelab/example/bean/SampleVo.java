package com.apelab.example.bean;

/**
 * TODO REPLACE_ME 示例服务
 */
public class SampleVo {
  private Long id;
  private String name;

  public SampleVo() {
  }

  public SampleVo(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
