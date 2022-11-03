package com.quiz.license.community.domain.entity;

import com.quiz.license.user.domain.entity.AccountEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor
@Table(name = "COMMENT")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private Long boardSeq;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_SEQ", nullable = false)
    private AccountEntity account;
    private String comment;
    private String delYn;
    private LocalDateTime createDatetime;
    private LocalDateTime modifyDatetime;

    @Builder
    public CommentEntity(Long seq, Long boardSeq, AccountEntity account, String comment, String delYn, LocalDateTime createDatetime, LocalDateTime modifyDatetime) {
        this.seq = seq;
        this.boardSeq = boardSeq;
        this.account = account;
        this.comment = comment;
        this.delYn = delYn;
        this.createDatetime = createDatetime;
        this.modifyDatetime = modifyDatetime;
    }

}
