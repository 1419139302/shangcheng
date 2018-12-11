package com.zzy.shopp.app.biz.controller;

import com.zzy.shopp.app.biz.model.Product;
import com.zzy.shopp.app.biz.model.Propertyvalue;
import com.zzy.shopp.app.biz.service.ProductService;
import com.zzy.shopp.app.biz.service.PropertyvalueService;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;
import com.zzy.shopp.app.biz.vo.PropertyvalueVo;
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
@RequestMapping("/propertyvalue")
@Api(value = "API - PropertyvalueController", description = "属性值表接口")
public class PropertyvalueController {
    @Autowired
    private PropertyvalueService propertyvalueService;

    @Autowired
    private ProductService productService;


    @ApiOperation(value = "添加")
    @ApiImplicitParam(name = "propertyvalueVos", value = "属性值表对象集合", dataType = "PropertyvalueVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PostMapping("/addList")
    public JsonData addList(@RequestBody List<PropertyvalueVo> propertyvalueVos) {
        FluentValidatorUtil.resultAdd(propertyvalueVos);
        List<Propertyvalue> propertyvalues = BeanUtil.copyList(propertyvalueVos, Propertyvalue.class);
        propertyvalueService.addList(propertyvalues);
        return JsonData.ok();
    }

    @ApiOperation(value = "删除", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "ids", value = "属性值表对象ID数组", dataType = "Long", required = true, paramType = "path")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @DeleteMapping("/delete/{ids}")
    public JsonData delete(@PathVariable("ids") Long[] ids) {
        propertyvalueService.deleteList(ids);
        return JsonData.ok();
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParam(name = "propertyvalueVo", value = "属性值对象集合", dataType = "PropertyvalueVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PutMapping("/update")
    public JsonData update(@RequestBody @Validated(UpdateProject.class) PropertyvalueVo propertyvalueVo) {
        Propertyvalue propertyvalue = Propertyvalue.builder().build();
        BeanUtil.copyProperties(propertyvalueVo, propertyvalue);
        propertyvalueService.update(propertyvalue);
        return JsonData.ok();
    }

/*    @ApiOperation(value = "模糊查询属性值表")
    @ApiImplicitParam(name = "commonSelectVo", value = "公用对象", dataType = "CommonSelectVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PostMapping("/select")
    public JsonData<List<Propertyvalue>> select(@RequestBody CommonSelectVo commonSelectVo) {
        List<Propertyvalue> propertyvalues = propertyvalueService.likeGetList(commonSelectVo);
        return JsonData.ok(propertyvalues);
    }*/


    @ApiOperation(value = "根据商品id查询属性值")
    @ApiImplicitParam(name = "id", value = "id", dataType = "Long", required = true, paramType = "path")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @GetMapping("/get/{id}")
    public JsonData<Propertyvalue> get(@PathVariable("id") Long id) {
        Product p = productService.getById(id);
        propertyvalueService.init(p);
        CommonSelectVo commonSelectVo = CommonSelectVo.builder().id(p.getId()).build();
        List<Propertyvalue> propertyValues = propertyvalueService.likeGetList(commonSelectVo);
        return JsonData.ok(propertyValues);
    }
}
