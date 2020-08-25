package com.example.common;

import com.example.constant.ResultEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * com.example.common
 *
 * @author mioto-qinxj
 * @date 2019/10/17
 */
@Data
public class ResultData<T> implements Serializable {

    /**
     * HTTP状态码
     */
    private String code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private T data;

    public ResultData(ResultEnum resultEnum) {
        this.code = resultEnum.code();
        this.msg = resultEnum.msg();
    }

    public ResultData(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultData(ResultEnum resultEnum, T data) {
        this(resultEnum);
        this.data = data;
    }

    public ResultData(String code, String msg, T data) {
        this(code, msg);
        this.data = data;
    }

    public ResultData(T data) {
        this.data = data;
    }
}
