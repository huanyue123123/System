package com.example.springbootdemo.controller;

import com.example.springbootdemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {


    @Autowired
    private TestService testService;


    @RequestMapping("/test")
    public String test(String test){

        return test;
    }

    @RequestMapping("/downLoad")
    public void downLoad() throws Exception {

        testService.becomeExcel();
    }
}
