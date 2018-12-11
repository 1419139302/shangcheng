package com.zzy.shopp.app.biz.service.impl;

import com.zzy.shopp.app.biz.mapper.UserMapper;
import com.zzy.shopp.app.biz.model.User;
import com.zzy.shopp.app.biz.service.UserService;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;
import com.zzy.shopp.app.exception.BizErrorCode;
import com.zzy.shopp.app.exception.BusinessException;
import com.zzy.shopp.app.util.PojoPageHandler;
import com.zzy.shopp.app.util.Validator;
import com.zzy.shopp.app.util.id.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 类说明:
 *
 * @author Heaton
 * Created by noname on 2018-11-29 17:28:57
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getById(Long id) {
        return userMapper.getById(id);
    }

    @Override
    public List<User> getList(User entity) {
        List<User> resut = null;
        resut = userMapper.getList(entity);
        return resut;
    }

    @Override
    public List<User> likeGetList(CommonSelectVo commonSelectVo) {
        PojoPageHandler.doPagingAndSorting(commonSelectVo);
        List<User> resut = null;
        resut = userMapper.likeGetList(commonSelectVo);
        return resut;
    }

    @Override
    public int update(User entity) {
        entity.setUpdateTime(new Date());
        return userMapper.update(entity);
    }

    @Override
    public int deleteById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int deleteList(Long[] ids) {
        Example example = getExample(ids);
        User user = User.builder().isDel(true).deleteTime(new Date()).build();
        return userMapper.updateByExampleSelective(user, example);
    }

    @Override
    public int add(User entity) {
        entity.setCreateTime(new Date());
        return userMapper.add(entity);
    }

    @Override
    public int addList(List<User> entityList) {
        for (User entity : entityList) {
            entity.setId(IdUtil.generateId());
            entity.setCreateTime(new Date());
            entity.setIsDel(false);
        }
        return userMapper.addList(entityList);
    }

    //查询符合id集合条件的
    private Example getExample(Long[] idss) {
        if (!Validator.valid(idss)) {
            throw new BusinessException(BizErrorCode.ID_NULL);
        }
        List<Long> ids = Arrays.asList(idss);
        Example example = new Example(User.class);
        example.createCriteria().andIn("id", ids);
        return example;
    }

}