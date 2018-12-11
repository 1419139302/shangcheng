package com.zzy.shopp.app.biz.controller;

import com.zzy.shopp.app.biz.model.Orderitem;
import com.zzy.shopp.app.biz.service.OrderitemService;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;
import com.zzy.shopp.app.biz.vo.OrderitemVo;
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
@RequestMapping("/orderitem")
@Api(value = "API - OrderitemController", description = "订单项表接口")
public class OrderitemController {
    @Autowired
    private OrderitemService orderitemService;


    @ApiOperation(value = "添加")
    @ApiImplicitParam(name = "orderitemVos", value = "订单项表对象集合", dataType = "OrderitemVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PostMapping("/addList")
    public JsonData addList(@RequestBody List<OrderitemVo> orderitemVos) {
        FluentValidatorUtil.resultAdd(orderitemVos);
        List<Orderitem> orderitems = BeanUtil.copyList(orderitemVos, Orderitem.class);
        orderitemService.addList(orderitems);
        return JsonData.ok();
    }

    @ApiOperation(value = "删除", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "ids", value = "订单项表对象ID数组", dataType = "Long", required = true, paramType = "path")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @DeleteMapping("/delete/{ids}")
    public JsonData delete(@PathVariable("ids") Long[] ids) {
        orderitemService.deleteList(ids);
        return JsonData.ok();
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParam(name = "orderitemVo", value = "订单项对象集合", dataType = "OrderitemVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PutMapping("/update")
    public JsonData update(@RequestBody @Validated(UpdateProject.class) OrderitemVo orderitemVo) {
        Orderitem orderitem = Orderitem.builder().build();
        BeanUtil.copyProperties(orderitemVo, orderitem);
        orderitemService.update(orderitem);
        return JsonData.ok();
    }

    @ApiOperation(value = "模糊查询订单项表")
    @ApiImplicitParam(name = "commonSelectVo", value = "公用对象", dataType = "CommonSelectVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PostMapping("/select")
    public JsonData<List<Orderitem>> select(@RequestBody CommonSelectVo commonSelectVo) {
        List<Orderitem> orderitems = orderitemService.likeGetList(commonSelectVo);
        return JsonData.ok(orderitems);
    }
}
