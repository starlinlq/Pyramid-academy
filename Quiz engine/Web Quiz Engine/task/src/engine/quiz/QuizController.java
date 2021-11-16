package engine.quiz;

import engine.Response;
import engine.user.User;
import engine.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.*;


@RestController
@RequestMapping(path = "/api/quizzes")
public class QuizController {
    private final QuizService quizService;
    @Autowired
    private UserService userService;

    @Autowired
    QuizController(QuizService quizService){
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes(){
        return new ResponseEntity<>(quizService.getAllQuizzes(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Quiz>> deleteQuiz(@PathVariable int id, @AuthenticationPrincipal UserDetails details){
        User user = userService.getUserByEmail(details.getUsername());
        List<Quiz> list= quizService.getAllByOwner(user.getEmail());
        Quiz exists  = quizService.getSingleQuiz(id);
        if(exists == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for(Quiz quiz : list){
            if(quiz.getId() == id){
                quizService.deleteQuiz(id);
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getSingleQuiz(@PathVariable int id){
        Quiz quiz = quizService.getSingleQuiz(id);

       if(quiz != null){
           System.out.println(quiz.getAnswer().toString());
           return new ResponseEntity<>(quiz, HttpStatus.OK);
       } else
           throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@Valid @RequestBody Quiz quiz, @AuthenticationPrincipal UserDetails userDetails){
        User user = userService.getUserByEmail(userDetails.getUsername());
        quiz.setOwner(user.getEmail());
        return new ResponseEntity<>(quizService.addQuiz(quiz), HttpStatus.OK);
    }

    @PostMapping("/{id}/solve")
    public ResponseEntity<Response> solveQuiz(@PathVariable int id, @RequestBody  HashMap<String, ArrayList<Integer>> answer){
       Quiz quiz  = quizService.getSingleQuiz(id);

       if(quiz == null ) {
           return new ResponseEntity<>(new Response(false, "Not Found"), HttpStatus.NOT_FOUND);
       }

       Response response;
       var answers = answer.get("answer");
       boolean isCorrect = false;

       if(quiz.getAnswer().size() == answers.size()){
           isCorrect =  answers.containsAll(quiz.getAnswer());
       }

       if(isCorrect){
           response = new Response(true , "Congratulations, you're right!");
       } else {
           response = new Response(false, "Wrong answer! Please, try again.");
       }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
