package co.com.sofka.questions.businessrouter;

import co.com.sofka.questions.businessusecase.AlertEditQuestionUseCase;
import co.com.sofka.questions.model.ResponseDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AlertEditQuestionRouter {
    @Bean
    public RouterFunction<ServerResponse>thowAlert(AlertEditQuestionUseCase alertEditQuestionUsecase){
        return route(GET("/alertedit/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                    .body(alertEditQuestionUsecase.throwAlert(request.pathVariable("id")), ResponseDTO.class));
    }

}
