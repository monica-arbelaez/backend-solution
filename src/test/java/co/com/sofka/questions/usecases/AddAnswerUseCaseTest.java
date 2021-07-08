package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.usecase.AddAnswerUseCase;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AddAnswerUseCaseTest {

    @MockBean
    private AnswerRepository answerRepository;

    @SpyBean
    private AddAnswerUseCase addAnswerUseCase;


    @Test
    public void setAddAnswerUseCaseTest(){
        var answerDTO = new  AnswerDTO("2", "11", "Medellin");
        var answer = new Answer();
        answer.setQuestionId("1");
        answer.setAnswer("Medellin");
        answer.setUserId("11");

        Mockito.when(answerRepository.save(Mockito.any(Answer.class))).thenReturn(Mono.just(answer));

        var  datoAnswer = addAnswerUseCase.addAnswer(answerDTO).block();

        Assertions.assertEquals(datoAnswer.getAnswer(), "Medellin");
    }
}