<#include "/macro.include">
<#assign className=table.className>
<#assign classNameLower=className?uncap_first>
package ${basepackage}.${modulepackage}.service;

import ${basepackage}.${modulepackage}.model.${className};
import java.util.List;

/**
 * I${className}Service
 *
 * @author ${author}
 * @date ${now?string('yyyy-MM-dd HH:mm:ss')}
 */
public interface I${className}Service{

    /**
     * 根据条件查询列表
     * @param ${classNameLower}
     * @return
     */
    List<${className}> findList(${className} ${classNameLower});

    /**
     * 根据列名和对应的值查询对象
     * @param column
     * @param value
     * @return
     */
    ${className} findByColumn(String column, Object value);

    /**
     * 新增对象
     * @param ${classNameLower}
     * @return
     */
    int insert(${className} ${classNameLower});

    /**
     * 修改对象
     * @param ${classNameLower}
     * @return
     */
    int update(${className} ${classNameLower});

    /**
     * 修改对象,忽略空值
     * @param ${classNameLower}
     * @return
     */
    int updateIgnoreNull(${className} ${classNameLower});

    /**
     * 根据列名和对应的值删除对象
     * @param column
     * @param value
     * @return
     */
    int deleteByColumn(String column, Object value);

    /**
     * 根据主键列表批量删除
     * @param ${table.pkColumn.columnNameLower}s
     * @return
     */
    int batchDelete(${table.pkColumn.javaType}[] ${table.pkColumn.columnNameLower}s);
}