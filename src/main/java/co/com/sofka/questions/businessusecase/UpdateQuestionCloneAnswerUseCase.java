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

    public Mono<QuestionDTO> actualizarClonarPreguntas(QuestionDTO questionDTO){
           var respuestas=answerRepository.findAllByQuestionId(questionDTO.getId());
           if(respuestas == null){
               return  questionRepository.save(questionMapper.mapperToQuestion(questionDTO.getId()).apply(questionDTO)).map(questionMapper.mapQuestionToDTO());
           }else{
               var questionClone = questionRepository.save(questionMapper.mapperToQuestion(null).apply(questionDTO));
               var prueba = questionClone.flatMap(it->{
                   var cloneAnswer = respuestas.map(answerMapper.AnswerClone(it.getId()));
                   answerRepository.saveAll(cloneAnswer);
                   return questionClone.map(questionMapper.mapQuestionToDTO());
                       });
               return prueba;
           }
    }

}
