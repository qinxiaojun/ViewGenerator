<#include "/macro.include">
<#assign className=table.className>
<#assign classNameLower=className?uncap_first>
package ${basepackage}.${modulepackage}.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.*;
import ${basepackage}.${modulepackage}.model.${className};
import ${basepackage}.${modulepackage}.service.I${className}Service;
import ${basepackage}.result.ResultData;
import ${basepackage}.result.SystemTip;
import ${basepackage}.component.BasePager;
import ${basepackage}.exception.BasicException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author ${author}
 * @date ${now?string('yyyy-MM-dd HH:mm:ss')}
 */
@RestController
@RequestMapping("${classNameLower}")
@Api(tags = "${tableComment}管理")
public class ${className}Controller {
    @Resource
    private I${className}Service ${classNameLower}Service;

    @GetMapping
    @ApiOperation(value="查询${tableComment}列表",response = ${className}.class)
    public ResultData list (HttpServletRequest request,${className} ${classNameLower}){
        return ResultData.success(${classNameLower}Service.findList(${classNameLower}));
    }

    @GetMapping("/pager")
    @ApiOperation(value="分页查询${tableComment}",response = ${className}.class)
    public ResultData pager (HttpServletRequest request, ${className} ${classNameLower},BasePager basePager){
        PageHelper.startPage(basePager.getPage(), basePager.getRows(), basePager.getSortBy());
        List<${className}> list = ${classNameLower}Service.findList(${classNameLower});
        PageInfo<${className}> pageInfo = new PageInfo<>(list);
        Map<String, Object> result = new HashMap<>(4);
        result.put("count", pageInfo.getTotal());
        result.put("list", pageInfo.getList());
        return ResultData.success(result);
    }

    @GetMapping("/{column}/{value}")
    @ApiOperation(value="通过字段名查询${tableComment}",response = ${className}.class)
    public ResultData findByColumn (@PathVariable("column")String column,@PathVariable("value")Object value){
        return ResultData.success(${classNameLower}Service.findByColumn(StrUtil.toUnderlineCase(column),value));
    }

    @PostMapping
    @ApiOperation(value="新增${tableComment}")
    public ResultData add (${className} ${classNameLower}){
        return Optional.of(${classNameLower}Service.insert(${classNameLower}))
                .filter(count -> count > 0)
                .map(count -> ResultData.success(${classNameLower}))
                .orElseThrow(() -> new BasicException(SystemTip.INSERT_FAIL));
    }

    @PatchMapping
    @ApiOperation(value="更新${tableComment}",notes = "局部更新,忽略空值")
    public ResultData updateIgnoreNull (${className} ${classNameLower}){
        return Optional.of(${classNameLower}Service.updateIgnoreNull(${classNameLower}))
                .filter(count -> count > 0)
                .map(count -> ResultData.success(${classNameLower}))
                .orElseThrow(() -> new BasicException(SystemTip.UPDATE_FAIL));
    }

    @PutMapping
    @ApiOperation(value="更新${tableComment}",notes = "整体更新")
    public ResultData update (${className} ${classNameLower}){
        return Optional.of(${classNameLower}Service.update(${classNameLower}))
                .filter(count -> count > 0)
                .map(count -> ResultData.success(${classNameLower}))
                .orElseThrow(() -> new BasicException(SystemTip.UPDATE_FAIL));
    }

    @DeleteMapping("/{column}/{value}")
    @ApiOperation(value="通过字段名删除${tableComment}")
    public ResultData deleteByColumn (@PathVariable("column")String column,@PathVariable("value")Object value){
        return Optional.of(${classNameLower}Service.deleteByColumn(StrUtil.toUnderlineCase(column),value))
                .filter(count -> count > 0)
                .map(count -> ResultData.success())
                .orElseThrow(() -> new BasicException(SystemTip.DELETE_FAIL));
    }

    @DeleteMapping("/batch")
    @ApiOperation(value="批量删除${tableComment}")
    public ResultData batchDelete (${table.pkColumn.javaType}... ${table.pkColumn.columnNameLower}s){
        return Optional.of(${classNameLower}Service.batchDelete(${table.pkColumn.columnNameLower}s))
                .filter(count -> count > 0)
                .map(count -> ResultData.success())
                .orElseThrow(() -> new BasicException(SystemTip.DELETE_FAIL));
    }
}