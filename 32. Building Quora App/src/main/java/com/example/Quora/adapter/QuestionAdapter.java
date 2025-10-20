package com.example.Quora.adapter;

import com.example.Quora.dto.QuestionResponseDTO;
import com.example.Quora.models.Question;

public class QuestionAdapter {
    public static QuestionResponseDTO toquestionResponseDTO(Question question){
        return QuestionResponseDTO.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .build();
    }
}
