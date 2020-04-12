package org.cis.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class TestBean {

    private String msg;

    public TestBean() {
        this.msg = "TetsBean";
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
