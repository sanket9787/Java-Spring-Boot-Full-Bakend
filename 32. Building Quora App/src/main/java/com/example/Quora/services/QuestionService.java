package com.example.Quora.services;

import com.example.Quora.adapter.QuestionAdapter;
import com.example.Quora.dto.QuestionRequestDTO;
import com.example.Quora.dto.QuestionResponseDTO;
import com.example.Quora.exceptions.QuestionNotFoundException;
import com.example.Quora.models.Question;
import com.example.Quora.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
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

//    @Override
//    public Mono<QuestionResponseDTO> getQuestionById(String id) {
//        Mono<Question> questionMono = questionRepository.findById(id);
//
//        Mono<Question> checkedMono = questionMono.switchIfEmpty(
//                Mono.error(new QuestionNotFoundException("Question not found with ID: " + id))
//        );
//
//        Mono<QuestionResponseDTO> dtoMono = checkedMono.map(QuestionAdapter::toquestionResponseDTO);
//
//        Mono<QuestionResponseDTO> loggedMono = dtoMono
//                .doOnSuccess(response -> System.out.println("✅ Question found successfully: " + response))
//                .doOnError(error -> System.out.println("❌ Error getting question: " + error.getMessage()));
//
//        return loggedMono;
//    }

    @Override
    public Mono<QuestionResponseDTO> getQuestionById(String id) {
        return questionRepository.findById(id)
                .switchIfEmpty(Mono.error(new QuestionNotFoundException("Question not found with ID: " + id)))
                .map(QuestionAdapter::toquestionResponseDTO)
                .doOnSuccess(response -> System.out.println("✅ Question found successfully: " + response))
                .doOnError(error -> System.out.println("❌ Error getting question: " + error.getMessage()));
    }

    @Override
    public Flux<QuestionResponseDTO> getAllQuestions() {
        return questionRepository.findAll()
                .map(QuestionAdapter::toquestionResponseDTO);
    }

    @Override
    public Mono<QuestionResponseDTO> deleteQuestionById(String id) {
        Mono<Question> questionMono = questionRepository.findById(id).switchIfEmpty(
                Mono.error(new QuestionNotFoundException("Question not found with id:" + id))
        );
        return questionMono.flatMap(question -> questionRepository.delete(question)
                .then(Mono.just(QuestionAdapter.toquestionResponseDTO(question))))
                .doOnSuccess(response -> System.out.println("Deleted successfully" + response))
                .doOnError(response -> System.out.println("Error deleting a question " + response.getMessage()));
    }

    @Override
    public Flux<QuestionResponseDTO> searchQuestions(String query, int page, int size) {
        Flux<QuestionResponseDTO> resultFlux = questionRepository.findByTitleContainingIgnoreCase(query)
                .skip((long) page*size)
                .take(size)
                .map(QuestionAdapter::toquestionResponseDTO);

        return resultFlux.switchIfEmpty(Flux.error(new QuestionNotFoundException("Question not found with query " + query)))
                        .doOnComplete(() -> System.out.println("Completed fetching results"))
                        .doOnError(err -> System.out.println("Error occurred " + err.getMessage()));

    }
}
