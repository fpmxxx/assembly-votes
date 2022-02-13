package com.assemblyvotes.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Classe para mapeamento de parametros no arquivo application.properties
 */
@Configuration
@ConfigurationProperties(prefix = "config")
public class ConfigProperties {

	private Integer timeSession;

	public Integer getTimeSession() {
		return timeSession;
	}

	public void setTimeSession(Integer timeSession) {
		this.timeSession = timeSession;
	}
	
}
