package com.dabai.mytwo.util;

import java.util.LinkedHashMap;

/**
 * @author dabai:
 * <p>
 * 类说明  返回简单数据
 */
public class ResponseData extends LinkedHashMap<String, Object> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ResponseData result(String key, Object value) {
        this.put(key, value);
        return this;
    }

    public ResponseData success() {
        this.put("code", 200);
        this.put("message", "success");
        return this;
    }

    public ResponseData fail() {
        this.put("code", 400);
        this.put("message", "fail");
        return this;
    }

    public ResponseData unauthorized() {
        this.put("code", 401);
        this.put("message", "the current user is unauthorized");
        return this;
    }

    public ResponseData code(int code) {
        return result("code", code);
    }

    public ResponseData message(String message) {
        return result("message", message);
    }

    public ResponseData data(Object data) {
        return result("data", data);
    }

}
