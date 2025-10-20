package com.example.Quora.repositories;

import com.example.Quora.dto.QuestionResponseDTO;
import com.example.Quora.models.Question;
import org.reactivestreams.Publisher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question, String> {

    Flux<Question> findByTitleContainingIgnoreCase(String query);
}
