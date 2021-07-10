package co.com.sofka.questions.businessusecase;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ListRecommendedQuestionsUseCaseTest {

    @MockBean
    private QuestionRepository questionRepository;

    @SpyBean
    private ListRecommendedQuestionsUseCase listRecommendedQuestionsUseCase;

    @Test
    @DisplayName("Listar recomendacion de preguntas")
    public void listrecommendedQuestions(){

        var questioDTO = new QuestionDTO("15", "1", "quien soy", "OPEN", "DDDD");
        var question = new Question();
        question.setId("15");
        question.setUserId("1");
        question.setQuestion("quien soy yo");
        question.setType("OPEN");
        question.setCategory("DDDD");

        Mockito.when(questionRepository.findByquestionLike(Mockito.any(String.class))).thenReturn(Flux.just(question));
        var resultado = listRecommendedQuestionsUseCase.seek("quien");

        Assertions.assertEquals(resultado.blockFirst().getId(),"15");
        Assertions.assertEquals(resultado.blockFirst().getQuestion(),"quien soy yo");
        Assertions.assertEquals(resultado.blockFirst().getType(),"OPEN");
    }



}