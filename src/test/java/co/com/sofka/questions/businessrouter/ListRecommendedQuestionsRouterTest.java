package co.com.sofka.questions.businessrouter;

import co.com.sofka.questions.businessusecase.ListRecommendedQuestionsUseCase;
import co.com.sofka.questions.model.QuestionDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;


@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ListRecommendedQuestionsRouter.class})
class ListRecommendedQuestionsRouterTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ListRecommendedQuestionsUseCase listRecommendedQuestionsUseCase;


    @Test
    @DisplayName("Listar recomendacion de preguntas")
    public void listRecommended(){

        var question = new QuestionDTO();
        question.setId("15");
        question.setUserId("1");
        question.setQuestion("quien soy yo");
        question.setType("OPEN");
        question.setCategory("DDDD");

        Mockito.when(listRecommendedQuestionsUseCase.seek(Mockito.any(String.class))).thenReturn(Flux.just(question));

        webTestClient.get()
                .uri("/recomendarquestion/{question}","yo")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(QuestionDTO.class)
                .value(response->{
                    Assertions.assertThat(response.get(0).getQuestion()).isEqualTo(question.getQuestion());
                });


    }

}