package com.quiz.license.community.controller;

import com.quiz.license.community.domain.dto.CommunityDto;
import com.quiz.license.community.service.CommunityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/community/user")
public class CommunityController {

    private final CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    // 커뮤니티 게시글 등록 -- /community/user/insert
    @ResponseBody
    @PostMapping("/insert")
    public void insertCommunity(@RequestBody CommunityDto communityDto) {
        log.info("=== insertCommunity === communityDto : {}", communityDto);
        communityService.insertCommunity(communityDto);
    }

    // 커뮤니티 게시글 삭제 -- /community/user/delete/{seq}
    @ResponseBody
    @DeleteMapping("/delete/{seq}")
    public void deleteCommunity(@PathVariable Long seq) {
        log.info("=== deleteCommunity === seq : {}", seq);
        communityService.deleteCommunity(seq);
    }

    // 커뮤니티 게시글 수정 -- /community/user/update
    @ResponseBody
    @PutMapping("/update")
    public void updateCommunity(@RequestBody CommunityDto communityDto) {
        log.info("=== updateCommunity === communityDto : {}", communityDto);
        communityService.updateCommunity(communityDto);
    }


}
