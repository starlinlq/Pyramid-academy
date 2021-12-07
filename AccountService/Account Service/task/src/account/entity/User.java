package account.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "USER_TBL")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private long id;
    @NotBlank
    @JsonProperty("name")
    private String userName;
    @NotBlank
    private String lastname;
    @Pattern(regexp = "\\w+@acme.com")
    private String email;
    @NotBlank
    private String password;
    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();
    @JsonIgnore
    private boolean accountNonExpired;
    @JsonIgnore
    private boolean accountNonLocked;
    @JsonIgnore
    private boolean credentialsNonExpired;
    @JsonIgnore
    private boolean enabled;

    public User() {
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public void addRole(String role) {
        this.roles.add(role);
    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setUserName(String username){
        this.userName = username;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email.toLowerCase(Locale.ROOT);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    @Override
    public List<GrantedAuthority> getAuthorities() {
       List<GrantedAuthority> authorities = new ArrayList<>();
       roles.forEach(role-> authorities.add(new SimpleGrantedAuthority(role)));
       return authorities;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    @JsonProperty("name")
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
