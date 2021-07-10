package co.com.sofka.questions.router;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecase.UpdateUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import org.assertj.core.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ModificarQuestionRouter.class})
class ModificarQuestionRouterTest {
    @MockBean
    private UpdateUseCase updateUseCase;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("modificar pregunta CRUD router")
    public void modificarQuestionTest(){
        var questionDTO  = new QuestionDTO("14", "1", "quien soy", "OPEN", "DDDD");
        Mockito.when(updateUseCase.modificarQuestion(questionDTO)).thenReturn(Mono.just(questionDTO));

        webTestClient.put().uri("/modificarQuestion")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(questionDTO),QuestionDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(QuestionDTO.class)
                .value(response->{
                    Assertions.assertThat(response.getId()).isEqualTo("14");

                });

    }

}