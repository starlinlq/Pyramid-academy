package com.crave.crave.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "USER")
public class User implements UserDetails {

    @Id
    @SequenceGenerator(sequenceName = "id_sequence", name="id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    private long id;
    @Size(min = 4)
    @Column(name="username",unique = true)
    private String username;
    @Size(min = 6)
    private String password;

    @Size(min = 5)
    @Column(name = "email", unique = true)
    private String email;
    @JsonIgnore
    private boolean expired;
    @JsonIgnore
    private boolean enable;
    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;

    @OneToMany(mappedBy = "user")
    List<Recipe> recipes = new ArrayList<>();

    public List<String> getRoles() {
        return this.roles;
    }

    public void addRole(String role) {
       this.roles.add(role);
    }

    User(){
        this.expired = true;
        this.enable = true;
    }

    public long getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
