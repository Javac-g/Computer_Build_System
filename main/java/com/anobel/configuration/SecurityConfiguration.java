package com.anobel.configuration;

import com.anobel.model.Client;
import com.anobel.repository.ClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfiguration implements WebMvcConfigurer {

    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();


    }

    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/login").setViewName("login");
    }
    @Bean
    public UserDetailsService userDetailsService(ClientRepository clientRepository){
        return login->{
            Client client = clientRepository.findClientByLogin(login);
            if (client != null)return client;
            throw new UsernameNotFoundException("User with login: " + login + " not found");
        };
    }

    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
                .requestMatchers("/clients/all").hasAuthority("ADMIN")
                .requestMatchers("/login","/clients/new").permitAll()
                .anyRequest().hasAnyAuthority("USER","ADMIN").and()
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/home",true)
                .failureUrl("/login?error").and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout");
    }

    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
