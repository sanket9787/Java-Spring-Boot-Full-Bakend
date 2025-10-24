package com.example.Quora.services;

import com.example.Quora.dto.AnswerRequestDTO;
import com.example.Quora.dto.AnswerResponseDTO;
import com.example.Quora.models.Answer;
import reactor.core.publisher.Mono;

public interface   IAnswerService {
    public Mono<AnswerResponseDTO> createAnswer(AnswerRequestDTO answerRequestDTO);
    public Mono<Answer> getAnswerById(String id);
}
