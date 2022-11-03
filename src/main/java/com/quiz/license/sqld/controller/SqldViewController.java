package com.quiz.license.sqld.controller;


import com.quiz.license.sqld.domain.entity.SqldEntity;
import com.quiz.license.sqld.service.SqldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/sqld/view") // view 동작. select List 혹인 select detail
public class SqldViewController {

    private final SqldService sqldService;

    @Autowired
    public SqldViewController(SqldService sqldService) {
        this.sqldService = sqldService;
    }

    // @RequestBody, @RequestParam, @ModelAttribute 차이점과 사용해야하는 때를 잘 구별하기.
    // SQLD 퀴즈 리스트 조회하기 -- /sqld/view/common/list
    @ResponseBody
    @GetMapping("/common/list")
    public ResponseEntity<List<SqldEntity>> getSqldQuizList() {
        List<SqldEntity> sqldEntityList = sqldService.getSqldQuizList();
        return ResponseEntity.ok(sqldEntityList);
    }

}
