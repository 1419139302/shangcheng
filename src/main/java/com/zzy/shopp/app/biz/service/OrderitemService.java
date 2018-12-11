package com.zzy.shopp.app.biz.service;

import com.zzy.shopp.app.biz.model.Orderitem;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;

import java.util.List;

/**
 * 类说明:
 *
 * @author Heaton
 * Created by noname on 2018-11-30 9:34:06
 */
public interface OrderitemService {


    public Orderitem getById(Long id);


    public List<Orderitem> getList(Orderitem entity);


    public List<Orderitem> likeGetList(CommonSelectVo commonSelectVo);


    public int update(Orderitem entity);


    public int deleteById(Long id);


    public int deleteList(Long[] ids);


    public int add(Orderitem entity);


    public int addList(List<Orderitem> entityList);

}