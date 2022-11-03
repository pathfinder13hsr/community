package com.quiz.license.community.domain.dto;

import com.quiz.license.common.util.SessionUtil;
import com.quiz.license.community.domain.entity.CommentEntity;
import com.quiz.license.user.domain.dto.AccountDto;
import com.quiz.license.user.domain.entity.AccountEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class CommentDto {

    private Long seq;
    private Long boardSeq;
    private AccountDto account;

    private String userId;
    private String userEmail;
    private String userNickname;
    private LocalDateTime commentCreateDatetime;
    private LocalDateTime commentModifyDatetime;
    private String comment;
    private String delYn;
    private LocalDateTime createDatetime;
    private LocalDateTime modifyDatetime;

    public CommentDto(){
    }

    @Builder(builderClassName = "byEntity", builderMethodName = "byEntity")
    public CommentDto(Long seq, Long boardSeq, AccountDto account, String comment, String delYn, LocalDateTime createDatetime, LocalDateTime modifyDatetime) {
        this.seq = seq;
        this.boardSeq = boardSeq;
        this.account = account;
        this.comment = comment;
        this.delYn = delYn;
        this.createDatetime = createDatetime;
        this.modifyDatetime = modifyDatetime;
    }

    @Builder(builderClassName = "bySelect", builderMethodName = "bySelect")
    public CommentDto(CommentEntity commentEntity){
        this.seq = commentEntity.getSeq();
        this.boardSeq = commentEntity.getBoardSeq();
        this.account = new AccountDto(commentEntity.getAccount());
        this.comment = commentEntity.getComment();
        this.delYn = commentEntity.getDelYn();
        this.createDatetime = commentEntity.getCreateDatetime();
        this.modifyDatetime = commentEntity.getModifyDatetime();
    }

    public CommentEntity byInsert(){
        return CommentEntity
                .builder()
                .boardSeq(this.boardSeq)
                .account(AccountEntity.builder().seq(SessionUtil.getSeq()).build())
                .comment(this.comment)
                .delYn("N")
                .createDatetime(LocalDateTime.now())
                .build();
    }


    public CommentEntity byUpdate(CommentDto oldDto){
        return CommentEntity
                .builder()
                .seq(oldDto.getSeq())
                .boardSeq(oldDto.getBoardSeq())
                .account(AccountEntity.builder().seq(oldDto.getAccount().getSeq()).build())
                .comment(this.comment)
                .delYn("N")
                .createDatetime(oldDto.getCreateDatetime())
                .modifyDatetime(LocalDateTime.now())
                .build();
    }


    public CommentEntity byDelete(CommentDto oldDto, String comment){
        return CommentEntity
                .builder()
                .seq(oldDto.getSeq())
                .boardSeq(oldDto.getBoardSeq())
                .account(AccountEntity.builder().seq(oldDto.getAccount().getSeq()).build())
                .comment(comment)
                .delYn("Y")
                .createDatetime(oldDto.getCreateDatetime())
                .modifyDatetime(LocalDateTime.now())
                .build();
    }


}
