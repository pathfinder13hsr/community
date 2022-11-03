package com.quiz.license.community.mapper;

import com.quiz.license.community.domain.dto.CommunityDto;
import com.quiz.license.community.domain.entity.CommunityEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityMapper {

    List<CommunityDto> getCommunityList(CommunityDto communityDto);
    CommunityDto getCommunity(Long seq);
    void insertCommunity(CommunityEntity communityEntity);
    void deleteCommunity(Long seq);
    void updateCommunity(CommunityEntity communityEntity);

}
