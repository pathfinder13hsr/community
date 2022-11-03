package com.quiz.license.notice.service;

import com.quiz.license.community.domain.dto.CommunitySearchDto;
import com.quiz.license.notice.domain.dto.NoticeDto;
import com.quiz.license.notice.domain.dto.NoticeSearchDto;
import com.quiz.license.notice.domain.entity.NoticeEntity;
import com.quiz.license.notice.domain.repository.NoticeRepository;
import com.quiz.license.notice.domain.repository.NoticeRepositorySupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final NoticeRepositorySupport noticeRepositorySupport;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository, NoticeRepositorySupport noticeRepositorySupport) {
        this.noticeRepository = noticeRepository;
        this.noticeRepositorySupport = noticeRepositorySupport;
    }

//    public List<NoticeDto> getNoticeList() {
//
//        List<NoticeEntity> noticeEntityList = noticeRepository.findAll();
//
//        List<NoticeDto> noticeDtoList = new ArrayList<>(); // return
//        for (NoticeEntity noticeEntity : noticeEntityList) {
//            NoticeDto noticeDto = NoticeDto.byEntity().noticeEntity(noticeEntity).build();
//            noticeDtoList.add(noticeDto);
//        }
//
//        return noticeDtoList;
//    }

    // 사용 안함.
//    public Page<NoticeEntity> getNoticeList(Pageable pageable) {
//        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
//        pageable = PageRequest.of(page, 10, Sort.by("seq").descending());
//        return noticeRepository.findAll(pageable);
//    }

    public List<NoticeDto> getNoticeList(NoticeSearchDto noticeSearchDto) {
        return noticeRepositorySupport.getNoticeList(noticeSearchDto, noticeSearchDto.getPage(), noticeSearchDto.getPageSize());
    }

    public Long getListCount(NoticeSearchDto noticeSearchDto){
        return noticeRepositorySupport.getListCount(noticeSearchDto);
    }

    public void insertNotice(NoticeDto noticeDto) {
        NoticeEntity noticeEntity = noticeDto.byInsert();
        noticeRepository.save(noticeEntity);
    }

    //공지사항 게시글 상세
    public NoticeDto getNotice(Long seq) {
        return noticeRepositorySupport.getNotice(seq);
    }

    public void deleteNotice(Long seq) {
        noticeRepository.deleteById(seq);
    }

    public void updateNotice(NoticeDto noticeDto) {
        NoticeEntity noticeEntity = noticeRepository.findBySeq(noticeDto.getSeq());
        log.info("=== updateNotice === noticeEntity : {}", noticeEntity);
        noticeRepository.save(noticeDto.byUpdate(noticeEntity));
    }


}
