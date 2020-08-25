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
 * 前端页面要搜索的字段
 *
 * @author mioto-qinxj
 * @date 2019/9/16
 */
@Slf4j
public class SearchField {
    private static final String SEARCH_FIELD = "searchFields";

    private static final String COLUMN_NAME = "columnName";
    private static final String COLUMN_ALIAS = "columnAlias";
    private static final String COLUMN_SEARCH_TYPE = "columnSearchType";

    private static JSONObject put(String columnName, String columnAlias, String searchType) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(COLUMN_NAME, columnName);
        jsonObject.put(COLUMN_ALIAS, columnAlias);
        jsonObject.put(COLUMN_SEARCH_TYPE, searchType);
        return jsonObject;
    }

    /**
     * 设置搜索字段
     *
     * @param tableName
     * @param props
     */
    public static void setSearchField(String tableName, String props) {
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
        GeneratorProperties.setProperty(SEARCH_FIELD, jsonArray.toJSONString());
        log.info("{}列表搜索的字符串列表:{}", tableName, GeneratorProperties.getProperty(SEARCH_FIELD));
    }
}
