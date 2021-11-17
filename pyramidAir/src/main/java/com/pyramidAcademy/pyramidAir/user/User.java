package com.pyramidAcademy.pyramidAir.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pyramidAcademy.pyramidAir.boardingPass.BoardingPass;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Size(min = 3, max=15)
    @Column(name = "username", unique = true)
    private String username;

    @Size(min=3, max=20)
    @Column(name = "name")
    private String name;

    @Column(name="email", unique = true)
    private String email;

    @Size(min =4)
    @Column(name = "password")
    private String password;

    @Column(name="age")
    private int age;

    @Column(name = "gender")
    private String gender;

    @Column(name ="role")
    private  String role;

    @OneToMany(mappedBy = "user")
    List<BoardingPass> boardingPass = new ArrayList<>();

    public User(){}


    public List<BoardingPass> getBoardingPass() {
        return boardingPass;
    }

    public void setBoardingPass(List<BoardingPass> boardingPass) {
        this.boardingPass = boardingPass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
