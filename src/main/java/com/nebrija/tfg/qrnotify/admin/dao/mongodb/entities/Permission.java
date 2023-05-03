package com.nebrija.tfg.qrnotify.admin.dao.mongodb.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
public class Permission{

    @Field("topic_id")
    private String topic_id;
    @Field("active")
    private Boolean active;
}
