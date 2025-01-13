package com.websterProjects.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.websterProjects.workshopmongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String>{

}
