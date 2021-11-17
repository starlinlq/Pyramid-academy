package engine.quiz;

import engine.Response;
import engine.completedQuiz.Completed;
import engine.completedQuiz.CompletedService;
import engine.user.User;
import engine.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;


@RestController
@RequestMapping(path = "/api/quizzes")
public class QuizController {
    private final QuizService quizService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompletedService completedService;
    @Autowired
    QuizController(QuizService quizService){
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseEntity<Page<Quiz>> getAllQuizzes(@RequestParam int page, @RequestParam(defaultValue = "10") int pageSize){
        return new ResponseEntity<>(quizService.getAllQuizzes(page, pageSize), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Quiz>> deleteQuiz(@PathVariable int id, @AuthenticationPrincipal UserDetails details){
        User user = userService.getUserByEmail(details.getUsername());
        System.out.println(user.getQuizList().toString());
        Quiz exists  = quizService.getSingleQuiz(id);
        if(exists == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for(Quiz quiz : user.getQuizList()){
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
        quiz.setUser(user);
        return new ResponseEntity<>(quizService.addQuiz(quiz), HttpStatus.OK);
    }

    @PostMapping("/{id}/solve")
    public ResponseEntity<Response> solveQuiz(@PathVariable int id, @RequestBody  HashMap<String, ArrayList<Integer>> answer, Authentication auth){
       Quiz quiz  = quizService.getSingleQuiz(id);
       UserDetails userDetails = (UserDetails) auth.getPrincipal();
       User user  = userService.getUserByEmail(userDetails.getUsername());

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
           Completed completed = new Completed(quiz.getId(), LocalDateTime.now(), user);
           completedService.save(completed);
       } else {
           response = new Response(false, "Wrong answer! Please, try again.");
       }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/completed")
    public ResponseEntity<Page<Completed>> completed(@RequestParam int page, Authentication auth){
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user  = userService.getUserByEmail(userDetails.getUsername());
        Page<Completed> list = completedService.getAllCompletedByUser(user, page);

        return ResponseEntity.ok().body(list);
    }



}
