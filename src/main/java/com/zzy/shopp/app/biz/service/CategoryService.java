package com.zzy.shopp.app.biz.service;

import com.zzy.shopp.app.biz.model.Category;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;

import java.util.List;

/**
 * 类说明:
 *
 * @author Heaton
 * Created by noname on 2018-11-30 9:34:06
 */
public interface CategoryService {


    public Category getById(Long id);


    public List<Category> getList(Category entity);


    public List<Category> likeGetList(CommonSelectVo commonSelectVo);

    public List<Category> likeGetListAndChilds(CommonSelectVo commonSelectVo);

    public int update(Category entity);


    public int deleteById(Long id);


    public int deleteList(Long[] ids);


    public int add(Category entity);


    public int addList(List<Category> entityList);

}