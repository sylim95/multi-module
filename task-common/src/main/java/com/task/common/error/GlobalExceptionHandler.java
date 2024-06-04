package com.task.common.error;

import com.task.common.common.code.ReturnCode;
import com.task.common.error.exception.BusinessException;
import com.task.common.error.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

/**
 * packageName    : com.task.common.error.exception
 * fileName       : GlobalExceptionHandler
 * author         : limsooyoung
 * date           : 6/3/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/3/24        limsooyoung       최초 생성
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException, {}", ExceptionUtils.getMessage(e));
        final ErrorResponse response = ErrorResponse.of(ReturnCode.ERROR_400, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ReturnCode.ERROR_400.getCode()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException e) {
        log.error("handleNoResourceFoundException, {}", ExceptionUtils.getMessage(e));
        final ErrorResponse response = ErrorResponse.of(ReturnCode.ERROR_404);
        return new ResponseEntity<>(response, HttpStatus.valueOf(ReturnCode.ERROR_404.getCode()));
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
        log.error("handleBusinessException, {}", ExceptionUtils.getMessage(e));
        final ReturnCode errorCode = e.getReturnCode();
        final List<ErrorResponse.Error> errors = ErrorResponse.Error.of(StringUtils.defaultString(e.getMessage()));
        final ErrorResponse response = ErrorResponse.of(errorCode, errors);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getCode()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("handleException, {}", ExceptionUtils.getMessage(e));
        final List<ErrorResponse.Error> errors = ErrorResponse.Error.of(StringUtils.defaultString(e.getMessage()));
        final ErrorResponse response = ErrorResponse.of(ReturnCode.ERROR_500, errors);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
