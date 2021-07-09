package co.com.sofka.questions.businessrouter;


import co.com.sofka.questions.businessusecase.AlertEditQuestionUseCase;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;


import static org.junit.jupiter.api.Assertions.*;


@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AlertEditQuestionRouter.class})
class AlertEditQuestionRouterTest {


    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private AlertEditQuestionUseCase alertEditQuestionUseCase;


}