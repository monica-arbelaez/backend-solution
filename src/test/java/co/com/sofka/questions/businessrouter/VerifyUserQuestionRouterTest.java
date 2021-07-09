package co.com.sofka.questions.businessrouter;

import co.com.sofka.questions.businessusecase.VerifyUserQuestionUseCase;
import co.com.sofka.questions.model.QuestionDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {VerifyUserQuestionRouter.class})
class VerifyUserQuestionRouterTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private VerifyUserQuestionUseCase verifyUserQuestionUseCase;

    @Test
    @DisplayName("Test de verificar pregunta de usuario")
    public void verifyUserQuestionRouterTest(){
        var questionDTO= new QuestionDTO("14", "1", "quien soy", "OPEN", "DDDD");
        Mockito.when(verifyUserQuestionUseCase.verificarUsuarioPregunta(Mockito.any(QuestionDTO.class)).thenReturn(Mono.just(questionDTO)));

        webTestClient.get().uri("/verificarusuario")
                .exchange()
                .expectStatus().isOk()
                .expectBody(QuestionDTO.class)
                .value(response->{
                    Assertions.assertThat(response.getUserId()).isEqualTo(questionDTO.getUserId());
                });

    }


}