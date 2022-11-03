package com.quiz.license.community.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.quiz.license.common.util.SessionUtil;
import com.quiz.license.community.domain.entity.CommunityEntity;
import com.quiz.license.user.domain.dto.AccountDto;
import com.quiz.license.user.domain.entity.AccountEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
//@NoArgsConstructor //No primary or single unique constructor found for class com.quiz.license.community.domain.dto.CommunityDto 에러나서 추가함
public class CommunityDto {

    private Long seq;
    private String title;
    private String content;
    private List<CommentDto> commentList = new ArrayList<>();
    private AccountDto account;

    private String userId;
    private String userEmail;
    private String userNickname;
    private String statusYn;

    private LocalDateTime createDatetime;
    private LocalDateTime modifyDatetime;

    public CommunityDto(){
    }

    @Builder(builderClassName = "bySearch", builderMethodName = "bySearch")
    public CommunityDto(CommunitySearchDto communitySearchDto) {
        this.seq = communitySearchDto.getSeq();
        this.title = communitySearchDto.getTitle();
        this.content = communitySearchDto.getContent();
    }

    @Builder(builderClassName = "byEntity", builderMethodName = "byEntity")
    public CommunityDto(CommunityEntity communityEntity) {
        this.seq = communityEntity.getSeq();
        this.title = communityEntity.getTitle();
        this.content = communityEntity.getContent();
        this.account = AccountDto.bySelect().accountEntity(communityEntity.getAccount()).build();
        this.createDatetime = communityEntity.getCreateDatetime();
        this.modifyDatetime = communityEntity.getModifyDatetime();
    }

    public CommunityEntity byInsert() {
       return CommunityEntity
               .builder()
               .account(AccountEntity.builder().seq(SessionUtil.getSeq()).build())
               .title(this.title)
               .content(this.content)
               .createDatetime(LocalDateTime.now())
               .build();
    }

    public CommunityEntity byUpdate() {
        return CommunityEntity
                .builder()
                .seq(this.seq)
                .account(AccountEntity.builder().seq(SessionUtil.getSeq()).build())
                .title(this.title)
                .content(this.content)
                .createDatetime(this.createDatetime)
                .modifyDatetime(LocalDateTime.now())
                .build();
    }

}
