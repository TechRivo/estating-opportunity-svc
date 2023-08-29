package com.estating.opportunity.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "estating.api")
public record EstatingApiProperties(String url, String username, String password) {
}
