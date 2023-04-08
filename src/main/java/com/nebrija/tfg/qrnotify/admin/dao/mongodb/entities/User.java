package com.nebrija.tfg.qrnotify.admin.dao.mongodb.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@SuperBuilder
@NoArgsConstructor
@Document(collection = "users")
public class User extends AuditableEntityListener{

    @MongoId
    private ObjectId id;

    @Field("name")
    private String name;

    @Field("phone_number")
    private String phoneNumber;

    @Field("confirmation_code")
    private String confirmationCode;
}
