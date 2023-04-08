package com.nebrija.tfg.qrnotify.admin.mappers;

import com.nebrija.tfg.qrnotify.admin.dao.mongodb.entities.User;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiUserRequestDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiUserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    ApiUserResponseDto toDto(User user);
    User toEntity(ApiUserRequestDto apiUserRequestDto);
    List<ApiUserResponseDto> fromEntitiesToDtos(List<User> users);

}
