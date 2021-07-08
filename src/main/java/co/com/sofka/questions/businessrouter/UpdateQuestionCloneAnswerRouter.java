package co.com.sofka.questions.businessrouter;

import co.com.sofka.questions.businessusecase.VerifyUserQuestionUseCase;
import co.com.sofka.questions.model.QuestionDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UpdateQuestionCloneAnswerRouter {
    @Bean
    public RouterFunction<ServerResponse> actualizarClonarQuestion(VerifyUserQuestionUseCase updateQuestionCloneAnswerUseCase) {

        return route(PUT("/modificarclonarquestion").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(QuestionDTO.class)
                        .flatMap(questionDTO -> updateQuestionCloneAnswerUseCase.verificarUsuarioPregunta(questionDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result)).onErrorResume(error ->{
                                            if(error instanceof IllegalAccessException){
                                                return ServerResponse.badRequest().bodyValue("usuario no esta");
                                            }
                                            return ServerResponse.badRequest().build();
                                })));

    }


}
