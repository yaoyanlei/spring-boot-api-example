package com.apelab.example.repository;

import com.apelab.example.bean.SampleVo;
import com.apelab.example.bean.SearchSampleReq;
import com.apelab.example.repository.domain.SampleDomain;
import com.apelab.example.repository.mapper.SampleMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by yaoyaolei on 2020/2/12 15:44
 */
@Repository
public class SampleRepository {

  @Autowired
  private SampleMapper sampleMapper;

  public Integer addSample(SampleDomain sampleDomain) {
    return sampleMapper.insertSample(sampleDomain);
  }

  public SampleDomain getSample(final Long channelId) {
    return sampleMapper.selectSample(channelId);
  }

  public void updateSample(final SampleDomain channelDomain) {
    sampleMapper.updateSampleById(channelDomain);
  }

  public PageList<SampleVo> getChannels(final SearchSampleReq searchSampleReq) {
    final PageBounds bounds = new PageBounds(searchSampleReq.getPageNo(), searchSampleReq.getPageSize(), null, true);
    return sampleMapper.selectChannels(searchSampleReq, bounds);
  }
}
