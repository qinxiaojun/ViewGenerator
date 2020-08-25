package com.example.constant;

import cn.org.rapid_framework.generator.GeneratorProperties;
import cn.org.rapid_framework.generator.provider.db.table.TableFactory;
import cn.org.rapid_framework.generator.provider.db.table.model.Table;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.common.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * com.example.constant
 * 前端页面列表要显示的字段
 *
 * @author mioto-qinxj
 * @date 2019/9/17
 */
@Slf4j
public class DisplayField {
    private static final String DISPLAY_FIELDS = "displayFields";

    private static final String COLUMN_NAME = "columnName";
    private static final String COLUMN_ALIAS = "columnAlias";


    /**
     * 设置显示的属性
     *
     * @param tableName
     * @param props     显示字段
     */
    public static void setDisplayField(String tableName, String props) {
        Table table = TableFactory.getInstance().getTable(tableName);
        JSONArray jsonArray = new JSONArray();
        if (StringUtils.isNotEmpty(props)) {
            String[] properties = StringUtil.upperCaseKey(props.contains(",") ? props.split(",") : new String[]{props});
            table.getColumns().iterator().forEachRemaining(column -> {
                int index = 0;
                for (String property : properties) {
                    index++;
                    if (Objects.equals(column.getColumnNameFirstLower(), property)) {
                        break;
                    }
                    if (index == properties.length) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put(COLUMN_NAME, column.getColumnNameFirstLower());
                        jsonObject.put(COLUMN_ALIAS, column.getColumnAlias());
                        jsonArray.add(jsonObject);
                    }
                }
            });
        }
        GeneratorProperties.setProperty(DISPLAY_FIELDS, jsonArray.toJSONString());
        log.info("{}列表显示的字符串列表:{}", tableName, GeneratorProperties.getProperty(DISPLAY_FIELDS));
    }
}
