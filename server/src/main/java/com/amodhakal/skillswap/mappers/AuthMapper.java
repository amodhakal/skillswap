package com.amodhakal.skillswap.mappers;

import com.amodhakal.skillswap.dto.AuthDto;
import com.amodhakal.skillswap.entities.AuthEntity;

public class AuthMapper {
    public AuthEntity mapToEntity(AuthDto authDto) {
        return new AuthEntity(authDto.getId(), authDto.getName(), authDto.getEmail(), authDto.getPassword(),
                authDto.getCreatedAt());
    }

    public AuthDto mapToDto(AuthEntity authEntity) {
        return new AuthDto(authEntity.getId(), authEntity.getName(), authEntity.getEmail(), authEntity.getPassword(),
                authEntity.getCreatedAt());
    }
}
