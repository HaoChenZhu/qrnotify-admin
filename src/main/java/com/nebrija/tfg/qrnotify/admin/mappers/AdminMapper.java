package com.nebrija.tfg.qrnotify.admin.mappers;

import com.nebrija.tfg.qrnotify.admin.dao.mongodb.entities.Admin;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiAdminRequestDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiAdminResponseDto;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    default String objectIdToString(ObjectId objectId) {
        return objectId != null ? objectId.toString() : null;
    }

    @Mapping(expression = "java(objectIdToString(admin.getId()))", target = "id")
    ApiAdminResponseDto toDto(Admin admin);

    Admin toEntity(ApiAdminRequestDto apiAdminRequestDto);

    List<ApiAdminResponseDto> fromEntitiesToDtos(List<Admin> admins);
}
