package com.api.microservice.repositories.repositoryDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String type;
    private String token;
}
