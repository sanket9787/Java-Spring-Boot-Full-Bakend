package com.example.Quora.repositories;

import com.example.Quora.models.Answer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AnswerRepository extends ReactiveMongoRepository<Answer, String> {



}
