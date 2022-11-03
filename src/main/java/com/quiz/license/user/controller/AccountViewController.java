package com.quiz.license.user.controller;

import com.quiz.license.common.security.LoginService;
import com.quiz.license.user.domain.dto.AccountDto;
import com.quiz.license.user.domain.entity.AccountEntity;
import com.quiz.license.user.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/account/view")
public class AccountViewController {

    private final AccountService accountService;
    private final LoginService loginService;

    @Autowired
    public AccountViewController(AccountService accountService, LoginService loginService) {
        this.accountService = accountService;
        this.loginService = loginService;
    }

    // 내용 이해하고 builder에 대해 알아두기. - 난이도 중하
    // 회원 리스트 조회 -- /account/view/admin/list
    @GetMapping("/admin/list")
    public String getAccountList(Model model, @PageableDefault Pageable pageable) {
//        List<AccountDto> userDtoList = accountService.getUserList();
//        model.addAttribute("userDtoList", userDtoList);
//        log.info("=== getAccountList === userDtoList : {}", userDtoList);
        //페이징
        Page<AccountEntity> userEntityList = accountService.getUserListPageable(pageable);
        model.addAttribute("userEntityList", userEntityList);
        int start = (int) (Math.floor(userEntityList.getNumber() / 10) * 10 + 1);
        int last = start + 9 < userEntityList.getTotalPages() ? start + 9 : userEntityList.getTotalPages();
        model.addAttribute("start", start);
        model.addAttribute("last", last);

        return "/user/list";
    }

    // 회원 등록 폼 -- /account/view/common/signup
    @GetMapping("/common/signup")
    public String signup() {
        return "/user/signup";
    }


    // 회원 마이페이지  -- /account/view/user/detail/{userId}
    @GetMapping("/user/detail/{userId}")
    public String getUserDetail(@PathVariable String userId, Principal principal, Model model) {
        if (userId.equals(principal.getName())) {
            AccountDto userDto = accountService.getUserDetail(userId);
            model.addAttribute("userDto", userDto);
            return "/user/detail";
        } else {
            return "/403";
        }
    }

    // 회원 마이페이지 관리자가 보기  -- /account/view/admin/detail/{userId}
    @GetMapping("/admin/detail/{userId}")
    public String getUserDetailByAdmin(@PathVariable String userId, Model model) {
        AccountDto userDto = accountService.getUserDetail(userId);
        model.addAttribute("userDto", userDto);
        return "/user/detailForAdmin";

    }

    // 회원 마이페이지->업데이트 폼 --/account/view/user/update/{userId}
    @GetMapping("/user/update/{userId}")
    public String updateForm(@PathVariable String userId, Principal principal, Model model) {
        if (userId.equals(principal.getName())) {
            AccountDto userDto = accountService.getUserDetail(userId);
            model.addAttribute("userDto", userDto);
            return "/user/update";
        } else {
            return "/403";
        }
    }


}
