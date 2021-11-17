package com.pyramidAcademy.pyramidAir.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private final String username;
    private final String password;
    private final List<GrantedAuthority> grantedAuthorityList;

    UserDetailsImpl(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.grantedAuthorityList = List.of(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorityList;
    }


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
        return true;
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
        return true;
    }
}