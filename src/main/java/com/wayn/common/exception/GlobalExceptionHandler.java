package com.wayn.common.exception;

import com.wayn.common.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Objects;

/**
 * 全局异常处理器
 *
 * @author ruoyi
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public R businessException(BusinessException e) {
        log.error(e.getMessage(), e);
        if (Objects.isNull(e.getCode())) {
            return R.error(e.getMessage());
        }
        return R.error(e.getCode(), e.getMessage());
    }

    /**
     * 404异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public R handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return R.error(HttpStatus.NOT_FOUND.value(), "路径不存在，请检查路径是否正确");
    }

    /**
     * 认证失败异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    public R handleAuthorizationException(AccessDeniedException e) {
        log.error(e.getMessage());
        return R.error(HttpStatus.FORBIDDEN.value(), "没有权限，请联系管理员授权");
    }
    /**
     * 全局异常
     */
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        log.error(e.getMessage(), e);
        return R.error(e.getMessage());
    }
}
