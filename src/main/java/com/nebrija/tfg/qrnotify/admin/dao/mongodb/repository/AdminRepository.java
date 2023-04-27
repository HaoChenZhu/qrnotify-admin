package com.nebrija.tfg.qrnotify.admin.dao.mongodb.repository;

import com.nebrija.tfg.qrnotify.admin.dao.mongodb.entities.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AdminRepository {


    List<Admin> findAll();

    Admin findBy_id(String _id);

    Admin findByEmail(String email);

    void save(Admin admin);

    void delete(Admin admin);

    boolean existsByIdentifier(String identifier);
}
