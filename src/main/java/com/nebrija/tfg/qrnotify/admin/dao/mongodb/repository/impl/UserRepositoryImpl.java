package com.nebrija.tfg.qrnotify.admin.dao.mongodb.repository.impl;

import com.nebrija.tfg.qrnotify.admin.dao.mongodb.entities.User;
import com.nebrija.tfg.qrnotify.admin.dao.mongodb.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public User findBy_id(String _id) {
        ObjectId id = new ObjectId();
        User user =mongoTemplate.findById(id, User.class);
        return user;
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        Query query = new Query(Criteria.where("phoneNumber").is(phoneNumber));
        User users = mongoTemplate.findOne(query, User.class);
        return users;
    }

    @Override
    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public void save(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        Query query = new Query(Criteria.where("phoneNumber").is(phoneNumber));
        User users = mongoTemplate.findOne(query, User.class);
        if (users != null) {
            return true;
        }
        return false;
    }
}
