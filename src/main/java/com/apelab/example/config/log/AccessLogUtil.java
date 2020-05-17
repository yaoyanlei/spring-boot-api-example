package com.apelab.example.config.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yaoyaolei on 2020/5/16 16:26
 */
public final class AccessLogUtil {


	private AccessLogUtil() {
		// hide it
	}

	private static final DateTimeFormatter DATETIME_FORMATTER =
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static void log(HttpServletRequest request, HttpServletResponse response, long time,
			long bytes) {
		AccessLogger logger = AccessLoggerInstanceHolder.getSingletion();
		if ("nop".equals(logger.name())) {
			return; // just return for NopAccessLogger
		}
		// [datetime] [thread] [remoteAddress] remoteAppId method uri status time traceId protocol
		// "referer" "userAgent" bytes
		StringBuilder sb = new StringBuilder();
		sb.append('[').append(DATETIME_FORMATTER.format(LocalDateTime.now())).append(']')
				.append(' ').append('[').append(Thread.currentThread().getName()).append(']')
				.append(' ').append('[').append(getRemoteAddr(request)).append(']')
				.append(' ').append(getHeader(request, "X-apelab-App-Id"))
				.append(' ').append(request.getMethod())
				.append(' ').append(request.getRequestURI())
				.append(' ').append(response.getStatus())
				.append(' ').append(time)
				.append(' ').append(getHeader(request, "X-apelab-TraceId"))
				.append(' ').append(request.getProtocol())
				.append(' ').append('"').append(getHeader(request, "Referer")).append('"')
				.append(' ').append('"').append(getHeader(request, "User-Agent")).append('"')
				.append(' ').append(bytes > -1 ? String.valueOf(bytes) : "-");
		logger.log(sb.toString());
	}

	public static String getHeader(HttpServletRequest request, String name) {
		String header = request.getHeader(name);
		return header == null ? "-" : header;
	}

	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = request.getHeader("X-Forwarded-For");
		if (remoteAddr != null && !"".equals(remoteAddr)) {
			return remoteAddr;
		}
		remoteAddr = request.getRemoteAddr();
		return remoteAddr == null ? "-" : remoteAddr;
	}

}
