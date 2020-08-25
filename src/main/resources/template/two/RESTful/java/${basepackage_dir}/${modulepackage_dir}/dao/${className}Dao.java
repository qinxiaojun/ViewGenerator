<#include "/macro.include">
<#assign className=table.className>
<#assign classNameLower=className?uncap_first>
package ${basepackage}.${modulepackage}.dao;

import ${basepackage}.${modulepackage}.model.${className};
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author ${author}
 * @date ${now?string('yyyy-MM-dd HH:mm:ss')}
 */
@Repository
public interface ${className}Dao{

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
    ${className} findByColumn(@Param("column") String column,@Param("value") Object value);

    /**
     * 新增对象
     * @param ${classNameLower}
     * @return
     */
    int insert(${className} ${classNameLower});

    /**
     * 新增对象,忽略空值
     * @param ${classNameLower}
     * @return
     */
    int insertIgnoreNull(${className} ${classNameLower});

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
    int deleteByColumn(@Param("column") String column,@Param("value") Object value);

    /**
     * 根据主键列表批量删除
     * @param ${table.pkColumn.columnNameLower}s
     * @return
     */
    int batchDelete(${table.pkColumn.javaType}[] ${table.pkColumn.columnNameLower}s);
}