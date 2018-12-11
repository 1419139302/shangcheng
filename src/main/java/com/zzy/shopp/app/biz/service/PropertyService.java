package com.zzy.shopp.app.biz.service;

import com.zzy.shopp.app.biz.model.Property;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;

import java.util.List;

/**
 * 类说明:
 *
 * @author Heaton
 * Created by noname on 2018-11-30 9:34:06
 */
public interface PropertyService {


    public Property getById(Long id);


    public List<Property> getList(Property entity);


    public List<Property> likeGetList(CommonSelectVo commonSelectVo);


    public int update(Property entity);


    public int deleteById(Long id);


    public int deleteList(Long[] ids);


    public int add(Property entity);


    public int addList(List<Property> entityList);

}