package com.quiz.license.common.config;

import com.quiz.license.common.security.CustomAuthenticationFailureHandler;
import com.quiz.license.common.security.CustomAuthenticationProvider;
import com.quiz.license.common.security.CustomAuthenticationSuccessHandler;
import com.quiz.license.common.security.CustomWebAuthenticationDetailsSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
@Configuration
// 웹 보안 활성화 의미가 있으려면 WebSecurityConfigurer 구현해주어야한다.
// 매 Request는 스프링 시큐리티의 필터들을 거친다.
@EnableWebSecurity
// 메서드 수준에서 권한을 제어(?)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{

    private final CustomAuthenticationProvider customAuthenticationProvider;

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomWebAuthenticationDetailsSource customWebAuthenticationDetailsSource;


    @Autowired
    public SecurityConfig(CustomAuthenticationProvider customAuthenticationProvider, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler, CustomAuthenticationFailureHandler customAuthenticationFailureHandler, CustomWebAuthenticationDetailsSource customWebAuthenticationDetailsSource) {
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
        this.customWebAuthenticationDetailsSource = customWebAuthenticationDetailsSource;
    }

    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web
                        .ignoring()
                        .antMatchers("/css/**", "/fonts/**", "/js/**", "/img/**", "/lib/**", "/scss/**");
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable(); // 토큰 검사 비활성화 - 토큰을 넘겨준다
//        http.csrf().ignoringAntMatchers("/api/**"); /* REST API 사용 예외처리 */

        log.info("=== SecurityFilterChain ===");

        http.sessionManagement()
                .maximumSessions(1)// 최대 허용 가능 세션 수, -1인 경우 무제한 세션 허용
                .maxSessionsPreventsLogin(false) // 동시 로그인 차단, false인 경우 기존 세션 만료
                .expiredUrl("/login")
                .sessionRegistry(sessionRegistry()); // 이걸 붙여주지 않으면 Logout 후 다시 Login 할 때 "Maximum sessions of 1 for this principal exceeded" 에러를 발생시키며 로그인 되지 않습니다.

        http.authorizeRequests()
                .antMatchers("/login", "/", "/**/**/common/**", "/**/common/**", "/account/checkId", "/account/checkEmail", "/account/checkNickname", "/account/signup").permitAll() // 여기에 적은 URL은 모두의 접근을 허용한다
                .antMatchers("/login").authenticated()
                .antMatchers("/admin", "/admin/**").hasRole("ADMIN") // /admin, /admin/** 요청에 대해서는 ROLE_ADMIN 역할을 가지고 있어야 함
                .antMatchers("/**").hasAnyRole("ADMIN", "USER") // /** 요청에 대해서는 ROLE_ADMIN 또는 ROLE_USER 역할을 가지고 있어야 함
                .anyRequest().authenticated(); // 그 외의 URL은 허용하지 않겠다는 의미

        http.formLogin()
                .loginPage("/login") // 로그인 시도할 페이지 URL을 적는다. 여기에 페이지 URL 지정 안해주면 시큐리티 기본제공 창이 뜸.
                .loginProcessingUrl("/login-authentication") // 사용자 이름과 암호를 제출할 URL
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .authenticationDetailsSource(customWebAuthenticationDetailsSource)
                .permitAll()
//                .defaultSuccessUrl("/") // 로그인 성공 후 이동할 URL
//                .failureUrl("/login/fail").permitAll() // 로그인 실패 후 이동 할 URL. permitAll() 인해주면 권한문제 발생.
        ;

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true);


        http.exceptionHandling().accessDeniedPage("/login/denied");

        http.authenticationProvider(customAuthenticationProvider);

        return http.build();
    }

    // logout 후 login할 때 정상동작을 위함
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    // was가 여러개 있을 때(session clustering)
    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }

}
