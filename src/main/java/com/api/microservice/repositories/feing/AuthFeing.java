package com.api.microservice.repositories.feing;

import com.api.microservice.dtos.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "authFeing", url ="http://localhost:8081" )
public interface AuthFeing {

    @PostMapping(value = "/auth")
    Object authUser(@RequestBody UserDto userDto);

    @GetMapping(value = "/auth/validatetoken")
    boolean validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION)String token);

    @GetMapping(value = "/auth/typeuser")
    String getTypeUser(@RequestHeader(HttpHeaders.AUTHORIZATION)String token);
}
