package com.example.Quora.services;

import com.example.Quora.dto.QuestionRequestDTO;
import com.example.Quora.dto.QuestionResponseDTO;
import com.example.Quora.models.Question;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionService {
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO);

    public Mono<QuestionResponseDTO> getQuestionById(String id);

    public Flux<QuestionResponseDTO> getAllQuestions();

    public Mono<QuestionResponseDTO> deleteQuestionById(String id);

    Flux<QuestionResponseDTO> searchQuestions(String query, int page, int size);
}
