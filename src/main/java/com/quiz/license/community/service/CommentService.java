package com.quiz.license.community.service;

import com.quiz.license.common.ApiFailExceptionMsg;
import com.quiz.license.common.ApiResponseCodeEnum;
import com.quiz.license.common.util.SessionUtil;
import com.quiz.license.community.domain.dto.CommentDto;
import com.quiz.license.community.domain.entity.CommentEntity;
import com.quiz.license.community.domain.repository.CommentRepository;
import com.quiz.license.community.mapper.CommentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    // 댓글 등록
    public void insertComment(CommentDto commentDto) {
        CommentEntity commentEntity = commentDto.byInsert();
        commentRepository.save(commentEntity);
    }

    // 댓글 리스트 조회.
    public List<CommentDto> getCommentList(Long boardSeq) {

        List<CommentDto> commentDtoList = commentMapper.getCommentList(boardSeq);

        return commentDtoList;
    }

    // 댓글 수정.
    public void updateComment(CommentDto commentDto) {

        // 수정 전 등록되어 있는 내용 조회해 보고 없으면 throw 하는 부분.
        // 이부분 오류나면 로그인 세션이 없어서 걸리는 문제. SessionUtil.getSeq() -> 정상적인 Long 값으로 변경 하면 일시적으로 테스트 가능.
        CommentEntity commentEntity
                = Optional.ofNullable(commentRepository
                        .findBySeqAndDelYnAndAccount_Seq(commentDto.getSeq(), "N", SessionUtil.getSeq()))
                .orElseThrow(() -> new ApiFailExceptionMsg(ApiResponseCodeEnum.SEARCH_FAIL));

        // 회원의 요청일때만 댓글 등록자 검사하는데 등록자 수정자 일치하지 않으면 throw
        if (Objects.equals("user", SessionUtil.getUserType())
                && !Objects.equals(commentEntity.getAccount().getSeq(), SessionUtil.getSeq())) {
            new ApiFailExceptionMsg(ApiResponseCodeEnum.PERMISSION_FAIL);
        }

        CommentDto getCommentDto = new CommentDto(commentEntity);
        CommentEntity updateEntity = commentDto.byUpdate(getCommentDto);

        commentRepository.save(updateEntity);
    }

    // 댓글 삭제.
    public void deleteComment(Long seq) {
        CommentEntity commentEntity = commentRepository.findBySeqAndDelYn(seq, "N");

        String comment = "삭제 된 댓글입니다.";

        if (Objects.equals("user", SessionUtil.getUserType())) {
            // 회원이 삭제를 요청 할 때만 본인의 댓글인지 확인.
            if (!Objects.equals(SessionUtil.getSeq(), commentEntity.getAccount().getSeq())) {
                new ApiFailExceptionMsg(ApiResponseCodeEnum.PERMISSION_FAIL);
            }
            comment = "사용자가 삭제 한 댓글입니다.";
        } else if (Objects.equals("admin", SessionUtil.getUserType())) {
            comment = "관리자에 의해 삭제 된 댓글입니다.";
        } else {
            new ApiFailExceptionMsg(ApiResponseCodeEnum.PERMISSION_FAIL);
        }

        CommentDto getCommentDto = new CommentDto(commentEntity);
        CommentEntity deleteEntity = getCommentDto.byDelete(getCommentDto, comment);

        commentRepository.save(deleteEntity);
    }

}
