package edu.whu.recirco.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    JwtRequestFilter jwtRequestFilter; //注入JWT过滤器Bean

    //使用HttpSecurity来配置认证和授权
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();   //关闭csrf过滤器

        http.authorizeRequests()
                .antMatchers("/login").permitAll() //login接口直接放行
                .antMatchers("/register").permitAll() //register接口直接放行
                .antMatchers("/editor.html").permitAll() //register接口直接放行
                //.anyRequest().authenticated()  //其他接口需要认证
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //添加jwtRequestFilter过滤器到UsernamePasswordAuthenticationFilter之前
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //BCrypt加密算法
    }
}
