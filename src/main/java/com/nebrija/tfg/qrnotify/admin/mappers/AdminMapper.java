package com.nebrija.tfg.qrnotify.admin.mappers;

import com.nebrija.tfg.qrnotify.admin.dao.mongodb.entities.Admin;
import com.nebrija.tfg.qrnotify.admin.dao.mongodb.entities.Permission;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiAdminRequestDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiAdminResponseDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiPermissionRequestDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiPermissionResponseDto;
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

    @Mapping(source = "topicId", target = "topic_id")
    Permission toPermissionEntity(ApiPermissionRequestDto permission);
    @Mapping(source = "topic_id", target = "topicId")
    ApiPermissionResponseDto toPermissionDto(Permission permission);
}
