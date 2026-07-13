package com.lira.dev.locadora_veiculos.config;

import com.lira.dev.locadora_veiculos.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter){
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/usuarios/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        //Acesso aos métodos da entidade Veiculo
                        .requestMatchers(HttpMethod.POST,"/veiculos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/veiculos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/veiculos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/veiculos").hasAnyRole("ADMIN","FUNCIONARIO")
                        .requestMatchers(HttpMethod.GET,"/veiculos/**").hasAnyRole("ADMIN","FUNCIONARIO")
                        //Acesso aos métodos da entidade Locacao
                        .requestMatchers(HttpMethod.POST,"/locacoes").hasAnyRole("ADMIN","FUNCIONARIO")
                        .requestMatchers(HttpMethod.PATCH,"/locacoes/*/devolver").hasAnyRole("ADMIN","FUNCIONARIO")
                        .requestMatchers(HttpMethod.GET,"/locacoes").hasAnyRole("ADMIN","FUNCIONARIO")
                        .anyRequest().authenticated());

        return http.build();
    }




}
