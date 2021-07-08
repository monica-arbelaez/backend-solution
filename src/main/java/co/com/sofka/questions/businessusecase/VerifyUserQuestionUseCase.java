package co.com.sofka.questions.businessusecase;

import co.com.sofka.questions.mapper.AnswerMapper;
import co.com.sofka.questions.mapper.QuestionMapper;
import co.com.sofka.questions.mapper.ResponseMapper;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.model.ResponseDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class VerifyUserQuestionUseCase {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    private final QuestionMapper questionMapper;
    private final ResponseMapper responseMappe;

    @Autowired
    public VerifyUserQuestionUseCase(QuestionRepository questionRepository, AnswerRepository answerRepository, AnswerMapper answerMapper, QuestionMapper questionMapper, ResponseMapper responseMappe) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
        this.questionMapper = questionMapper;
        this.responseMappe = responseMappe;
    }

    public Mono<QuestionDTO> verificarUsuarioPregunta(QuestionDTO questionDTO) {
        return questionRepository.findByIdAndUserId(questionDTO.getId(), questionDTO.getUserId())
                .map(questionMapper.mapQuestionToDTO()).switchIfEmpty(Mono.error(new IllegalAccessException()));
    }


}
