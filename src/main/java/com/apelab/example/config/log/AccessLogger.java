package com.apelab.example.config.log;

/**
 * Created by yaoyaolei on 2020/5/16 16:22
 */
public interface AccessLogger {

	/**
	 * @return log name
	 */
	String name();

	/**
	 * log info
	 */
	void log(String info);

}
