package com.example.Quora.services;

import com.example.Quora.adapter.QuestionAdapter;
import com.example.Quora.dto.QuestionRequestDTO;
import com.example.Quora.dto.QuestionResponseDTO;
import com.example.Quora.models.Question;
import com.example.Quora.repositories.QuestionRepository;
import com.example.Quora.utils.CursorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor //contruction based injection
public class QuestionService implements IQuestionService{

    private final QuestionRepository questionRepository;

    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO) {

        Question question = Question.builder()
                .title(questionRequestDTO.getTitle())
                .content(questionRequestDTO.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // Mono<Question> questionMono = questionRepository.save(question);
       // Mono<QuestionResponseDTO> questionResponseDTOMono = questionMono.map(QuestionAdapter::toquestionResponseDTO);

        return questionRepository.save(question)
        .map(QuestionAdapter:: toquestionResponseDTO)
                .doOnSuccess(response -> System.out.println("Question crated successfully:" + response))
                .doOnError(error -> System.out.println("Error creating quesiton: " + error));

    }

    @Override
    public Mono<QuestionResponseDTO> getQuestionById(String id) {
        Mono<Question> questionMono = questionRepository.findById(id);
        Mono<QuestionResponseDTO> questionResponseDTOMono = questionMono.map(QuestionAdapter::toquestionResponseDTO);
        return questionResponseDTOMono;

    }

    @Override
    public Flux<QuestionResponseDTO> searchQuestions(String searchTerm, int offset, int size) {

        return questionRepository.findByTitleOrContentContainingIgnoreCase(searchTerm, PageRequest.of(offset,size))
                .map(QuestionAdapter::toquestionResponseDTO)
                .doOnError(error -> System.out.println("Error searching questions: " + error))
                .doOnComplete(() -> System.out.println("Question serached successfully "));
    }

    @Override
    public Flux<QuestionResponseDTO> getAllQuestions(String cursor, int size) {
        Pageable pageable = PageRequest.of(0, size);

        if(!CursorUtils.isValidCursor(cursor)){
            return questionRepository.findTop10ByOrderByCreatedAtAsc()
                    .take(size)
                    .map(QuestionAdapter::toquestionResponseDTO)
                    .doOnComplete(() -> System.out.println("Question fetched successfully"))
                    .doOnError(error -> System.out.println("Error fetching questions: " + error));
        } else{
            LocalDateTime cursorTimeStamp = CursorUtils.parseCursor(cursor);
            //here this is similar to this sql command
            // SELECT * FROM TABLE WHERE created_at > cursor limit 10 ORDER BY created_at ASC;
            return questionRepository.findByCreatedAtGreaterThanOrderByCreatedAtAsc(cursorTimeStamp, pageable)
                    .map(QuestionAdapter::toquestionResponseDTO)
                    .doOnError(error -> System.out.println("Error fetching questions: " + error))
                    .doOnComplete(() -> System.out.println("Question fetched successfully"));
        }

    }

}
