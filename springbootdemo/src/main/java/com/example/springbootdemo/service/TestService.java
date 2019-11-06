package com.example.springbootdemo.service;

import com.example.springbootdemo.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private TestDao testDao;

    public int selectCount(){
        return testDao.selectCount();
    }
}
