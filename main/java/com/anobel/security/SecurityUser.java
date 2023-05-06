package com.anobel.security;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import com.anobel.model.Client;
import java.util.*;

public class SecurityUser implements UserDetails{
    private final Client client;
    public SecurityUser(Client client){
        this.client = client;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
    public Client getClient(){
        return client;
    }
    public Long getId(){
        return client.getId();
    }
    public String getFullName(){
        return client.getFullName();
    }
    @Override
    public String getUsername() {
        return client.getLogin();
    }
	@Override
    public String getPassword() {
        return client.getPassword();
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
