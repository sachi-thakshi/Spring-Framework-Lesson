package lk.ijse.gdse.o_13_spring_security_with_jwt.config;

import lk.ijse.gdse.o_13_spring_security_with_jwt.util.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity // authorization check -> method ekn method ekata apply wenne
//@EnableWebSecurity // role base na ekata uda eka oni
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtAuthFilter jwtAuthFilter;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // SecurityFilterChain -> checking requests
        http.csrf(AbstractHttpConfigurer::disable) // csrf-> Cross-Site Request Forgery disable karanwa
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/auth/**").permitAll().anyRequest().authenticated()) // request karapu ewa balanwa

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // session eka create wena policy eka
                //STATELESS -> store karanne na

                .authenticationProvider(authenticationProvider()) // daoAuthenticationProvider ekata userge details set karanawa

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // token eka thiyenawada balanna hadapu filter eka add karanawa

        return http.build(); // http security obj eka bulid karanwa
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        return daoAuthenticationProvider;
    }
}
