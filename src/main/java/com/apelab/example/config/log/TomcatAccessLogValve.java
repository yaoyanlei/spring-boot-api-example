package com.apelab.example.config.log;

import java.io.IOException;
import javax.servlet.ServletException;
import lombok.Setter;
import org.apache.catalina.AccessLog;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

/**
 * Created by yaoyaolei on 2020/5/16 16:25
 */
public class TomcatAccessLogValve  extends ValveBase implements AccessLog {

	private boolean requestAttributesEnabled = false;
	@Setter
	private boolean logResponseSize = true;

	public TomcatAccessLogValve() {
		super(true); // asyncSupported
	}


	@Override
	public void log(Request request, Response response, long time) {
		if (!getState().isAvailable()) {
			return;
		}
		AccessLogUtil.log(request, response, time, logResponseSize ? response.getBytesWritten(false)
				: -1);
	}

	@Override
	public void setRequestAttributesEnabled(boolean requestAttributesEnabled) {
		this.requestAttributesEnabled = requestAttributesEnabled;
	}

	@Override
	public boolean getRequestAttributesEnabled() {
		return this.requestAttributesEnabled;
	}

	@Override
	public void invoke(Request request, Response response) throws IOException, ServletException {
		getNext().invoke(request, response);
	}
}
