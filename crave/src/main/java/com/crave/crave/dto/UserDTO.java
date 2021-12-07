package com.crave.crave.dto;

public class UserDTO {
    private long id;
    private String username;
    private String email;
    private String token;


    public String getToken() {
        return token;
    }

    public long getId() {
        return id;
    }

    public UserDTO setId(long id) {
        this.id = id;
        return this;
    }

    public UserDTO setToken(String token) {
        this.token = token;
        return this;
    }

    public UserDTO setUsername(String username){
        this.username = username;
        return this;
    }

    public UserDTO setEmail(String email){
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
