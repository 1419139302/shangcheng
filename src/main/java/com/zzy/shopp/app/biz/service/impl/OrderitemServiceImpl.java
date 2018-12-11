package com.zzy.shopp.app.biz.service.impl;

import com.zzy.shopp.app.biz.mapper.OrderitemMapper;
import com.zzy.shopp.app.biz.model.Orderitem;
import com.zzy.shopp.app.biz.service.OrderitemService;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;
import com.zzy.shopp.app.exception.BizErrorCode;
import com.zzy.shopp.app.exception.BusinessException;
import com.zzy.shopp.app.util.PojoPageHandler;
import com.zzy.shopp.app.util.Validator;
import com.zzy.shopp.app.util.id.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

/**
 * 类说明:
 *
 * @author Heaton
 * Created by noname on 2018-11-30 9:34:06
 */
@Service("orderitemService")
public class OrderitemServiceImpl implements OrderitemService {

    @Autowired
    private OrderitemMapper orderitemMapper;

    @Override
    public Orderitem getById(Long id) {
        return orderitemMapper.getById(id);
    }

    @Override
    public List<Orderitem> getList(Orderitem entity) {
        List<Orderitem> resut = null;
        resut = orderitemMapper.getList(entity);
        return resut;
    }

    @Override
    public List<Orderitem> likeGetList(CommonSelectVo commonSelectVo) {
        PojoPageHandler.doPagingAndSorting(commonSelectVo);
        List<Orderitem> resut = null;
        resut = orderitemMapper.likeGetList(commonSelectVo);
        return resut;
    }

    @Override
    public int update(Orderitem entity) {
        return orderitemMapper.update(entity);
    }

    @Override
    public int deleteById(Long id) {
        return orderitemMapper.deleteById(id);
    }

    @Override
    public int deleteList(Long[] ids) {
        Example example = getExample(ids);
        Orderitem orderitem = Orderitem.builder().isDel(true).build();
        return orderitemMapper.updateByExampleSelective(orderitem, example);
    }

    @Override
    public int add(Orderitem entity) {
        return orderitemMapper.add(entity);
    }

    @Override
    public int addList(List<Orderitem> entityList) {
        for (Orderitem entity : entityList) {
            entity.setId(IdUtil.generateId());
            entity.setIsDel(false);
        }
        return orderitemMapper.addList(entityList);
    }

    //查询符合id集合条件的
    private Example getExample(Long[] idss) {
        if (!Validator.valid(idss)) {
            throw new BusinessException(BizErrorCode.ID_NULL);
        }
        List<Long> ids = Arrays.asList(idss);
        Example example = new Example(Orderitem.class);
        example.createCriteria().andIn("id", ids);
        return example;
    }

}