package com.quiz.license.common;

import lombok.Getter;

@Getter
public class ApiFailExceptionMsg extends RuntimeException {

    private String code;
    private String reason;
    private String data;

    public ApiFailExceptionMsg(String code, String reason, Object data) {
        this.code = code;
        this.reason = reason;
        this.data = String.valueOf(data);
    }

    public ApiFailExceptionMsg(String code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public ApiFailExceptionMsg(ApiResponseCodeEnum apiResponseCodeEnum) {
        this.code = apiResponseCodeEnum.getCode();
        this.reason = apiResponseCodeEnum.getMsg();
    }


}
