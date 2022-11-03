package com.quiz.license.common;

import lombok.Getter;

@Getter
public enum ApiResponseCodeEnum {

    SUCCESS("200", "성공"),
    PERMISSION_FAIL("996", "권한이 없습니다."),
    SEARCH_FAIL("997", "조회된 정보가 없습니다."),
    SYSTEM_FAIL("998", "시스템 오류. 관리자 문의 부탁드립니다."),
    FAIL("999", "실패");

    private String code;
    private String msg;

    ApiResponseCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
