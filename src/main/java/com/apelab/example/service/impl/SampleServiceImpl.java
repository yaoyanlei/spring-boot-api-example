package com.apelab.example.service.impl;

import com.apelab.example.bean.SampleVo;
import com.apelab.example.repository.SampleRepository;
import com.apelab.example.repository.domain.SampleDomain;
import com.apelab.example.service.SampleService;

import java.util.concurrent.atomic.LongAdder;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yaoyaolei on 2020/5/16 15:48
 */
@Service
@Slf4j
public class SampleServiceImpl implements SampleService {

  @Autowired
  private SampleRepository sampleRepository;

  @Override
  public SampleVo getSample(Long id) {
    SampleDomain sample = sampleRepository.getSample(id);
    return sample != null ? new SampleVo(sample.getId(), sample.getName()) : null;
  }

  @Override
  public Long createSample(SampleDomain sample) {
    return 1L;
  }

  @Override
  public void deleteSample(Long id) {
    log.info("Delete sample by id = {}", id);
  }
}
