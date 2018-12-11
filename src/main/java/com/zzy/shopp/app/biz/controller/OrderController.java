package com.zzy.shopp.app.biz.controller;

import com.zzy.shopp.app.biz.model.Order;
import com.zzy.shopp.app.biz.service.OrderService;
import com.zzy.shopp.app.biz.shoppenum.PayEnum;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;
import com.zzy.shopp.app.biz.vo.OrderVo;
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
@RequestMapping("/order")
@Api(value = "API - OrderController", description = "订单表接口")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @ApiOperation(value = "添加")
    @ApiImplicitParam(name = "orderVos", value = "订单表对象集合", dataType = "OrderVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PostMapping("/addList")
    public JsonData addList(@RequestBody List<OrderVo> orderVos) {
        FluentValidatorUtil.resultAdd(orderVos);
        List<Order> orders = BeanUtil.copyList(orderVos, Order.class);
        orderService.addList(orders);
        return JsonData.ok();
    }

    @ApiOperation(value = "删除", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "ids", value = "订单表对象ID数组", dataType = "Long", required = true, paramType = "path")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @DeleteMapping("/delete/{ids}")
    public JsonData delete(@PathVariable("ids") Long[] ids) {
        orderService.deleteList(ids);
        return JsonData.ok();
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParam(name = "orderVo", value = "订单对象集合", dataType = "OrderVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PutMapping("/update")
    public JsonData update(@RequestBody @Validated(UpdateProject.class) OrderVo orderVo) {
        Order order = Order.builder().build();
        BeanUtil.copyProperties(orderVo, order);
        orderService.update(order);
        return JsonData.ok();
    }

    @ApiOperation(value = "等待发货--》发货--》等待确认")
    @ApiImplicitParam(name = "id", value = "订单对象id", dataType = "OrderVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PutMapping("/waitconfirm/{id}")
    public JsonData update(@PathVariable("id") Long id) {
        Order order = Order.builder().id(id).status(PayEnum.WAITCONFIRM.getEnglishName()).build();
        orderService.update(order);
        return JsonData.ok();
    }

    @ApiOperation(value = "模糊查询订单表")
    @ApiImplicitParam(name = "commonSelectVo", value = "公用对象", dataType = "CommonSelectVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PostMapping("/select")
    public JsonData<List<Order>> select(@RequestBody CommonSelectVo commonSelectVo) {
        List<Order> orders = orderService.likeGetList(commonSelectVo);
        return JsonData.ok(orders);
    }
}
