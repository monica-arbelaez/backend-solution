package co.com.sofka.questions.businessrouter;

import co.com.sofka.questions.businessusecase.ListRecommendedQuestionsUseCase;
import co.com.sofka.questions.model.QuestionDTO;
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
public class ListRecommendedQuestionsRouter {
    @Bean
    public RouterFunction<ServerResponse> recomendarQuestions(ListRecommendedQuestionsUseCase listRecommendedQuestionsUseCase){
        return route(GET("/recomendarquestion/{question}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .body(listRecommendedQuestionsUseCase.seek(request.pathVariable("question")), QuestionDTO.class)

        );
    }
}
