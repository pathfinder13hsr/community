package com.quiz.license.sqld.controller;


import com.quiz.license.sqld.service.SqldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j // 로깅
@Controller // @RestController and @Controller : 차이 알아보기
@RequestMapping("/sqld") // API 동작. insert update delete
public class SqldController {

    private final SqldService sqldService;

    @Autowired // 실제로 이 부분에 @Autowired 를 달아도 동작하지 않지만 명시적으로 표현하기 위해 씀.
    public SqldController(SqldService sqldService) {
        this.sqldService = sqldService;
    }

}
