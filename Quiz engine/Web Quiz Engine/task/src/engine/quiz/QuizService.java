package engine.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    @Autowired
    QuizService(QuizRepository quizRepository){
        this.quizRepository = quizRepository;
    }

    public List<Quiz> getAllQuizzes(){
        return (List<Quiz>) quizRepository.findAll();
    }

    public Quiz getSingleQuiz(long id){
        Optional<Quiz> quiz = quizRepository.findById(id);
       return quiz.orElse(null);
    }

    public List<Quiz> getAllByOwner(String email){
        return quizRepository.getAllByOwner(email);
    }

    public Quiz addQuiz(Quiz quiz){

        quizRepository.save(quiz);
        return quiz;
    }

    public void deleteQuiz(long id){
        quizRepository.deleteById(id);
    }
}
