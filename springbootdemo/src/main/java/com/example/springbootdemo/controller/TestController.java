package com.example.springbootdemo.controller;

import com.example.springbootdemo.service.MsgRecevier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private MsgRecevier msgRecevier;

    @RequestMapping("/content")
    public void content(String content){

        msgRecevier.process(content);
    }

    @RequestMapping("/test")
    public String test(String test){

        return test;
    }
}
