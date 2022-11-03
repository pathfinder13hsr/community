package com.quiz.license.user.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@ToString
@Entity(name = "ACCOUNT") // 테이블명 대문자로 명시해주기
@NoArgsConstructor //기본생성자를 생성해줌
@DynamicInsert
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String userId;
    private String userPw;
    private String userEmail;
    private String userNickname;
    private String statusYn;
    private String adminYn;
    private LocalDateTime createDatetime;
    private LocalDateTime modifyDatetime;

    @Builder
    public AccountEntity(Long seq, String userId, String userPw, String userEmail, String userNickname, String statusYn, String adminYn, LocalDateTime createDatetime, LocalDateTime modifyDatetime) {
        this.seq = seq;
        this.userId = userId;
        this.userPw = userPw;
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.statusYn = statusYn;
        this.adminYn = adminYn;
        this.createDatetime = createDatetime;
        this.modifyDatetime = modifyDatetime;
    }

}
