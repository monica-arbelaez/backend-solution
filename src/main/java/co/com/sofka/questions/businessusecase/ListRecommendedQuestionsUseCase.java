package co.com.sofka.questions.businessusecase;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.mapper.QuestionMapper;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
public class ListRecommendedQuestionsUseCase {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Autowired
    public ListRecommendedQuestionsUseCase(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    public Flux<QuestionDTO> seek(String question){
        return   questionRepository.findByquestionLike(question)
                .map(questionMapper.mapQuestionToDTO());


    }
}
