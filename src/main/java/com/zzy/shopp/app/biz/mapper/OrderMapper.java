package com.zzy.shopp.app.biz.mapper;

import com.zzy.shopp.app.biz.model.Order;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;
import com.zzy.shopp.app.lib.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 类说明:
 *
 * @author Heaton
 * Created by noname on 2018-11-30 9:34:06
 */
@Mapper
public interface OrderMapper extends MyBaseMapper<Order> {

    Order getById(Long id);

    List<Order> getList(Order entity);

    List<Order> likeGetList(CommonSelectVo commonSelectVo);

    int update(Order entity);

    int deleteById(Long id);

    int add(Order entity);

    int addList(List<Order> entityList);

}