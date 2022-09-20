package com.quiz.license.sqld.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@ToString
@Entity
@NoArgsConstructor
@DynamicUpdate
@Table(name = "SQLD")
public class SqldEntity {

    // 일단 간략하게 테스트용으로만 작성.
    @Id // 이 내용을 PK로 사용하겠다는 의미.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SEQ 생성 방법 DB에 위임. 다른 옵션도 있음.
    private Long seq; // Long 타입으로 해주고 DB를 BigInt 로 생성해주는게 가장 좋다.
    private String title;
    private String content;

}
