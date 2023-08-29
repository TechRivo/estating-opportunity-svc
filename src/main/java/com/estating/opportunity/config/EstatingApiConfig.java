package com.estating.opportunity.config;

import com.estating.opportunity.dto.AdminLoginRequest;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class EstatingApiConfig {

    private static final String LOGIN_URL = "/api/v1/admin/login";
    private static final String AUTH_HEADER = "X-Auth";

    @Bean
    public WebClient authWebClient(EstatingApiProperties props) {
        return WebClient.builder()
                .baseUrl(props.url() + LOGIN_URL)
                .build();
    }

    @Bean
    public RequestInterceptor authRequestInterceptor(WebClient authWebClient, EstatingApiProperties props) {
        var body = new AdminLoginRequest(props.username(), props.password());

        return template -> {
            var jwt = authWebClient.post()
                    .body(BodyInserters.fromValue(body))
                    .retrieve()
                    .toBodilessEntity()
                    .map(resp -> resp.getHeaders().get(AUTH_HEADER).get(0))
                    .block();
            template.header(AUTH_HEADER, jwt);
        };
    }

}
