package com.zzy.shopp.app.biz.controller;

import com.zzy.shopp.app.biz.model.Category;
import com.zzy.shopp.app.biz.service.CategoryService;
import com.zzy.shopp.app.biz.service.ProductService;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;
import com.zzy.shopp.app.config.JsonData;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SuppressWarnings("all")
@RestController
@RequestMapping("/fore")
@Api(value = "API - ForeController", description = "前台接口")
public class ForeController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "模糊查询分类表")
    @ApiImplicitParam(name = "commonSelectVo", value = "公用对象", dataType = "CommonSelectVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PostMapping("/select")
    public JsonData<List<Category>> select(@RequestBody CommonSelectVo commonSelectVo) {
        List<Category> categorys = categoryService.likeGetListAndChilds(commonSelectVo);
        productService.fillByRow(categorys);
        return JsonData.ok(categorys);
    }
}
