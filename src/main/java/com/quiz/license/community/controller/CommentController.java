package com.quiz.license.community.controller;

import com.quiz.license.community.domain.dto.CommentDto;
import com.quiz.license.community.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/comment/user")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 등록.
    @ResponseBody
    @PostMapping("/insert")
    public void insertComment(@RequestBody CommentDto commentDto) {
        log.info("=== insertComment === commentDto : {}", commentDto);
        commentService.insertComment(commentDto);
    }

    // 댓글 수정.
    @ResponseBody
    @PutMapping("/update")
    public void updateComment(@RequestBody CommentDto commentDto) {
        log.info("=== updateComment === commentDto : {}", commentDto);
        commentService.updateComment(commentDto);
    }

    // 댓글 삭제.
    @ResponseBody
    @DeleteMapping("/delete/{seq}")
    public void deleteComment(@PathVariable Long seq) { // 타입 오류시 String 으로 변경 후 받은 다음에 convert 처리
        log.info("=== deleteComment === seq : {}", seq);
        commentService.deleteComment(seq);
    }

}
