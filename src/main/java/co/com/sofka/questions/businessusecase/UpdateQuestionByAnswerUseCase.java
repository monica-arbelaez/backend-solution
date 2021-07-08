package co.com.sofka.questions.businessusecase;


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

    public Mono<QuestionDTO> updateQuestionByAnswer(QuestionDTO questionDTO){
        var respuestas = answerRepository.findAllByQuestionId(questionDTO.getId());
        var questions = respuestas.map(answerMapper.fromAnswerToAnswerDTO()).buffer().map(answerMapper.fromAnswerToQuestionsDTO(questionDTO)).ignoreElements();
        var re = questions.flatMap(it-> it.getId().isEmpty()?questionRepository.save(questionMapper.mapperToQuestion(questionDTO.getId()).apply(questionDTO)): questionRepository.save(questionMapper.mapperToQuestion(null).apply(questionDTO)));
        return  re.map(questionMapper.mapQuestionToDTO());
    }


}
