package com.zzy.shopp.app.biz.service;

import com.zzy.shopp.app.biz.model.Productimage;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;

import java.util.List;

/**
 * 类说明:
 *
 * @author Heaton
 * Created by noname on 2018-11-30 9:34:06
 */
public interface ProductimageService {


    public Productimage getById(Long id);


    public List<Productimage> getList(Productimage entity);


    public List<Productimage> likeGetList(CommonSelectVo commonSelectVo);


    public int update(Productimage entity);


    public int deleteById(Long id);


    public int deleteList(Long[] ids);


    public int add(Productimage entity);


    public int addList(List<Productimage> entityList);

}