package co.com.sofka.questions.mapper;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.model.ResponseDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ResponseMapper {

    public Function<QuestionDTO, ResponseDTO> dtoToResponse(String mensaje){
        return question->{
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMensaje(mensaje);
            responseDTO.setQuestionDTO(question);
            return  responseDTO;
        };
    }
}
