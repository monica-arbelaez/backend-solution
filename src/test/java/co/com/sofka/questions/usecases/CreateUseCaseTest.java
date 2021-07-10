package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import co.com.sofka.questions.usecase.CreateUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
class CreateUseCaseTest {

    @SpyBean
    private CreateUseCase createUseCase;
    @MockBean
    private QuestionRepository questionRepository;

    @Test
    @DisplayName("crear preguntas CRUD use case")
    public void createQuestion(){

        var QuestioDTO = new QuestionDTO("14", "1", "quien soy", "OPEN", "DDDD");
        var question = new Question();
        question.setId("14");
        question.setUserId("1");
        question.setType("OPEN");
        question.setCategory("DDDD");

//        when(questionRepository.save(question)).thenReturn(Mono.just(question)); //va a retornar question
        when(questionRepository.save(Mockito.any(Question.class))).thenReturn(Mono.just(question));

        var respuesta = createUseCase.insertar(QuestioDTO);

        Assertions.assertEquals(respuesta.block().getId(),"14");
    }

}