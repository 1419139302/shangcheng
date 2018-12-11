package com.zzy.shopp.app.biz.service;

import com.zzy.shopp.app.biz.model.Category;
import com.zzy.shopp.app.biz.model.Product;
import com.zzy.shopp.app.biz.model.Property;
import com.zzy.shopp.app.biz.model.Propertyvalue;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;

import java.util.List;

/**
 * 类说明:
 *
 * @author Heaton
 * Created by noname on 2018-11-30 9:34:06
 */
public interface PropertyvalueService {


    public Propertyvalue getById(Long id);


    public List<Propertyvalue> getList(Propertyvalue entity);


    public List<Propertyvalue> likeGetList(CommonSelectVo commonSelectVo);


    public int update(Propertyvalue entity);


    public int deleteById(Long id);


    public int deleteList(Long[] ids);


    public int add(Propertyvalue entity);


    public int addList(List<Propertyvalue> entityList);

    public List<Property> listByCategory(Category c);

    public void init(Product product);
}