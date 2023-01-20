package com.api.microservice.repositories.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthClient {

    String URI = "http://localhost:8081/auth/";
    RestTemplate restTemplate = new RestTemplate();

    public boolean validToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        HttpEntity<Object> request = new HttpEntity<Object>(headers);

        var response = restTemplate.exchange(URI + "validatetoken", HttpMethod.GET, request, Boolean.class);

        return response.getBody();
    }

    public String getTypeUser(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        HttpEntity<Object> request = new HttpEntity<Object>(headers);

        var response = restTemplate.exchange(URI + "typeuser", HttpMethod.GET, request, String.class);

        return response.getBody();
    }
}
