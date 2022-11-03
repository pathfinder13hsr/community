package com.quiz.license.community.service;

import com.quiz.license.community.domain.dto.CommentDto;
import com.quiz.license.community.domain.dto.CommunityDto;
import com.quiz.license.community.domain.entity.CommunityEntity;
import com.quiz.license.community.domain.repository.CommunityRepository;
import com.quiz.license.community.domain.dto.CommunitySearchDto;
import com.quiz.license.community.domain.repository.CommunityRepositorySupport;
import com.quiz.license.community.mapper.CommunityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Service
public class CommunityService {

    private final CommunityMapper communityMapper;
    private final CommunityRepository communityRepository;
    private final CommunityRepositorySupport communityRepositorySupport;
    private final CommentService commentService;


    @Autowired
    public CommunityService(CommunityMapper communityMapper, CommunityRepository communityRepository, CommunityRepositorySupport communityRepositorySupport, CommentService commentService) {
        this.communityMapper = communityMapper;
        this.communityRepository = communityRepository;
        this.communityRepositorySupport = communityRepositorySupport;
        this.commentService = commentService;
    }

//    // 일반
//    // mybatis 이용 리스트 불러오기
//    public List<CommunityDto> getCommunityList(CommunitySearchDto communitySearchDto) {
//        CommunityDto searchDto = new CommunityDto(communitySearchDto);
//        log.info("=== getCommunityList === searchDto : {}", searchDto);
//        List<CommunityDto> communityDtoList = communityMapper.getCommunityList(searchDto);
//        log.info("=== getCommunityList === communityDtoList : {}", communityDtoList);
//        return communityDtoList;
//    }

    //jpa pageable 이용 페이징 구현
    public List<CommunityDto> getCommunityList(CommunitySearchDto communitySearchDto) {
        return communityRepositorySupport.getCommunityList(communitySearchDto, communitySearchDto.getPage(), communitySearchDto.getPageSize());
    }

    public Long getListCount(CommunitySearchDto communitySearchDto){
        return communityRepositorySupport.getListCount(communitySearchDto);
    }


    public CommunityDto getCommunity(Long seq) {

        CommunityDto communityDto = communityMapper.getCommunity(seq);

        List<CommentDto> commentDtoList = commentService.getCommentList(communityDto.getSeq());

        if(!ObjectUtils.isEmpty(commentDtoList)){
            communityDto.setCommentList(commentDtoList);
        }

        log.info("=== communityDto === communityDto : {}", communityDto);
        return communityDto;
    }

    public void insertCommunity(CommunityDto communityDto) {
        CommunityEntity communityEntity = communityDto.byInsert();
        log.info("=== insertCommunity === communityEntity : {}", communityEntity);
        communityMapper.insertCommunity(communityEntity);
    }

    public void deleteCommunity(Long seq) {
        communityMapper.deleteCommunity(seq);
    }

    public void updateCommunity(CommunityDto communityDto) {
        CommunityEntity communityEntity = communityDto.byUpdate();
        log.info("=== updateCommunity === communityEntity : {}", communityEntity);
        communityMapper.updateCommunity(communityEntity);
    }


}
