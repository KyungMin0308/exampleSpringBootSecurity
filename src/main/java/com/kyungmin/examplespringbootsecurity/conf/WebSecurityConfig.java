package com.kyungmin.examplespringbootsecurity.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //이거는 없어도 가능: @EnableWebSecurity 에 @Configuration 이 포함되어 있기 때문에
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { //Authentication & Authorization 설정

    @Autowired
    private UserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Authentication
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    //정적 자원 리스트
    private static final String[] PUBLIC_MATCHERS = {
            "/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/about/**",
            "/contact/**",
            "/error/**/*",
            "/console/**"
    };

    //Authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(PUBLIC_MATCHERS).permitAll()
                    .antMatchers("/", "/home", "/signup").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/home")
                    .failureUrl("/login?error")
                    .permitAll()
                    .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/accessDenied");
    }
}
