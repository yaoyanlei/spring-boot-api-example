package com.apelab.example.config.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yaoyaolei on 2020/5/16 16:23
 */
public class Slf4jAccessLogger implements AccessLogger{

	private static final Logger LOGGER = LoggerFactory.getLogger("api.accesslog");

	@Override
	public String name() {
		return "slf4j";
	}

	@Override
	public void log(String info) {
		LOGGER.info(info);
	}
}
