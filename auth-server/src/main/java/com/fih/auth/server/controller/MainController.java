package com.fih.auth.server.controller;

import com.fih.auth.server.model.CustomRequest;
import com.fih.auth.server.model.Result;
import com.fih.auth.server.service.IOauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author songxiaolong
 * @CreateDate: 2020-03-28
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class MainController {

    @Autowired
    private IOauthClientDetailsService oauthClientDetailsService;

    @PostMapping("/getSmsCode")
    public Result getSmsCode(@RequestBody @Valid CustomRequest customRequest) {
        return oauthClientDetailsService.getSmsCode(customRequest);
    }

}
