package com.quiz.license.common.security;

import com.quiz.license.user.domain.dto.AccountDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final LoginService loginService;

    public CustomAuthenticationProvider(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = (String)authentication.getPrincipal();
        String userPassword = (String)authentication.getCredentials();

        log.info("userId : "+userId);
        log.info("userPassword : "+userPassword);

        //로그인 로직 구현
        UserDetails user = loginService.loadUserByUsername(userId);

        // 비밀번호 체크
        if(userPassword.equals(user.getPassword())){
            AccountDto accountDto = loginService.getAccount(userId);
            HttpSession session = ((CustomWebAuthenticationDetails)authentication.getDetails()).getSession();
            session.setAttribute("userNickname", accountDto.getUserNickname());
            session.setAttribute("userId", accountDto.getUserId());
            session.setAttribute("seq", accountDto.getSeq());
            session.setAttribute("userType", Objects.equals("Y", accountDto.getAdminYn()) ? "admin" : "user");

            return new UsernamePasswordAuthenticationToken(user.getUsername(), userPassword, user.getAuthorities());
        }else{
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
