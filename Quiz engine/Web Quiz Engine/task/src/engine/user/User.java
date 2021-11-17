package engine.user;

import engine.completedQuiz.Completed;
import engine.quiz.Quiz;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true, name = "email")
    @Pattern(regexp = "\\w+[@]\\w+[.]\\w+")
    private String email;
    @Column(name = "password")
    @Size(min = 5)
    private String password;
    @Column(name = "role")
    private String role;
    @OneToMany(mappedBy = "user")
    private List<Quiz> quizzes = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Completed> completedList = new ArrayList<>();

    public User(){}
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Completed> getCompletedList() {
        return completedList;
    }

    public void setCompletedList(List<Completed> completedList) {
        this.completedList = completedList;
    }

    public List<Quiz> getQuizList() {
        return quizzes;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizzes = quizList;
    }
}
