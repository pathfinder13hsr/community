package com.quiz.license;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {


    // 회원 로그인 -- /account/view/common/login
    @GetMapping
    public String login() {
        return "/user/login";
    }


    // 회원 로그인 성공시 이동-- /account/view/common/login/success
    @GetMapping("/success")
    public String loginSuccess() {
        log.info("성공 페이지 호출");
//        model.addAttribute("msg", "실패");
        return "index";
    }

    // 회원 로그인 실패 메시지-- /account/view/common/login/fail
    @GetMapping("/fail")
    public String loginFail(Model model) {
        log.info("실패 페이지 호출");
//        model.addAttribute("msg", "실패");
        return "/user/login";
    }


}
