package com.task.common.error.exception;

import com.task.common.common.code.ReturnCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : com.task.common.error.exception
 * fileName       : BusinessException
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
public class BusinessException extends RuntimeException {

    private ReturnCode returnCode;

    public BusinessException(String message, ReturnCode returnCode) {
        super(message);
        this.returnCode = returnCode;
    }

    public static BusinessException of(String message, ReturnCode returnCode) {
        return new BusinessException(message, returnCode);
    }
}
