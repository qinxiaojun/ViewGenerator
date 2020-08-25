package com.example.constant;

/**
 * com.example.constant
 *
 * @author mioto-qinxj
 * @date 2019/10/15
 */
public enum ResultEnum {

    SUCCESS("0000", "请求成功"),
    INSERT_ERROR("1111", "数据添加失败"),
    UPDATE_ERROR("1112", "数据更新失败"),
    DELETE_ERROR("1113", "数据删除失败"),
    ;

    private String code;
    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String msg() {
        return msg;
    }

    public String code() {
        return code;
    }
}
