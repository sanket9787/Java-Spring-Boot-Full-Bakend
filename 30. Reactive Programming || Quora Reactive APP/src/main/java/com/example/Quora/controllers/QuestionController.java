package com.example.Quora.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @@GetMapping("/autohr/{authorId}")
    public Flux<QuestionResponse> getQuestionsByAuthor(){

    }
}
