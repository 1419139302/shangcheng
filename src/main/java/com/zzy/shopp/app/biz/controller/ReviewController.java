package com.zzy.shopp.app.biz.controller;

import com.zzy.shopp.app.biz.model.Review;
import com.zzy.shopp.app.biz.service.ReviewService;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;
import com.zzy.shopp.app.biz.vo.ReviewVo;
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
@RequestMapping("/review")
@Api(value = "API - ReviewController", description = "评论表接口")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;


    @ApiOperation(value = "添加")
    @ApiImplicitParam(name = "reviewVos", value = "评论表对象集合", dataType = "ReviewVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PostMapping("/addList")
    public JsonData addList(@RequestBody List<ReviewVo> reviewVos) {
        FluentValidatorUtil.resultAdd(reviewVos);
        List<Review> reviews = BeanUtil.copyList(reviewVos, Review.class);
        reviewService.addList(reviews);
        return JsonData.ok();
    }

    @ApiOperation(value = "删除", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "ids", value = "评论表对象ID数组", dataType = "Long", required = true, paramType = "path")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @DeleteMapping("/delete/{ids}")
    public JsonData delete(@PathVariable("ids") Long[] ids) {
        reviewService.deleteList(ids);
        return JsonData.ok();
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParam(name = "reviewVo", value = "评论对象集合", dataType = "ReviewVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PutMapping("/update")
    public JsonData update(@RequestBody @Validated(UpdateProject.class) ReviewVo reviewVo) {
        Review review = Review.builder().build();
        BeanUtil.copyProperties(reviewVo, review);
        reviewService.update(review);
        return JsonData.ok();
    }

    @ApiOperation(value = "模糊查询评论表")
    @ApiImplicitParam(name = "commonSelectVo", value = "公用对象", dataType = "CommonSelectVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PostMapping("/select")
    public JsonData<List<Review>> select(@RequestBody CommonSelectVo commonSelectVo) {
        List<Review> reviews = reviewService.likeGetList(commonSelectVo);
        return JsonData.ok(reviews);
    }
}
