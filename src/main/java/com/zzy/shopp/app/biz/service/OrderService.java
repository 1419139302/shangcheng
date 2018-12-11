package com.zzy.shopp.app.biz.service;

import com.zzy.shopp.app.biz.model.Order;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;

import java.util.List;

/**
 * 类说明:
 *
 * @author Heaton
 * Created by noname on 2018-11-30 9:34:06
 */
public interface OrderService {


    public Order getById(Long id);


    public List<Order> getList(Order entity);


    public List<Order> likeGetList(CommonSelectVo commonSelectVo);


    public int update(Order entity);


    public int deleteById(Long id);


    public int deleteList(Long[] ids);


    public int add(Order entity);


    public int addList(List<Order> entityList);

}