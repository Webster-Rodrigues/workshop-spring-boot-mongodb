package com.websterProjects.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.websterProjects.workshopmongo.domain.User;

public interface UserRepository extends MongoRepository<User, String>{

}
