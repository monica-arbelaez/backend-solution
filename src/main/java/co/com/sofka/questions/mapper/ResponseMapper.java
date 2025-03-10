package co.com.sofka.questions.mapper;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.model.ResponseDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ResponseMapper {



    public  Function<Answer, ResponseDTO> anwerToResponse(){
        return answer -> {
            ResponseDTO dto = new ResponseDTO();
                dto.setEstado(answer.getId()==null);
                if(dto.getEstado()){
                    dto.setMensaje("Se puede modificar la pregunta");
                }else{
                    dto.setMensaje("EL guardado de la pregunta creará otra pregunta, pero no actualizara la actual");
                }
            return  dto;
        };

    }
}
