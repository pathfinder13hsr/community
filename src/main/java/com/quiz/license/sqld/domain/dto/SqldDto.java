package com.quiz.license.sqld.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SqldDto {
    private Long seq; // Long 타입으로 해주고 DB를 BigInt 로 생성해주는게 가장 좋다.
    private String title;
    private String content;
}
