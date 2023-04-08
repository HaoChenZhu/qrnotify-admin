package com.nebrija.tfg.qrnotify.admin.dao.mongodb.repository;

import com.nebrija.tfg.qrnotify.admin.dao.mongodb.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository  {

    User findBy_id(String _id);

    User findByPhoneNumber(String phoneNumber);

    List<User> findAll();

    void save(User user);

    boolean existsByPhoneNumber(String phoneNumber);
}
