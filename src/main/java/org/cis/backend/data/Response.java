package org.cis.backend.data;

public class Response {
    private int ret;
    private String message;

    public Response() {
        ret = 0;
        message = "";
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLog() {
        return ret + ":" + message;
    }
}
