package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import co.com.sofka.questions.usecase.UpdateUseCase;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UpdateUseCaseTest {

    @MockBean
    private QuestionRepository questionRepository;
    @SpyBean
    private UpdateUseCase updateUseCase;

    @Test
    public  void editarQuestionTest(){

        var question = new QuestionDTO("14", "1", "quien soy", "OPEN", "DDDD");
        var questionResponse = new Question();
        questionResponse.setId("14");
        questionResponse.setUserId("1");
        questionResponse.setType("OPEN");
        questionResponse.setCategory("DDDD");

        Mockito.when(questionRepository.save(Mockito.any(Question.class))).thenReturn(Mono.just(questionResponse));

        var spy = updateUseCase.modificarQuestion(question);

        Assertions.assertEquals(spy.block().getQuestion(),"esta es una nueva respuesta");
    }


}