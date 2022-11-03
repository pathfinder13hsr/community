package com.quiz.license.notice.controller;

import com.quiz.license.notice.domain.dto.NoticeDto;
import com.quiz.license.notice.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j //로깅????? 어케써용
@Controller
@RequestMapping("/notice/admin")
public class NoticeController {
    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    // TODO. 여기 있는 애들 리턴이 없는 애들인데 ResponseBody 떼고 확인 부탁드립니다.

    // 공지사항 등록 -- /notice/admin/insert
    @ResponseBody
    @PostMapping("/insert")
    public void insertNotice(@RequestBody NoticeDto noticeDto) {
        log.info("=== insertNotice === noticeDto : {}", noticeDto);
        noticeService.insertNotice(noticeDto);
    }

    // 공지사항 삭제 -- /notice/admin/delete/{seq}
    @ResponseBody
    @DeleteMapping("/delete/{seq}")
    public void deleteNotice(@PathVariable Long seq) {
        log.info("=== deleteNotice === seq : {}", seq);
        noticeService.deleteNotice(seq);
    }

    // 공지사항 업데이트 -- /notice/admin/update
    @ResponseBody
    @PutMapping("/update")
    public void updateNotice(@RequestBody NoticeDto noticeDto) {
        log.info("=== updateNotice === noticeDto : {}", noticeDto);
        noticeService.updateNotice(noticeDto);
    }

}
