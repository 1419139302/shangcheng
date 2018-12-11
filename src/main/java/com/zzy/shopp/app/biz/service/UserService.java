package com.zzy.shopp.app.biz.service;

import com.zzy.shopp.app.biz.model.User;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;

import java.util.List;

/**
 * 类说明:
 * @author Heaton
 * Created by noname on 2018-11-29 17:28:57
 */
public interface UserService {


	public User getById(Long id);


	public List<User> getList(User entity);


	public List<User> likeGetList(CommonSelectVo commonSelectVo);


	public int update(User entity);


	public int deleteById(Long id);


 	public int deleteList(Long[] ids);


	public int add(User entity);


	public int addList(List<User> entityList);

}