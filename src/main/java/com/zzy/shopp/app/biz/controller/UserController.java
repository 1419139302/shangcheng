package com.zzy.shopp.app.biz.controller;

import com.zzy.shopp.app.biz.model.User;
import com.zzy.shopp.app.biz.service.UserService;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;
import com.zzy.shopp.app.biz.vo.UserVo;
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
 * Created by noname on 2018-11-29 17:28:57
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/user")
@Api(value = "API - UserController", description = "用户表接口")
public class UserController {
    @Autowired
    private UserService userService;


    @ApiOperation(value = "添加")
    @ApiImplicitParam(name = "userVos", value = "用户表对象集合", dataType = "UserVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PostMapping("/addList")
    public JsonData addList(@RequestBody List<UserVo> userVos) {
        FluentValidatorUtil.resultAdd(userVos);
        List<User> users = BeanUtil.copyList(userVos, User.class);
        userService.addList(users);
        return JsonData.ok();
    }

    @ApiOperation(value = "删除", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "ids", value = "用户表对象ID数组", dataType = "Long", required = true, paramType = "path")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @DeleteMapping("/delete/{ids}")
    public JsonData delete(@PathVariable("ids") Long[] ids) {
        userService.deleteList(ids);
        return JsonData.ok();
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParam(name = "userVo", value = "学生对象集合", dataType = "UserVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PutMapping("/update")
    public JsonData update(@RequestBody @Validated(UpdateProject.class) UserVo userVo) {
        User user = User.builder().build();
        BeanUtil.copyProperties(userVo, user);
        userService.update(user);
        return JsonData.ok();
    }

    @ApiOperation(value = "模糊查询用户表")
    @ApiImplicitParam(name = "commonSelectVo", value = "公用对象", dataType = "CommonSelectVo", required = true, paramType = "body")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 999, message = "权限不足")})
    @PostMapping("/select")
    public JsonData<List<User>> select(@RequestBody CommonSelectVo commonSelectVo) {
        List<User> users = userService.likeGetList(commonSelectVo);
        return JsonData.ok(users);
    }
}
