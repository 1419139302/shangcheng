package com.zzy.shopp.app.biz.controller;

import com.zzy.shopp.app.biz.model.Product;
import com.zzy.shopp.app.biz.service.ProductService;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;
import com.zzy.shopp.app.biz.vo.ProductVo;
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
@RequestMapping("/product")
@Api(value = "API - ProductController", description = "产品表接口")
public class ProductController {
    @Autowired
    private ProductService productService;


    @ApiOperation(value = "添加")
    @ApiImplicitParam(name = "productVos", value = "产品表对象集合", dataType = "ProductVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PostMapping("/addList")
    public JsonData addList(@RequestBody List<ProductVo> productVos) {
        FluentValidatorUtil.resultAdd(productVos);
        List<Product> products = BeanUtil.copyList(productVos, Product.class);
        productService.addList(products);
        return JsonData.ok();
    }

    @ApiOperation(value = "删除", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "ids", value = "产品表对象ID数组", dataType = "Long", required = true, paramType = "path")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @DeleteMapping("/delete/{ids}")
    public JsonData delete(@PathVariable("ids") Long[] ids) {
        productService.deleteList(ids);
        return JsonData.ok();
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParam(name = "productVo", value = "产品对象集合", dataType = "ProductVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PutMapping("/update")
    public JsonData update(@RequestBody @Validated(UpdateProject.class) ProductVo productVo) {
        Product product = Product.builder().build();
        BeanUtil.copyProperties(productVo, product);
        productService.update(product);
        return JsonData.ok();
    }

    @ApiOperation(value = "模糊查询产品表")
    @ApiImplicitParam(name = "commonSelectVo", value = "公用对象", dataType = "CommonSelectVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PostMapping("/select")
    public JsonData<List<Product>> select(@RequestBody CommonSelectVo commonSelectVo) {
        List<Product> products = productService.likeGetList(commonSelectVo);
        return JsonData.ok(products);
    }


    @ApiOperation(value = "根据id查询商品")
    @ApiImplicitParam(name = "id", value = "id", dataType = "Long", required = true, paramType = "path")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @GetMapping("/get/{id}")
    public JsonData<Product> get(@PathVariable("id") Long id) {
        Product p = productService.getById(id);
        return JsonData.ok(p);
    }
}
