package com.quiz.license;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping("/")
    public String homeController() {
        return "index";
    }

    // -- /sqld/view/common/list 호출로 대체
//    @GetMapping("/quiz/sqld")
//    public String sqldController() {
//        return "quiz/sqldquiz";
//    }

    // -- /community/view/common/list 호출로 대체
//    @GetMapping("/community")
//    public String communityController() {
//        return "community/list";
//    }

    // -- /notice/view/common/list 호출로 대체
//    @GetMapping("/notice")
//    public String noticeController() {
//        return "notice/list";
//    }




}
