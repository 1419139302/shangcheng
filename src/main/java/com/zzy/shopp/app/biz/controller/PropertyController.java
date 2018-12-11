package com.zzy.shopp.app.biz.controller;

import com.zzy.shopp.app.biz.model.Property;
import com.zzy.shopp.app.biz.service.PropertyService;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;
import com.zzy.shopp.app.biz.vo.PropertyVo;
import com.zzy.shopp.app.config.JsonData;
import com.zzy.shopp.app.util.BeanUtil;
import com.zzy.shopp.app.util.FluentValidatorUtil;
import com.zzy.shopp.app.validGroup.UpdateProject;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 接口层
 *
 * @author Heaton
 * Created by noname on 2018-11-30 9:34:06
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/property")
@Api(value = "API - PropertyController", description = "属性表接口")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;


    @ApiOperation(value = "添加")
    @ApiImplicitParam(name = "propertyVos", value = "属性表对象集合", dataType = "PropertyVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PostMapping("/addList")
    public JsonData addList(@RequestBody List<PropertyVo> propertyVos) {
        FluentValidatorUtil.resultAdd(propertyVos);
        List<Property> propertys = BeanUtil.copyList(propertyVos, Property.class);
        propertyService.addList(propertys);
        return JsonData.ok();
    }

    @ApiOperation(value = "删除", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "ids", value = "属性表对象ID数组", dataType = "Long", required = true, paramType = "path")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @DeleteMapping("/delete/{ids}")
    public JsonData delete(@PathVariable("ids") Long[] ids) {
        propertyService.deleteList(ids);
        return JsonData.ok();
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParam(name = "propertyVo", value = "属性对象集合", dataType = "PropertyVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PutMapping("/update")
    public JsonData update(@RequestBody @Validated(UpdateProject.class) PropertyVo propertyVo) {
        Property property = Property.builder().build();
        BeanUtil.copyProperties(propertyVo, property);
        propertyService.update(property);
        return JsonData.ok();
    }

    @ApiOperation(value = "模糊查询属性表")
    @ApiImplicitParam(name = "commonSelectVo", value = "公用对象", dataType = "CommonSelectVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PostMapping("/select")
    public JsonData<List<Property>> select(@RequestBody CommonSelectVo commonSelectVo) {
        List<Property> propertys = propertyService.likeGetList(commonSelectVo);
        return JsonData.ok(propertys);
    }

    @ApiOperation(value = "根据id查询属性")
    @ApiImplicitParam(name = "id", value = "id", dataType = "Long", required = true, paramType = "path")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @GetMapping("/get/{id}")
    public JsonData<Property> get(@PathVariable("id") Long id) {
        Property p = propertyService.getById(id);
        return JsonData.ok(p);
    }
}
