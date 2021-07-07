<#include "/macro.include"/>
<#assign className=table.className>
<#assign classNameLower=className?uncap_first>
package ${basepackage}.${modulepackage}.model;

import java.io.Serializable;
<#list table.columns as column>
<#if column.isDateTimeColumn>
import java.util.Date;
<#break>
</#if>
</#list>
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * ${tableComment}实体
 * @author ${author}
 * @date ${now?string('yyyy-MM-dd HH:mm:ss')}
 */
@Data
@ApiModel(value = "${tableComment}")
public class ${className} implements Serializable{

    private static final long serialVersionUID=${className}.class.getName().hashCode();
    
    <@generateFieldsNew/>
    
    <#macro generateFieldsNew>
    <#--自定义函数，根据数据库中表字段生成java中的属性-->
    <#list table.columns as column>

    <#if column.isDateTimeColumn>
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    </#if>
    @ApiModelProperty(value = "${column.columnAlias}")
    private ${column.javaType} ${column.columnNameLower};
    </#list>
    </#macro>
}