package com.task.common.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

/**
 * packageName    : com.task.common.common.code
 * fileName       : ReturnCode
 * author         : limsooyoung
 * date           : 6/3/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/3/24        limsooyoung       최초 생성
 */
@Getter
@AllArgsConstructor
public enum ReturnCode {
    /** 정상 처리 */
    SUCCESS(HttpStatus.OK.value(), "Success"),

    /** 요청 오류(Bad Request) */
    ERROR_400(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()),

    /** Unauthorized */
    ERROR_401(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase()),

    /** Forbidden */
    ERROR_403(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase()),

    /** Not Found */
    ERROR_404(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()),

    /** Method Not Allowed */
    ERROR_405(HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase()),

    /** Request Timeout */
    ERROR_408(HttpStatus.REQUEST_TIMEOUT.value(), HttpStatus.REQUEST_TIMEOUT.getReasonPhrase()),

    /** MediaType 오류 */
    ERROR_415(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase()),

    /** 토큰 갱신 오류 */
    ERROR_426(HttpStatus.UPGRADE_REQUIRED.value(), HttpStatus.UPGRADE_REQUIRED.getReasonPhrase()),

    /** 서버 실패 */
    ERROR_500(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())

    ;

    private Integer code;

    private String message;

    public static ReturnCode getCode(Integer code) {
        if(code == null) return null;

        return Arrays.stream(ReturnCode.values())
                .filter(v -> v.getCode().equals(code))
                .findAny().orElse(null);
    }
}
