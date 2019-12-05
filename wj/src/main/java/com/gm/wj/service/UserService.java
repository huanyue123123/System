package com.gm.wj.service;

import cn.com.webxml.ArrayOfString;
import cn.com.webxml.WeatherWS;
import cn.com.webxml.WeatherWSSoap;
import com.gm.wj.dao.UserMapper;
import com.gm.wj.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User login(User user){
        return userMapper.selectUserByEntity(user);
    }

    public List<String> weather(String place) throws Exception {
        WeatherWS factory = new WeatherWS();
        // 根据工厂创建一个WeatherWSSoap对象
        WeatherWSSoap weatherWSSoap = factory.getWeatherWSSoap();

        //查询上海这个城市的城市编号
        ArrayOfString cityInfo =  weatherWSSoap.getSupportCityString(place);
        List<String> lstCityInfo = cityInfo.getString();
        String CityCode = "";
        // 遍历天气预报信息
        for (String string : lstCityInfo) {
            String[] strings = string.split(",");
            if(strings[0] != null && strings[0].equals(place)){
                CityCode = strings[1];
            }
        }
        if(CityCode == null ||CityCode.equals("")){
            throw new Exception("未找到城市信息");
        }

        // 调用WebService提供的getWeather方法获取上海浦东的天气预报情况
        ArrayOfString weatherInfo = weatherWSSoap.getWeather(CityCode,"");
        List<String> lstWeatherInfo = weatherInfo.getString();
        return lstWeatherInfo;
    }
}
