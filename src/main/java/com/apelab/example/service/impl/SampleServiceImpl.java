package com.apelab.example.service.impl;

import com.apelab.example.repository.domain.SampleDomain;
import com.apelab.example.service.SampleService;
import java.util.concurrent.atomic.LongAdder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by yaoyaolei on 2020/5/16 15:48
 */
@Service
@Slf4j
public class SampleServiceImpl  implements SampleService {

	private LongAdder longAdder = new LongAdder();

	@Override
	public SampleDomain getSample(Long id) {
		return new SampleDomain("Name" + id);
	}

	@Override
	public Long createSample(SampleDomain sample) {
		longAdder.increment();
		Long id = longAdder.longValue();
		log.info("Create sample, new id = {}", id);
		return id;
	}

	@Override
	public void deleteSample(Long id) {
		log.info("Delete sample by id = {}", id);
	}

}
