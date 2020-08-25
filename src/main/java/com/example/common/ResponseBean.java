package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ResponseBean
 *
 * @author dolyw.com
 * @date 2018/8/30 11:39
 */
@Data
@AllArgsConstructor
public class ResponseBean {
    /**
     * HTTP状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private Object data;


    public ResponseBean(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
