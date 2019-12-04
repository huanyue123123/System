package com.gm.wj.controller;

import com.gm.wj.common.SendMessagePropertis;
import com.gm.wj.entity.User;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultCode;
import com.gm.wj.result.ResultFactory;
import com.gm.wj.service.UserService;
import com.gm.wj.utils.RedisUtil;
import com.gm.wj.utils.SendMessageUtil;
import com.gm.wj.utils.VerifyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;

@Api(description = "登陆注册")
@RestController
public class LoginController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SendMessagePropertis prop;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登陆")
    @PostMapping(value = "/api/login")
    public Result login(@RequestBody User requestUser,HttpServletRequest request) {
        if (requestUser != null) {
            User user = userService.login(requestUser);
            if(user != null){
                return ResultFactory.buildResult(ResultCode.SUCCESS, "ok",user);
            }
            return ResultFactory.buildResult(ResultCode.FAIL, "账号或密码错误", requestUser);

        }
        return ResultFactory.buildResult(ResultCode.FAIL, "参数为空", requestUser);
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
    public String authentication() {
        return "身份认证成功";
    }

    @GetMapping("api/createImg")
    @ApiOperation(value = "图形验证码生成")
    public Result createImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            VerifyUtil randomValidateCode = new VerifyUtil();
            Map<String,Object> map = randomValidateCode.getRandcode(request, response,redisUtil);//输出验证码图片
            return ResultFactory.buildResult(ResultCode.SUCCESS,"ok",map);

        }catch (Exception e){
            e.printStackTrace();
            System.err.println("生成验证码异常");
            return ResultFactory.buildResult(ResultCode.FAIL,"error","");
        }
    }

    @PostMapping("api/checkVerify")
    @ApiOperation(value = "验证码校验")
    public Result createImg(String verify,String uuid,HttpServletRequest request) throws Exception {
        Object trueVerify = redisUtil.get(uuid);
        if(trueVerify!= null && trueVerify.equals(verify)){
            return ResultFactory.buildResult(ResultCode.SUCCESS, "验证码正确", "");
        }else if(trueVerify != null){
            return ResultFactory.buildResult(ResultCode.FAIL, "验证码不正确", "");
        }else{
            return ResultFactory.buildResult(ResultCode.NOT_FOUND,"请刷新验证码","");
        }
    }

    @PostMapping("api/getPhoneCode")
    @ApiOperation(value = "手机验证码")
    public Result getPhoneCode(@RequestBody User user) throws Exception {

        prop.setAimPhone(user.getUsername());
        prop.setContent("验证码为:"+ SendMessageUtil.getRandomCode(6) );
        Integer res  = SendMessageUtil.send(prop);
        return ResultFactory.buildResult(ResultCode.SUCCESS, ""+ res, prop);
    }

}
