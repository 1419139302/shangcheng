package com.zzy.shopp.app.biz.mapper;

import com.zzy.shopp.app.biz.model.User;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;
import com.zzy.shopp.app.lib.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 类说明:
 *
 * @author Heaton
 * Created by noname on 2018-11-29 17:28:57
 */
@Mapper
public interface UserMapper extends MyBaseMapper<User> {
    public User getById(Long id);


    public List<User> getList(User entity);


    public List<User> likeGetList(CommonSelectVo commonSelectVo);


    public int update(User entity);


    public int deleteById(Long id);


    public int add(User entity);


    public int addList(List<User> entityList);
}