<#include "/macro.include">
<#assign className=table.className>
<#assign classNameLower=className?uncap_first>
package ${basepackage}.${modulepackage}.service.impl;

import org.springframework.stereotype.Service;

import ${basepackage}.${modulepackage}.dao.${className}Dao;
import ${basepackage}.${modulepackage}.model.${className};
import ${basepackage}.${modulepackage}.service.I${className}Service;
import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author ${author}
 * @date ${now?string('yyyy-MM-dd HH:mm:ss')}
 */
@Service("${classNameLower}Service")
public class ${className}ServiceImpl implements I${className}Service{

    @Resource
    private ${className}Dao ${classNameLower}Dao;

    @Override
    public List<${className}> findList(${className} ${classNameLower}) {
        return ${classNameLower}Dao.findList(${classNameLower});
    }

    @Override
    public int insert(${className} ${classNameLower}) {
        return ${classNameLower}Dao.insert(${classNameLower});
    }

    @Override
    public int insertIgnoreNull(${className} ${classNameLower}) {
        return ${classNameLower}Dao.insertIgnoreNull(${classNameLower});
    }

    @Override
    public int update(${className} ${classNameLower}) {
        return ${classNameLower}Dao.update(${classNameLower});
    }

    @Override
    public int updateIgnoreNull(${className} ${classNameLower}) {
        return ${classNameLower}Dao.updateIgnoreNull(${classNameLower});
    }

    @Override
    public ${className} findByColumn(String column, Object value) {
        return ${classNameLower}Dao.findByColumn(column,value);
    }

    @Override
    public int deleteByColumn(String column, Object value) {
        return ${classNameLower}Dao.deleteByColumn(column,value);
    }

    @Override
    public int batchDelete(${table.pkColumn.javaType}[] ${table.pkColumn.columnNameLower}s) {
        return ${classNameLower}Dao.batchDelete(${table.pkColumn.columnNameLower}s);
    }
}