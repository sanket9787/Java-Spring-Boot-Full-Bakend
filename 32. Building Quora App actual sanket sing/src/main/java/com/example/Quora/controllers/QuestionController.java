package com.example.Quora.controllers;

import com.example.Quora.dto.QuestionRequestDTO;
import com.example.Quora.dto.QuestionResponseDTO;
import com.example.Quora.models.Question;
import com.example.Quora.repositories.QuestionRepository;
import com.example.Quora.services.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {

    private final IQuestionService questionService;

    @PostMapping
    public Mono<QuestionResponseDTO> createQuestion(@RequestBody QuestionRequestDTO questionRequestDTO){
        return questionService.createQuestion(questionRequestDTO)
                .doOnSuccess(response -> System.out.println("Question created successfully" + response))
                .doOnError(error -> System.out.println("Error creating question" + error));

    }

    @GetMapping("/{id}")
    public Mono<QuestionResponseDTO> getQuestionById(@PathVariable String id){
        return questionService.getQuestionById(id)
                .doOnSuccess(response -> System.out.println("Got Question By id Successfully" + response))
                .doOnError(error -> System.out.println("Error getting Question by id" + error));

    }

    @GetMapping()
    public Flux<QuestionResponseDTO> getAllQuestions(
            @RequestParam(required = false) String cursor,
            @RequestParam(defaultValue = "10") int size
    ){
        return questionService.getAllQuestions(cursor, size);
    }
//
//    @DeleteMapping("/{id}")
//    public Mono<void> deleteQuestionById(@PathVariable String id){
//        throw new UnsupportedOperationException("Not implemented");
//    }

    @GetMapping("/search")
    public Flux<QuestionResponseDTO> searchQuestion(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return questionService.searchQuestions(query, page, size);
    }
//
//    @GetMapping("/tag/{tag}")
//    public Flux<QuestionResponseDTO> getQuestionByTag(@PathVariable String tag,
//      @RequestParam(defaultValue = "0") int page,
//      @RequestParam(defaultValue = "10") int size
//    ){
//        throw new UnsupportedOperationException("Not implemented");
//    }

}
