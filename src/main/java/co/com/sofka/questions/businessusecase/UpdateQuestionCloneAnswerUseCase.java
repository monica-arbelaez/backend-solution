package co.com.sofka.questions.businessusecase;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.mapper.AnswerMapper;
import co.com.sofka.questions.mapper.QuestionMapper;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class UpdateQuestionCloneAnswerUseCase {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    private final QuestionMapper questionMapper;

    @Autowired
    public UpdateQuestionCloneAnswerUseCase(QuestionRepository questionRepository, AnswerRepository answerRepository, AnswerMapper answerMapper, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
        this.questionMapper = questionMapper;
    }

    public Mono<QuestionDTO> actualizarClonarPreguntas(QuestionDTO questionDTO) {
        var respuestas = answerRepository.findAllByQuestionId(questionDTO.getId());
        if (respuestas == null) {
            return questionRepository.save(questionMapper.mapperToQuestion(questionDTO.getId()).apply(questionDTO)).map(questionMapper.mapQuestionToDTO());
        } else {
            var questionClone = questionRepository.save(questionMapper.mapperToQuestion(null).apply(questionDTO));
            var respuestaDTO = questionClone.flatMap(it -> {
                var updateAnswer = respuestas.map(answerMapper.AnswerClone(it.getId()));
                var answerClone = updateAnswer.flatMap((Function<Answer, Publisher<Answer>>) answerRepository::save);
                var questionDTOClone = answerClone.map(answerMapper.fromAnswerToAnswerDTO()).buffer().map(answerMapper.fromAnswerToQuestionsDTO(questionMapper.mapQuestionToDTO().apply(it))).ignoreElements();
                return questionDTOClone;
            });
            return respuestaDTO;
        }
    }

}
