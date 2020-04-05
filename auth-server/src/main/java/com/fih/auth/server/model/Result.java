package com.fih.auth.server.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletResponse;

/**
 * @author songxiaolong
 * @CreateDate: 2020-03-28
 * @Description: 响应实体类
 */
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
        return new Result(true, HttpServletResponse.SC_OK, "操作成功",data);
    }

    public static Result buildFail() {
        return buildFail("操作失败");
    }

    public static Result buildFail(String msg) {
        return buildFail(400, msg);
    }

    public static Result buildFail(Integer code, String msg) {
        return new Result(false, code, msg, null);
    }

}
