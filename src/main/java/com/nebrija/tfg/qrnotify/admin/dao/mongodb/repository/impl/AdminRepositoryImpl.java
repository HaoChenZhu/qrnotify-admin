package com.nebrija.tfg.qrnotify.admin.dao.mongodb.repository.impl;

import com.nebrija.tfg.qrnotify.admin.dao.mongodb.entities.Admin;
import com.nebrija.tfg.qrnotify.admin.dao.mongodb.repository.AdminRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public List<Admin> findAll() {
        return mongoTemplate.findAll(Admin.class);
    }

    @Override
    public Admin findBy_id(String _id) {
        ObjectId id = new ObjectId(_id);
        Admin admin = mongoTemplate.findById(id, Admin.class);
        return admin;
    }

    @Override
    public Admin findByEmail(String email) {
        Query query = new Query(Criteria.where("email").is(email));
        Admin admin = mongoTemplate.findOne(query, Admin.class);
        return admin;
    }

    @Override
    public void save(Admin admin) {
        mongoTemplate.save(admin);
    }

    @Override
    public void delete(Admin admin) {
        mongoTemplate.remove(admin);
    }

    @Override
    public boolean existsByIdentifier(String identifier) {
        ObjectId id = new ObjectId(identifier);
        Query query = new Query(Criteria.where("id").is(id));
        Boolean exists = mongoTemplate.exists(query, Admin.class);
        return exists;
    }


}
