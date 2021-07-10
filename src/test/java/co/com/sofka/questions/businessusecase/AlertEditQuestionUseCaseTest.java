package co.com.sofka.questions.businessusecase;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.model.ResponseDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class AlertEditQuestionUseCaseTest {

    @MockBean
    private AnswerRepository answerRepository;
    @MockBean
    private QuestionRepository questionRepository;
    @SpyBean
    private AlertEditQuestionUseCase alertEditQuestionUseCase;


    @Test
    @DisplayName("Test enviar alerta si la pregunta no tiene respuestas asociadas")
    public void alertEditQuestionTest(){
        var response = new ResponseDTO("Se puede modificar la pregunta", true);
        var question = new Answer();

        Mockito.when(answerRepository.findAllByQuestionId(Mockito.any(String.class))).thenReturn(Flux.just(question));

        var res = alertEditQuestionUseCase.throwAlert("15");

        Assertions.assertEquals(res.block().getMensaje(), response.getMensaje());
        Assertions.assertEquals(res.block().getEstado(), response.getEstado());
    }

}