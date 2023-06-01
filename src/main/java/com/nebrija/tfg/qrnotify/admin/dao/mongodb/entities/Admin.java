package com.nebrija.tfg.qrnotify.admin.dao.mongodb.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@Document(collection = "admins")
public class Admin extends AuditableEntityListener {

    @MongoId
    private ObjectId id;

    @Field("name")
    private String name;

    @Field("email")
    private String email;

    @Field("password")
    private String password;

    @Field("permissions")
    private List<Permission> permissions;
}
