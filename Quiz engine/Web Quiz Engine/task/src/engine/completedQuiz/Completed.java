package engine.completedQuiz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import engine.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="completed")
public class Completed {
    @JsonIgnore
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @JsonProperty("id")
    @Column(name = "QuizID")
    private long quizId;

    @Column(name = "completedAt")
    private LocalDateTime completedAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "User")
    User user;

    public Completed(long quizId, LocalDateTime dateTime, User user){
        this.quizId = quizId;
        this.completedAt = dateTime;
        this.user = user;
    }

    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Completed(){}

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
