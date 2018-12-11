package com.zzy.shopp.app.biz.controller;

import com.zzy.shopp.app.biz.model.Category;
import com.zzy.shopp.app.biz.service.CategoryService;
import com.zzy.shopp.app.biz.vo.CategoryVo;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;
import com.zzy.shopp.app.config.JsonData;
import com.zzy.shopp.app.util.BeanUtil;
import com.zzy.shopp.app.util.ImageUtil;
import com.zzy.shopp.app.validGroup.AddProject;
import com.zzy.shopp.app.validGroup.UpdateProject;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 接口层
 *
 * @author Heaton
 * Created by noname on 2018-11-30 9:34:06
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/category")
@Api(value = "API - CategoryController", description = "分类表接口")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @ApiOperation(value = "添加")
    @ApiImplicitParam(name = "categoryVos", value = "分类表对象", dataType = "CategoryVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PostMapping("/addList")
    public JsonData addList(@Validated(AddProject.class) CategoryVo categoryVo, MultipartFile image, HttpServletRequest request) {
        Category c = Category.builder().build();
        BeanUtil.copyProperties(categoryVo, c);
        categoryService.add(c);
        ImageUtil.saveOrUpdateImageFile(c, image, request, null);
        return JsonData.ok();
    }


    @ApiOperation(value = "删除", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "ids", value = "分类表对象ID数组", dataType = "Long", required = true, paramType = "path")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @DeleteMapping("/delete/{ids}")
    public JsonData delete(@PathVariable("ids") Long[] ids) {
        categoryService.deleteList(ids);
        return JsonData.ok();
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParam(name = "id", value = "分类对象id", dataType = "Long", required = true, paramType = "path")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PutMapping("/update/{id}/{name}")
    public JsonData update(@Validated(UpdateProject.class) CategoryVo categoryVo, MultipartFile image, HttpServletRequest request) {
        Category category = Category.builder().build();
        BeanUtil.copyProperties(categoryVo, category);
        categoryService.update(category);
        if (image != null) {
            ImageUtil.saveOrUpdateImageFile(category, image, request, null);
        }
        return JsonData.ok();
    }

    @ApiOperation(value = "模糊查询分类表")
    @ApiImplicitParam(name = "commonSelectVo", value = "公用对象", dataType = "CommonSelectVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PostMapping("/select")
    public JsonData<List<Category>> select(@RequestBody CommonSelectVo commonSelectVo) {
        List<Category> categorys = categoryService.likeGetList(commonSelectVo);
        return JsonData.ok(categorys);
    }

    @ApiOperation(value = "根据id查询分类")
    @ApiImplicitParam(name = "id", value = "id", dataType = "Long", required = true, paramType = "path")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @GetMapping("/get/{id}")
    public JsonData<Category> select(@PathVariable("id") Long id) {
        Category c = categoryService.getById(id);
        return JsonData.ok(c);
    }
}
