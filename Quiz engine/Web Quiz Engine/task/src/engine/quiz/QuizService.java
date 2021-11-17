package engine.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    @Autowired
    QuizService(QuizRepository quizRepository){
        this.quizRepository = quizRepository;
    }

    public Page<Quiz> getAllQuizzes(int page, int pageSize){
        Pageable paging =  PageRequest.of(page, 10);

        Page<Quiz> pageResult = quizRepository.findAll(paging);
        return pageResult;
    }

    public Quiz getSingleQuiz(long id){
        Optional<Quiz> quiz = quizRepository.findById(id);
       return quiz.orElse(null);
    }


    public Quiz addQuiz(Quiz quiz){

        quizRepository.save(quiz);
        return quiz;
    }

    public void deleteQuiz(long id){
        quizRepository.deleteById(id);
    }
}
