package com.zzy.shopp.app.biz.controller;

import com.zzy.shopp.app.biz.model.Productimage;
import com.zzy.shopp.app.biz.service.ProductimageService;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;
import com.zzy.shopp.app.biz.vo.ProductimageVo;
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
@RequestMapping("/productimage")
@Api(value = "API - ProductimageController", description = "产品图片表接口")
public class ProductimageController {
    @Autowired
    private ProductimageService productimageService;


    @ApiOperation(value = "添加")
    @ApiImplicitParam(name = "productimageVos", value = "产品图片表对象集合", dataType = "ProductimageVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PostMapping("/addList")
    public JsonData addList(@Validated(AddProject.class) ProductimageVo productimageVo, MultipartFile image, HttpServletRequest request) {
        Productimage pi = Productimage.builder().build();
        BeanUtil.copyProperties(productimageVo, pi);
        productimageService.add(pi);
        if ("single".equals(productimageVo.getType().toLowerCase())) {
            ImageUtil.saveOrUpdateImageFile(pi, image, request, "productSingle");
        } else {
            ImageUtil.saveOrUpdateImageFile(pi, image, request, "productDetail");
        }
        return JsonData.ok();
    }

    @ApiOperation(value = "删除", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "ids", value = "产品图片表对象ID数组", dataType = "Long", required = true, paramType = "path")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @DeleteMapping("/delete/{ids}")
    public JsonData delete(@PathVariable("ids") Long[] ids) {
        productimageService.deleteList(ids);
        return JsonData.ok();
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParam(name = "productimageVo", value = "产品图片对象集合", dataType = "ProductimageVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PutMapping("/update")
    public JsonData update(@RequestBody @Validated(UpdateProject.class) ProductimageVo productimageVo) {
        Productimage productimage = Productimage.builder().build();
        BeanUtil.copyProperties(productimageVo, productimage);
        productimageService.update(productimage);
        return JsonData.ok();
    }

    @ApiOperation(value = "模糊查询产品图片表")
    @ApiImplicitParam(name = "commonSelectVo", value = "公用对象", dataType = "CommonSelectVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PostMapping("/select")
    public JsonData<List<Productimage>> select(@RequestBody CommonSelectVo commonSelectVo) {
        List<Productimage> productimages = productimageService.likeGetList(commonSelectVo);
        return JsonData.ok(productimages);
    }

    @ApiOperation(value = "根据id查询商品图片")
    @ApiImplicitParam(name = "id", value = "id", dataType = "Long", required = true, paramType = "path")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @GetMapping("/get/{id}")
    public JsonData<Productimage> get(@PathVariable("id") Long id) {
        Productimage pi = productimageService.getById(id);
        return JsonData.ok(pi);
    }


}
