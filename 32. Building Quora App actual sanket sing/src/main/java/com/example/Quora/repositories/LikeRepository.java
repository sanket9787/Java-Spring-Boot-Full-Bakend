package com.example.Quora.repositories;

import com.example.Quora.models.Like;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LikeRepository extends ReactiveMongoRepository<Like, String> {

}
