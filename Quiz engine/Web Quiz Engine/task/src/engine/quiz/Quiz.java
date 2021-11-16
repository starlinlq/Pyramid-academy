package engine.quiz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import engine.user.User;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue
    private long id;
    @Column(name="title")
    @Pattern(regexp = "[A-Za-z](\\w\\s?,?.?!?)+")
    private String title;
    @Column(name="text")
    @Pattern(regexp = "[A-Za-z](\\w\\s?,?.?!?)+")
    private String text;
    @Column(name="options")
    @Size(min = 2)
    private String[] options;
    @ElementCollection
    @Column(name="answer")
    private List<Integer> answer;
    @JsonIgnore
    @Column(name = "owner")
     private String owner;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    Quiz(){}

    Quiz(String title, String text, String[] options, List<Integer> answer, User user){
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer == null ? new ArrayList<>() : answer;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonIgnore
    public List<Integer> getAnswer() {
        return answer == null ? new ArrayList<>(): answer;
    }
    @JsonProperty("answer")
    public void setAnswer(List<Integer> answer) {
        this.answer = answer == null ? new ArrayList<>() : answer;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText(){
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions(){
        return this.options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
