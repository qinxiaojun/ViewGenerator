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
 *
 * @author mioto-qinxj
 * @date 2019/10/16
 */
@Slf4j
public class SortField {
    private static final String SORT_FIELDS = "sortFields";

    private static final String COLUMN_NAME = "columnName";
    private static final String SORT_BY = "sortBy";

    private static JSONObject put(String columnName, String sortBy) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(COLUMN_NAME, columnName);
        jsonObject.put(SORT_BY, sortBy);
        return jsonObject;
    }

    public static void setSortField(String tableName, String props) {
        Table table = TableFactory.getInstance().getTable(tableName);
        JSONArray jsonArray = new JSONArray();
        if (StringUtils.isNotEmpty(props)) {
            JSONObject jsonObject = StringUtil.parse(JSONObject.parseObject(props));
            if (StringUtils.isNotEmpty(props)) {
                table.getColumns().iterator().forEachRemaining(column -> {
                    if (jsonObject.containsKey(column.getColumnNameFirstLower())) {
                        jsonArray.add(put(column.getColumnNameFirstLower(), jsonObject.getString(column.getColumnNameFirstLower())));
                    }
                });
            }
        }
        GeneratorProperties.setProperty(SORT_FIELDS, jsonArray.toJSONString());
        log.info("{}表单显示的字符串列表:{}", tableName, GeneratorProperties.getProperty(SORT_FIELDS));
    }
}
