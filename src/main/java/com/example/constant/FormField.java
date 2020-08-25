package com.example.constant;

import cn.org.rapid_framework.generator.GeneratorProperties;
import cn.org.rapid_framework.generator.provider.db.table.TableFactory;
import cn.org.rapid_framework.generator.provider.db.table.model.Table;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.common.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * com.example.constant
 * 前端表单显示字段
 *
 * @author mioto-qinxj
 * @date 2019/9/17
 */
@Slf4j
public class FormField {
    //表单字段列表
    private static final String FORM_FIELD = "formFields";
    //组件名
    private static final String COLUMN_NAME = "columnName";
    //组件显示名称
    private static final String COLUMN_ALIAS = "columnAlias";
    //组件类型
    private static final String COMPONENT_TYPE = "componentType";

    private static JSONObject put(String columnName, String columnAlias, String componentType) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(COLUMN_NAME, columnName);
        jsonObject.put(COLUMN_ALIAS, columnAlias);
        jsonObject.put(COMPONENT_TYPE, componentType);
        return jsonObject;
    }

    public static void setFormField(String tableName, String props) {
        Table table = TableFactory.getInstance().getTable(tableName);
        JSONArray jsonArray = new JSONArray();
        if (StringUtils.isNotEmpty(props)) {
            JSONObject jsonObject = StringUtil.parse(JSONObject.parseObject(props));
            if (StringUtils.isNotEmpty(props)) {
                table.getColumns().iterator().forEachRemaining(column -> {
                    if (jsonObject.containsKey(column.getColumnNameFirstLower())) {
                        jsonArray.add(put(column.getColumnNameFirstLower(), column.getColumnAlias(), jsonObject.getString(column.getColumnNameFirstLower())));
                    }
                });
            }
        }
        GeneratorProperties.setProperty(FORM_FIELD, jsonArray.toJSONString());
        log.info("{}表单显示的字符串列表:{}", tableName, GeneratorProperties.getProperty(FORM_FIELD));
    }
}
