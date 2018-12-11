package com.zzy.shopp.app.biz.service;

import com.zzy.shopp.app.biz.model.Category;
import com.zzy.shopp.app.biz.model.Product;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;

import java.util.List;

/**
 * 类说明:
 *
 * @author Heaton
 * Created by noname on 2018-11-30 9:34:06
 */
public interface ProductService {


    public Product getById(Long id);


    public List<Product> getList(Product entity);


    public List<Product> likeGetList(CommonSelectVo commonSelectVo);


    public int update(Product entity);


    public int deleteById(Long id);


    public int deleteList(Long[] ids);


    public int add(Product entity);


    public int addList(List<Product> entityList);

    public void fillByRow(List<Category> categorys);
}