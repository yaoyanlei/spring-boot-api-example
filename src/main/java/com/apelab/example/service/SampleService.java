package com.apelab.example.service;


import com.apelab.example.repository.domain.SampleDomain;

/**
 * TODO REPLACE_ME 示例服务
 */
public interface SampleService {

  public SampleDomain getSample(Long id) ;

  public Long createSample(SampleDomain sample);

  public void deleteSample(Long id) ;

}
