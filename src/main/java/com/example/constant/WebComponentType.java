package com.example.constant;

/**
 * com.example.constant
 * 前端组件类型
 *
 * @author mioto-qinxj
 * @date 2019/9/17
 */
public enum WebComponentType {

    //文本框
    TEXT("text"),
    //单个日期
    SINGLE_DATE("singleDate"),
    //下拉列表
    SELECT("select"),
    //时间段
    DATE_DURING("dateDuring"),
    //单选框
    RADIO("radio"),
    //复选框
    CHECKBOX("checkbox"),
    //开关
    SWITCH("switch"),
    //滑块
    SLIDER("slider"),
    //文本域
    TEXTAREA("textarea");

    private String value;

    public String getValue() {
        return value;
    }

    WebComponentType(String value) {
        this.value = value;
    }
}
