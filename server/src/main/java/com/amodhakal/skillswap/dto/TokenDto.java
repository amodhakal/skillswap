package com.amodhakal.skillswap.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class TokenDto {
    @Getter
    @Setter
    private String token;

    @Getter
    @Setter
    private Long expirationDate;
}
