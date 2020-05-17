package com.apelab.example.config.log;

/**
 * Created by yaoyaolei on 2020/5/16 16:23
 */
public class NopAccessLogger implements AccessLogger{

	@Override
	public String name() {
		return "nop";
	}

	@Override
	public void log(String info) {
		// nothing to do
	}

}
