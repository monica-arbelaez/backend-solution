package co.com.sofka.questions.businessusecase;


import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.mapper.AnswerMapper;
import co.com.sofka.questions.mapper.QuestionMapper;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class UpdateQuestionByAnswerUseCase {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;

    @Autowired
    public UpdateQuestionByAnswerUseCase(QuestionRepository questionRepository, AnswerRepository answerRepository, QuestionMapper questionMapper, AnswerMapper answerMapper) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.questionMapper = questionMapper;
        this.answerMapper = answerMapper;
    }

    public Mono<QuestionDTO> updateQuestionByAnswer(QuestionDTO questionDTO) {
        var respuestas = answerRepository.findAllByQuestionId(questionDTO.getId()).next().switchIfEmpty(Mono.just(new Answer()))
                .map(answerMapper.fromAnswerToQuestionsDTO(questionDTO))
                .flatMap(response -> {
                    if(response.getId()==null){
                      return  questionRepository.save(response);
                    }
                    return conteo(response);
                });
        return respuestas.map(questionMapper.mapQuestionToDTO());
    }

    public Mono<Question> conteo(Question question) {
        return questionRepository.findById(question.getId()).flatMap(response -> {
            response.setVersion(response.getVersion() + 1);
            response.setModified(true);
            return questionRepository.save(response);
        });
    }


}
