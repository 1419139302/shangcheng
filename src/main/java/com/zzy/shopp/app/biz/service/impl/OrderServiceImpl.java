package com.zzy.shopp.app.biz.service.impl;

import com.zzy.shopp.app.biz.mapper.OrderMapper;
import com.zzy.shopp.app.biz.model.Order;
import com.zzy.shopp.app.biz.model.Orderitem;
import com.zzy.shopp.app.biz.service.OrderService;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;
import com.zzy.shopp.app.exception.BizErrorCode;
import com.zzy.shopp.app.exception.BusinessException;
import com.zzy.shopp.app.util.MathUtil;
import com.zzy.shopp.app.util.PojoPageHandler;
import com.zzy.shopp.app.util.Validator;
import com.zzy.shopp.app.util.id.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 类说明:
 *
 * @author Heaton
 * Created by noname on 2018-11-30 9:34:06
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order getById(Long id) {
        return orderMapper.getById(id);
    }

    @Override
    public List<Order> getList(Order entity) {
        List<Order> resut = null;
        resut = orderMapper.getList(entity);
        return resut;
    }

    @Override
    public List<Order> likeGetList(CommonSelectVo commonSelectVo) {
        PojoPageHandler.doPagingAndSorting(commonSelectVo);
        List<Order> resut = null;
        resut = orderMapper.likeGetList(commonSelectVo);
        for (Order order : resut) {
            int number = 0;
            Double sum = 0.0d;
            for (Orderitem orderitem : order.getOrderItems()) {
                number += orderitem.getNumber();
                Double promotePice = orderitem.getProduct().getPromotePice();
                sum = MathUtil.add(sum, MathUtil.mul(number, promotePice));
            }
            order.setTotalNumber(number);
            order.setTotal(sum);
        }
        return resut;
    }

    @Override
    public int update(Order entity) {
        return orderMapper.update(entity);
    }

    @Override
    public int deleteById(Long id) {
        return orderMapper.deleteById(id);
    }

    @Override
    public int deleteList(Long[] ids) {
        Example example = getExample(ids);
        Order order = Order.builder().isDel(true).build();
        return orderMapper.updateByExampleSelective(order, example);
    }

    @Override
    public int add(Order entity) {
        long ll = IdUtil.generateId();
        entity.setId(ll);
        entity.setOrderCode(ll + "");
        entity.setCreateTime(new Date());
        entity.setIsDel(false);
        return orderMapper.add(entity);
    }

    @Override
    public int addList(List<Order> entityList) {
        for (Order entity : entityList) {
            long ll = IdUtil.generateId();
            entity.setId(ll);
            entity.setOrderCode(ll + "");
            entity.setCreateTime(new Date());
            entity.setIsDel(false);
        }
        return orderMapper.addList(entityList);
    }

    //查询符合id集合条件的
    private Example getExample(Long[] idss) {
        if (!Validator.valid(idss)) {
            throw new BusinessException(BizErrorCode.ID_NULL);
        }
        List<Long> ids = Arrays.asList(idss);
        Example example = new Example(Order.class);
        example.createCriteria().andIn("id", ids);
        return example;
    }

}