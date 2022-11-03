package com.quiz.license.notice.controller;

import com.quiz.license.common.vo.PagingVo;
import com.quiz.license.notice.domain.dto.NoticeDto;
import com.quiz.license.notice.domain.dto.NoticeSearchDto;
import com.quiz.license.notice.domain.entity.NoticeEntity;
import com.quiz.license.notice.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/notice/view")
public class NoticeViewController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeViewController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    // TODO. 두번째 세번째 패턴을 확인해서 권한 검증 진행.
    // user : 사용자, admin : 관리자, common : AllOpen

    //공지사항 게시판 리스트 불러오기 -- /notice/view/common/list
//    @GetMapping("/common/list")
//    public String list(Model model) {
//        List<NoticeDto> noticeDtoList = noticeService.getNoticeList();
//        model.addAttribute("noticeDtoList", noticeDtoList);
//        return "/notice/list";
//    }

    // 사용안함
//    @GetMapping("/common/list")
//    public String list(@PageableDefault Pageable pageable, Model model){
//        Page<NoticeEntity> noticeEntityList = noticeService.getNoticeList(pageable);
//        model.addAttribute("noticeEntityList", noticeEntityList);
//        int start = (int) (Math.floor(noticeEntityList.getNumber()/10)*10 + 1);
//        int last=start + 9 < noticeEntityList.getTotalPages() ? start + 9 : noticeEntityList.getTotalPages();
//        model.addAttribute("start", start);
//        model.addAttribute("last", last);
//        System.out.println("start>>>>"+start);
//        System.out.println("last>>>>"+last);
//        return "/notice/list";
//    }

    @GetMapping("/common/list")
    public String list(@ModelAttribute("noticeSearchDto") NoticeSearchDto noticeSearchDto, Model model){

        List<NoticeDto> noticeList = noticeService.getNoticeList(noticeSearchDto);
        long count = noticeService.getListCount(noticeSearchDto);
        log.info("noticeList : " + noticeList.toString());
        log.info("noticeCount : " + count);
        model.addAttribute("noticeEntityList", noticeList);
        model.addAttribute("paging", new PagingVo(noticeSearchDto.getPage(), noticeSearchDto.getPageSize(), count));

        return "/notice/list";
    }



    //공지사항 상세보기 -- /notice/view/common/{seq}
    @GetMapping("/common/{seq}")
    public String detail(@PathVariable Long seq, Model model) {
        NoticeDto noticeDtoDetail = noticeService.getNotice(seq);
        model.addAttribute("noticeDtoDetail", noticeDtoDetail);
        return "/notice/detail";
    }

    //공지사항 수정 -- /notice/view/admin/update/{seq}
    @GetMapping("/admin/update/{seq}")
    public String update(@PathVariable Long seq, Model model) {
        NoticeDto noticeDtoDetail = noticeService.getNotice(seq);
        model.addAttribute("noticeDtoDetail", noticeDtoDetail);
        return "/notice/update";
    }

    //공지사항 게시판 새글쓰기 -- /notice/view/admin/insert
    @GetMapping("/admin/insert")
    public String insert() {
        return "/notice/insert";
    }

}
