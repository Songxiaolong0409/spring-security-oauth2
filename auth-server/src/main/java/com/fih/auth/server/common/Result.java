package com.fih.auth.server.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author songxiaolong
 * @CreateDate: 2020-03-28
 * @Description: 响应实体类
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    //是否成功
    private boolean success;

    //返回码
    private int code;

    //返回信息
    private String msg;

    //返回数据
    private Object data;

    public static Result build() {
        return build(null);
    }

    public static Result build(Object data) {
        Result result= new Result(true, HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),data);
        log.info("接口成功返回:{}",result);
        return result;
    }

    public static Result buildFail() {
        return buildFail("操作失败");
    }

    public static Result buildFail(String msg) {
        return buildFail(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase()+":"+msg);
    }

    public static Result buildFail(Integer code, String msg) {
        Result result= new Result(false, code, msg, String.valueOf(new Date().getTime()));
        log.error("接口失败返回:{}",result);
        return result;
    }

    public static Result buildFail400(String message){
        return Result.buildFail(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase()+":"+message);
    }

    public static Result buildFail401(String message){
        return Result.buildFail(HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase()+":"+message);
    }

    public static Result buildFail500(String message){
        return Result.buildFail(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()+":"+message);
    }

    public static Result buildFail600(String message){
        return Result.buildFail(600,message);
    }


    public static void print(HttpServletResponse response,Result result){
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), result);
        } catch (Exception e) {
            throw new InvalidGrantException(e.toString());
        }
    }

    public static void print200(HttpServletResponse response,String message){
        print(response,build(message));
    }

    public static void print401(HttpServletResponse response,String message){
        print(response,buildFail401(message));
    }
}
