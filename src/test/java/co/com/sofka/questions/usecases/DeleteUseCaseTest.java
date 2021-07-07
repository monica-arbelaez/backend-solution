package co.com.sofka.questions.usecases;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import co.com.sofka.questions.usecase.CreateUseCase;
import co.com.sofka.questions.usecase.DeleteUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeleteUseCaseTest {

    @SpyBean
    private DeleteUseCase deleteUseCase;
    @MockBean
    private QuestionRepository questionRepository;
    @MockBean
    private AnswerRepository answerRepository;

    @Test
    public void deleteQuestion(){

        var questio = new QuestionDTO("14", "1", "quien soy", "OPEN", "DDDD");
        var respuesta = new AnswerDTO();
        respuesta.setQuestionId("14");
        respuesta.setUserId("1");
        respuesta.setAnswer("una mujer");

        Mockito.when(questionRepository.deleteById("14")).thenReturn(Mono.empty());
        Mockito.when(answerRepository.deleteByQuestionId("14")).thenReturn(Mono.empty());

        var datosRespuesta = deleteUseCase.deleteByquestionId("14").block();

        Assertions.assertEquals(datosRespuesta, null);


    }



}