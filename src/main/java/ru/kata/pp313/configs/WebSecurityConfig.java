package ru.kata.pp313.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration//необязательно
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;

    public WebSecurityConfig(SuccessUserHandler successUserHandler) {
        this.successUserHandler = successUserHandler;
    }

    //ниже определяется куда у пользователя есть доступ, порядок ввода пароля, действия после ввода пароля
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//правила доступа(ограничения)
                .antMatchers("/", "/index").permitAll()//вход для авторизованных пользователей остальное для всех
                .anyRequest().authenticated()
                .and()//конец блока настройки
                .formLogin().successHandler(successUserHandler)//form for authentication обработчик успешной аутентификации
                .permitAll()
                .and()//конец блока настройки
                .logout()//направление на страницу
                .permitAll();
    }

    // аутентификация inMemory
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("user")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}