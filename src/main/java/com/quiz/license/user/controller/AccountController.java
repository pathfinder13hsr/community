package com.quiz.license.user.controller;

import com.quiz.license.user.domain.dto.AccountDto;
import com.quiz.license.user.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService; //여기에 @Autowired 달면 필드인젝션


    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    } // 여기에 달면 생성자 주입

    //회원가입
    @ResponseBody
    @PostMapping("/signup")
    public void insertUser(@RequestBody @Validated AccountDto userDto) {
        log.info("=== insertUser === userDto : {}", userDto);
        accountService.insertUser(userDto);
        log.info("회원가입 성공");
    }


    //회원가입시 유효성검증 시작
    @ResponseBody
    @PostMapping("/checkId")
    public String checkId(@RequestBody String userId) {
        if (!accountService.checkId(userId)) {
            return "available";
        } else {
            return "duplicate";
        }
    }

    @PostMapping("/checkEmail")
    @ResponseBody
    public String checkEmail(@RequestBody String userEmail) {
        if (!accountService.checkEmail(userEmail)) {
            return "available";
        } else {
            return "duplicate";
        }
    }

    @PostMapping("/checkNickname")
    @ResponseBody
    public String checkNickname(@RequestBody String userNickname) {
        if (!accountService.checkNickname(userNickname)) {
            return "available";
        } else {
            return "duplicate";
        }
    }
    //유효성검증 끝

    // 회원 수정(회원 본인) -- /account/user/update
    @ResponseBody
    @PutMapping("/user/update")
    public void updateAccount(@RequestBody @Validated AccountDto accountDto) {
        log.info("=== updateAccount === accountDto : {}", accountDto);
        accountService.updateUser(accountDto);
    }

    // 회원 수정(관리자가 statusYn,adminYn 변경) -- /account/admin/update
    @ResponseBody
    @PutMapping("/admin/update")
    public void updateAccountByAdmin(@RequestBody AccountDto accountDto) {
        log.info("=== updateAccount === accountDto : {}", accountDto);
        accountService.updateUserByAdmin(accountDto);
    }

}
