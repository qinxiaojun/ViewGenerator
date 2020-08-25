package com.example.common;

import lombok.Data;

/**
 * com.example.common
 * 接收前端页面忽略、搜索字段
 *
 * @author mioto-qinxj
 * @date 2019/9/16
 */
@Data
public class GeneratorVO {
    private String ignoreProp;
    private String searchProp;
    private String formProp;
    private String sortProp;
}
