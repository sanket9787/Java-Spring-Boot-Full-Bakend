package com.example.Quora.services;

import com.example.Quora.dto.QuestionRequestDTO;
import com.example.Quora.dto.QuestionResponseDTO;
import com.example.Quora.models.Question;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionService {
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO);

    public Flux<QuestionResponseDTO> searchQuestions(String searchTerm, int offset, int size);

    public Flux<QuestionResponseDTO> getAllQuestions(String cursor, int size);

    //whenever somebody is invoked this we will update the corresponding view count  asynchronously
    public Mono<QuestionResponseDTO> getQuestionById(String id);
}
