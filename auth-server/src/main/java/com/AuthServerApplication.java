package com;

import com.fih.auth.server.common.Result;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author songxiaolong
 * @CreateDate: 2020-03-28
 */
@EnableAuthorizationServer
@SpringBootApplication
@ControllerAdvice
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        return Result.buildFail500(e.getMessage());
    }

    /**
     *  校验错误拦截处理
     *
     * @param exception 错误信息集合
     * @return 错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result validationBodyException(MethodArgumentNotValidException exception){
        BindingResult bindingResult = exception.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();

        StringBuffer buffer = new StringBuffer();

        allErrors.forEach(objectError -> {
            FieldError fieldError = (FieldError)objectError;
            buffer.append(fieldError.getField()).append(fieldError.getDefaultMessage()).append(" ;");
        });

        return Result.buildFail600(buffer.toString());
    }

    /**
     * 参数类型转换错误
     *
     * @param exception 错误
     * @return 错误信息
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseBody
    public Result parameterTypeException(HttpMessageConversionException exception){
        return Result.buildFail400(exception.getMessage());
    }
}
