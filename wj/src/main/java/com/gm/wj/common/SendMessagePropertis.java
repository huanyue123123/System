package com.gm.wj.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SendMessagePropertis {

    @Value("幻月qaz")
    private String account;

    @Value("d41d8cd98f00b204e980")
    private String key;

    private String aimPhone;

    private String content;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAimPhone() {
        return aimPhone;
    }

    public void setAimPhone(String aimPhone) {
        this.aimPhone = aimPhone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
