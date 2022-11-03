package com.quiz.license.notice.domain.entity;

import com.quiz.license.user.domain.entity.AccountEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@Entity(name = "NOTICE")
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class NoticeEntity {
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
    public NoticeEntity(Long seq, String title, String content, AccountEntity account, LocalDateTime createDatetime, LocalDateTime modifyDatetime) {
        this.seq = seq;
        this.title = title;
        this.content = content;
        this.account = account;
        this.createDatetime = createDatetime;
        this.modifyDatetime = modifyDatetime;
    }
}
