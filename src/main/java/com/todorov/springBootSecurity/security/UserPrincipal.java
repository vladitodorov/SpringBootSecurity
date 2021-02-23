package com.todorov.springBootSecurity.security;

import com.todorov.springBootSecurity.model.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private final UserEntity userEntity;

    public UserPrincipal(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        //Extract list of permissions
        this.userEntity.getPermissionList().forEach(p -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(p);
            authorities.add(grantedAuthority);
        });
        //Extract list of roles
        this.userEntity.getRolesList().forEach(r -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + r);
            authorities.add(grantedAuthority);
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userEntity.getUsername();
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
        return this.userEntity.getActive() == 1;
    }
}
