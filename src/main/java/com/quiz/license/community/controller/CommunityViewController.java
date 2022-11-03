package com.quiz.license.community.controller;

import com.quiz.license.common.vo.PagingVo;
import com.quiz.license.community.domain.dto.CommunityDto;
import com.quiz.license.community.domain.dto.CommunitySearchDto;
import com.quiz.license.community.service.CommunityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/community/view")
public class CommunityViewController {

    private final CommunityService communityService;

    @Autowired
    public CommunityViewController(CommunityService communityService) {
        this.communityService = communityService;
    }


    // 커뮤니티 게시판 글 목록 -- /community/view/common/list
    @GetMapping("/common/list")
    public String list(@ModelAttribute("communitySearchDto") CommunitySearchDto communitySearchDto, Model model) {

        System.out.println("communitySearchDto : " + communitySearchDto.toString());
        System.out.println("communitySearchDto : " + communitySearchDto.getPage());
        System.out.println("communitySearchDto : " + communitySearchDto.getPageSize());

        List<CommunityDto> communityList = communityService.getCommunityList(communitySearchDto);
        long count = communityService.getListCount(communitySearchDto);

//        log.info("communityList : " + communityList.toString());
//        log.info("communityCount : " + count);

        model.addAttribute("communityList", communityList);
        model.addAttribute("paging", new PagingVo(communitySearchDto.getPage(), communitySearchDto.getPageSize(), count));
        return "/community/list";
    }


    //트러블슈팅
    // 1.RequestParam으로 못받음, PathVariable로 바꾸니까 동작함
    // 2.No primary or single unique constructor found for class com.quiz.license.community.domain.dto.CommunityDto 에러남
    //   기본생성자가 없어서 발생하는 에러라고 함
    //   CommunityDto에 @NoArgsConstructor 어노테이션 추가
    // 3.detail 템플릿 파일명을 d'a'tail이라고 해서 못찾음 오타 주의 ㅎㅋ

    // 커뮤니티 게시글 상세보기 -- /community/view/common/{seq}
    @GetMapping("/common/{seq}")
    public String detail(@PathVariable Long seq, Model model) {
        CommunityDto communityDtoDetail = communityService.getCommunity(Long.valueOf(seq));
        System.out.println("communityDtoDetail : "+communityDtoDetail);
        model.addAttribute("communityDtoDetail", communityDtoDetail);
        return "/community/detail";
    }


    // 커뮤니티 게시판 새글쓰기 -- /community/view/user/insert
    @GetMapping("/user/insert")
    public String insert() {
        return "/community/insert";
    }


    // 커뮤니티 게시판 업데이트 내역 상세 조회 -- /community/view/user/update/{seq}
    @GetMapping("/user/update/{seq}")
    public String update(@PathVariable Long seq, Model model) {
        CommunityDto communityDtoDetail = communityService.getCommunity(seq);
        model.addAttribute("communityDtoDetail", communityDtoDetail);
        return "/community/update";
    }

}
