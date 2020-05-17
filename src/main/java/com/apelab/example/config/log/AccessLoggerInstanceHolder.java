package com.apelab.example.config.log;

/**
 * Created by yaoyaolei on 2020/5/16 16:21
 */
public final class AccessLoggerInstanceHolder {

	private static volatile AccessLogger instance = new NopAccessLogger();

	public static void updateAccessLogger(AccessLogger accessLogger) {
		synchronized (AccessLoggerInstanceHolder.class) {
			instance = accessLogger;
			System.out
					.println(String.format("Bind access logger with %s", accessLogger.getClass().getName()));
		}

	}

	public static AccessLogger getSingletion() {
		return instance;
	}

}
