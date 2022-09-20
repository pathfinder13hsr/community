package com.quiz.license.sqld.controller;


import com.quiz.license.sqld.Service.SqldService;
import com.quiz.license.sqld.domain.entity.SqldEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j // 로깅
@RestController // @RestController and @Controller : 차이 알아보기
@RequestMapping("/user/sqld") //
public class SqldController {

    private final SqldService sqldService;

    @Autowired // 실제로 이 부분에 @Autowired 를 달아도 동작하지 않지만 명시적으로 표현하기 위해 씀.
    public SqldController(SqldService sqldService) {
        this.sqldService = sqldService;
    }

    // @RequestBody, @RequestParam, @ModelAttribute 차이점과 사용해야하는 때를 잘 구별하기.
    @GetMapping("/list")
    public ResponseEntity<List<SqldEntity>> getBuyerList() {
        List<SqldEntity> sqldEntityList = sqldService.getSqldQuiz();
        System.out.println("!!");
        return ResponseEntity.ok(sqldEntityList);
    }

}
