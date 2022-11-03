package com.quiz.license.common.security;

import com.quiz.license.user.domain.dto.AccountDto;
import com.quiz.license.user.domain.entity.AccountEntity;
import com.quiz.license.user.domain.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class LoginService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Autowired
    public LoginService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        //userId를 이용하여 DB 조회
        log.info("userId : " + userId);
        if(accountRepository.existsByUserId(userId)){
            AccountEntity accountEntity = accountRepository.findByUserId(userId);
            AccountDto userDto = new AccountDto(accountEntity);
            String userPw = userDto.getUserPw();

            log.info("userDto : " + userDto);

            if(userDto.getStatusYn().equals("N")){
                throw new UsernameNotFoundException("탈퇴한 회원입니다.");
            }

            //성공일경우 user 객체 반환
            List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
            if ("N".equals(userDto.getAdminYn())) {
                roles.add(new SimpleGrantedAuthority("ROLE_USER"));
            } else if ("Y".equals(userDto.getAdminYn())) {
                roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else {
                return null;
            }

            return new User(userId, userPw, roles);

        } else {
            throw new UsernameNotFoundException("아이디가 존재하지 않습니다.");
        }
    }

    public AccountDto getAccount(String userId) throws UsernameNotFoundException {
        AccountEntity accountEntity = accountRepository.findByUserId(userId);

        if (!ObjectUtils.isEmpty(accountEntity)) {
            return new AccountDto(accountEntity);
        } else {
            throw new UsernameNotFoundException("아이디가 존재하지 않습니다.");
        }

    }

}
