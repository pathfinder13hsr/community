package com.quiz.license.community.mapper;

import com.quiz.license.community.domain.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<CommentDto> getCommentList(Long boardSeq);

}
