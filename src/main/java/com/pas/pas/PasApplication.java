package com.pas.pas;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasApplication.class, args);
	}

	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection securityCollection = new SecurityCollection();
				securityCollection.addPattern("/*");
				securityConstraint.addCollection(securityCollection);
				context.addConstraint(securityConstraint);
			}
		};

		tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());

		return tomcat;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	private Connector httpToHttpsRedirectConnector() {
		Connector connector = new Connector((TomcatServletWebServerFactory.DEFAULT_PROTOCOL));
		connector.setScheme("http");
		connector.setPort(3000);
		connector.setSecure(false);
		connector.setRedirectPort(3443);
		return connector;
	}

}
