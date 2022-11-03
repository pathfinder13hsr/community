package com.quiz.license.notice.domain.dto;

import com.quiz.license.common.util.SessionUtil;
import com.quiz.license.notice.domain.entity.NoticeEntity;

import com.quiz.license.user.domain.dto.AccountDto;
import com.quiz.license.user.domain.entity.AccountEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class NoticeDto {
    private Long seq;
    private String title;
    private String content;
    private AccountDto account;
    private LocalDateTime createDatetime;
    private LocalDateTime modifyDatetime;

    public NoticeDto(){};

    @Builder(builderClassName = "bySearch", builderMethodName = "bySearch")
    public NoticeDto(NoticeSearchDto noticeSearchDto) {
        this.seq = noticeSearchDto.getSeq();
        this.title = noticeSearchDto.getTitle();
        this.content = noticeSearchDto.getContent();
    }

    @Builder(builderClassName = "byEntityWithAccount", builderMethodName = "byEntityWithAccount")
    public NoticeDto(NoticeEntity noticeEntity, AccountEntity accountEntity) {
        this.seq = noticeEntity.getSeq();
        this.title = noticeEntity.getTitle();
        this.content = noticeEntity.getContent();
        this.account = AccountDto.bySelect().accountEntity(accountEntity).build();
        this.createDatetime = noticeEntity.getCreateDatetime();
        this.modifyDatetime = noticeEntity.getModifyDatetime();
    }
    @Builder(builderClassName = "byEntity", builderMethodName = "byEntity")
    public NoticeDto(NoticeEntity noticeEntity) {
        this.seq = noticeEntity.getSeq();
        this.title = noticeEntity.getTitle();
        this.content = noticeEntity.getContent();
        this.createDatetime = noticeEntity.getCreateDatetime();
        this.modifyDatetime = noticeEntity.getModifyDatetime();
    }


    public NoticeEntity byInsert() {
        return NoticeEntity
                .builder()
                .title(this.title)
                .content(this.content)
                .account(AccountEntity.builder().seq(SessionUtil.getSeq()).build())
                .createDatetime(LocalDateTime.now())
                .build();
    }

    public NoticeEntity byUpdate(NoticeEntity noticeEntity) {
        return NoticeEntity
                .builder()
                .seq(noticeEntity.getSeq())
                .title(this.title)
                .content(this.content)
                .account(AccountEntity.builder().seq(SessionUtil.getSeq()).build())
                .createDatetime(noticeEntity.getCreateDatetime())
                .modifyDatetime(LocalDateTime.now())
                .build();
    }
}
