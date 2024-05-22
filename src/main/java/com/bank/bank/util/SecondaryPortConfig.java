package com.bank.bank.util;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecondaryPortConfig {

        @Value("${port.secondary1}")
        private int secondaryPort1;

        @Bean
        public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainer() {
                return server -> {
                        Connector additionalConnector = new Connector(Http11NioProtocol.class.getName());
                        additionalConnector.setPort(secondaryPort1);
                        server.addAdditionalTomcatConnectors(additionalConnector);
                };
        }
}
