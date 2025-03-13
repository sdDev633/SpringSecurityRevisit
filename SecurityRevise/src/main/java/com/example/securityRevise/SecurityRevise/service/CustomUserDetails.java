package com.example.securityRevise.SecurityRevise.service;

import com.example.securityRevise.SecurityRevise.entity.UserInfo;
import com.example.securityRevise.SecurityRevise.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends UserInfo implements UserDetails {
    private String username;
    private String password;
    Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UserInfo byUsername){
        this.username = byUsername.getUsername();
        this.password = byUsername.getPassword();
        List<GrantedAuthority> auths = new ArrayList<>();

        for(UserRole role: byUsername.getRoles()){
            auths.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
        }
        this.authorities = auths;
    }

   ;; @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
