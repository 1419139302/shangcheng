package com.zzy.shopp.app.biz.service.impl;

import com.zzy.shopp.app.biz.mapper.PropertyMapper;
import com.zzy.shopp.app.biz.model.Property;
import com.zzy.shopp.app.biz.service.PropertyService;
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
import java.util.Date;
import java.util.List;

/**
 * 类说明:
 *
 * @author Heaton
 * Created by noname on 2018-11-30 9:34:06
 */
@Service("propertyService")
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyMapper propertyMapper;

    @Override
    public Property getById(Long id) {
        return propertyMapper.getById(id);
    }

    @Override
    public List<Property> getList(Property entity) {
        List<Property> resut = null;
        resut = propertyMapper.getList(entity);
        return resut;
    }

    @Override
    public List<Property> likeGetList(CommonSelectVo commonSelectVo) {
        PojoPageHandler.doPagingAndSorting(commonSelectVo);
        List<Property> resut = null;
        resut = propertyMapper.likeGetList(commonSelectVo);
        return resut;
    }

    @Override
    public int update(Property entity) {
        entity.setUpdateTime(new Date());
        return propertyMapper.update(entity);
    }

    @Override
    public int deleteById(Long id) {
        return propertyMapper.deleteById(id);
    }

    @Override
    public int deleteList(Long[] ids) {
        Example example = getExample(ids);
        Property property = Property.builder().isDel(true).build();
        return propertyMapper.updateByExampleSelective(property, example);
    }

    @Override
    public int add(Property entity) {
        return propertyMapper.add(entity);
    }

    @Override
    public int addList(List<Property> entityList) {
        for (Property entity : entityList) {
            entity.setId(IdUtil.generateId());
            entity.setIsDel(false);
            entity.setCreateTime(new Date());
        }
        return propertyMapper.addList(entityList);
    }

    //查询符合id集合条件的
    private Example getExample(Long[] idss) {
        if (!Validator.valid(idss)) {
            throw new BusinessException(BizErrorCode.ID_NULL);
        }
        List<Long> ids = Arrays.asList(idss);
        Example example = new Example(Property.class);
        example.createCriteria().andIn("id", ids);
        return example;
    }

}