package com.springdemo.issuedashboard.issuedashboard.github;

import com.springdemo.issuedashboard.issuedashboard.GithubProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class GithubClient {

    private final String EVENT_ISSUES_URL = "https://api.github.com/repos/{owner}/{repo}/issues/events";

    private final RestTemplate restTemplate;

    public GithubClient(RestTemplateBuilder builder, GithubProperties properties) {
        this.restTemplate = builder
                .additionalInterceptors(new GithubTokenInterceptor(properties.getToken()))
                .build();
    }

    public ResponseEntity<RepositoryEvent[]> fetchEvents(String orgName, String repoName) {
        return restTemplate.getForEntity(EVENT_ISSUES_URL, RepositoryEvent[].class, orgName, repoName);
    }


    private static class GithubTokenInterceptor implements ClientHttpRequestInterceptor {

        private final String token;

        public GithubTokenInterceptor(String token) {
            this.token = token;
        }

        @Override
        public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
            if(StringUtils.hasText(this.token)) {
                byte [] basicAuthValue  = this.token.getBytes(StandardCharsets.UTF_8);
                httpRequest.getHeaders().set(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString(basicAuthValue));
            }
            return clientHttpRequestExecution.execute(httpRequest, bytes);
        }
    }
}
