package com.apelab.example.service;


import com.apelab.example.bean.SampleVo;
import com.apelab.example.repository.domain.SampleDomain;

/**
 * TODO REPLACE_ME 示例服务
 */
public interface SampleService {

  SampleVo getSample(Long id) ;

  Long createSample(SampleDomain sample);

  void deleteSample(Long id) ;

}
