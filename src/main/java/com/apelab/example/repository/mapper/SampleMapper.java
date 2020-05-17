package com.apelab.example.repository.mapper;

import com.apelab.example.bean.SampleVo;
import com.apelab.example.bean.SearchSampleReq;
import com.apelab.example.repository.domain.SampleDomain;
import com.apelab.example.repository.mapper.provider.SampleProvider;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * Created by yaoyaolei on 2020/5/16 17:26
 */
public interface SampleMapper {

  void updateSampleById(SampleDomain channelDomain);

  SampleDomain selectSample(@Param("id") Long id);

  Integer insertSample(SampleDomain sampleDomain);

  @SelectProvider(type = SampleProvider.class, method = "selectSamples")
  @ResultMap("channelVo")
  PageList<SampleVo> selectChannels(SearchSampleReq searchSampleReq, PageBounds bounds);
}
