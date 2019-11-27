package com.gm.wj.controller;

import com.gm.wj.entity.User;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultCode;
import com.gm.wj.result.ResultFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
@Api(description = "登陆注册")
@RestController
public class LoginController {


    @ApiOperation(value = "登陆")
    @PostMapping(value = "/api/login")
    public Result login(@RequestBody User requestUser) {
        if(requestUser!= null){
            return ResultFactory.buildResult(ResultCode.SUCCESS,"",requestUser);
        }
        return ResultFactory.buildResult(ResultCode.FAIL,"参数为空",requestUser);
    }

    @ApiOperation(value = "注册")
    @PostMapping("api/register")
    public Result register(@RequestBody String user) {
        return null;
    }

    @ApiOperation(value = "登出")
    @GetMapping("api/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        String message = "成功登出";
        return ResultFactory.buildSuccessResult(message);
    }

    @ApiOperation(value = "身份认证")
    @GetMapping(value = "api/authentication")
    public String authentication(){
        return "身份认证成功";
    }
}
