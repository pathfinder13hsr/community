package com.quiz.license.community.domain.entity;

import com.quiz.license.user.domain.entity.AccountEntity;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@ToString // 나중에 삭제
@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor
@Table(name = "COMMUNITY")
public class CommunityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_SEQ", nullable = false)
    private AccountEntity account;


    private LocalDateTime createDatetime;
    private LocalDateTime modifyDatetime;

    @Builder
    public CommunityEntity(Long seq, String title, String content, AccountEntity account, LocalDateTime createDatetime, LocalDateTime modifyDatetime) {
        this.seq = seq;
        this.title = title;
        this.content = content;
        this.account = account;
        this.createDatetime = createDatetime;
        this.modifyDatetime = modifyDatetime;
    }
}
