package com.anobel.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfiguration  {
	
	//private final UserDetailsService detailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();


    }

/*

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
         http.authorizeHttpRequests()
                .requestMatchers("/clients/all").hasAuthority("ADMIN")
                .requestMatchers("/login","/clients/new").permitAll()
                .anyRequest().hasAnyAuthority("USER","ADMIN").and()
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/home",true)
                .failureUrl("/login?error").and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout");
         return http.build();
    }
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService)
                .passwordEncoder(passwordEncoder());
    }

*/
}
