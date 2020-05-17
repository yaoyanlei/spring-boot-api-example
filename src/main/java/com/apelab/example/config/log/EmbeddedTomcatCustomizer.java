package com.apelab.example.config.log;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;

/**
 * Created by yaoyaolei on 2020/5/16 16:18
 */
public class EmbeddedTomcatCustomizer  implements
		WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

	private final boolean logResponseSize;

	public EmbeddedTomcatCustomizer(boolean logResponseSize) {
		this.logResponseSize = logResponseSize;
		// update access logger
		AccessLoggerInstanceHolder.updateAccessLogger(new Slf4jAccessLogger());
	}

	@Override
	public void customize(ConfigurableServletWebServerFactory container) {
		TomcatServletWebServerFactory c = (TomcatServletWebServerFactory) container;
		TomcatAccessLogValve valve = new TomcatAccessLogValve();
		valve.setLogResponseSize(logResponseSize);
		c.addEngineValves(valve);
	}

}
