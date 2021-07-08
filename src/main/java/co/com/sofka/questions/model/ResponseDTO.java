package co.com.sofka.questions.model;

public class ResponseDTO {

    private String mensaje;
    private QuestionDTO questionDTO;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public QuestionDTO getQuestionDTO() {
        return questionDTO;
    }

    public void setQuestionDTO(QuestionDTO questionDTO) {
        this.questionDTO = questionDTO;
    }
}
