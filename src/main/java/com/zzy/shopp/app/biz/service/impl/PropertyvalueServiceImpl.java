package com.zzy.shopp.app.biz.service.impl;

import com.zzy.shopp.app.biz.mapper.PropertyvalueMapper;
import com.zzy.shopp.app.biz.model.Category;
import com.zzy.shopp.app.biz.model.Product;
import com.zzy.shopp.app.biz.model.Property;
import com.zzy.shopp.app.biz.model.Propertyvalue;
import com.zzy.shopp.app.biz.service.PropertyvalueService;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;
import com.zzy.shopp.app.exception.BizErrorCode;
import com.zzy.shopp.app.exception.BusinessException;
import com.zzy.shopp.app.util.PojoPageHandler;
import com.zzy.shopp.app.util.Validator;
import com.zzy.shopp.app.util.id.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * 类说明:
 *
 * @author Heaton
 * Created by noname on 2018-11-30 9:34:06
 */
@Service("propertyvalueService")
public class PropertyvalueServiceImpl implements PropertyvalueService {

    @Autowired
    private PropertyvalueMapper propertyvalueMapper;

    @Override
    public Propertyvalue getById(Long id) {
        return propertyvalueMapper.getById(id);
    }

    @Override
    public List<Propertyvalue> getList(Propertyvalue entity) {
        List<Propertyvalue> resut = null;
        resut = propertyvalueMapper.getList(entity);
        return resut;
    }

    @Override
    public List<Propertyvalue> likeGetList(CommonSelectVo commonSelectVo) {
        PojoPageHandler.doPagingAndSorting(commonSelectVo);
        List<Propertyvalue> resut = null;
        resut = propertyvalueMapper.likeGetList(commonSelectVo);
        return resut;
    }

    @Override
    public int update(Propertyvalue entity) {
        return propertyvalueMapper.update(entity);
    }

    @Override
    public int deleteById(Long id) {
        return propertyvalueMapper.deleteById(id);
    }

    @Override
    public int deleteList(Long[] ids) {
        Example example = getExample(ids);
        Propertyvalue propertyvalue = Propertyvalue.builder().isDel(true).build();
        return propertyvalueMapper.updateByExampleSelective(propertyvalue, example);
    }

    @Override
    public int add(Propertyvalue entity) {
        return propertyvalueMapper.add(entity);
    }

    @Override
    public int addList(List<Propertyvalue> entityList) {
        for (Propertyvalue entity : entityList) {
            entity.setId(IdUtil.generateId());
            entity.setIsDel(false);
        }
        return propertyvalueMapper.addList(entityList);
    }

    //查询符合id集合条件的
    private Example getExample(Long[] idss) {
        if (!Validator.valid(idss)) {
            throw new BusinessException(BizErrorCode.ID_NULL);
        }
        List<Long> ids = Arrays.asList(idss);
        Example example = new Example(Propertyvalue.class);
        example.createCriteria().andIn("id", ids);
        return example;
    }

    @Override
    public List<Property> listByCategory(Category c) {

        return propertyvalueMapper.listByCategory(c.getId());
    }

    public void init(Product product) {
        List<Property> propertys = listByCategory(product.getCategory());
        for (Property property : propertys) {
            Propertyvalue propertyValue = getByPropertyAndProduct(product.getId(), property.getId());
            if (null == propertyValue) {
                propertyValue = Propertyvalue.builder().id(IdUtil.generateId()).pid(product.getId()).ptid(property.getId()).createTime(new Date()).isDel(false).build();
                propertyValue.setProduct(product);
                propertyValue.setProperty(property);
                propertyvalueMapper.add(propertyValue);
            }
        }
    }

    public Propertyvalue getByPropertyAndProduct(Long pid, Long ptid) {
        Map<String, Long> map = new HashMap<>();
        map.put("pid", pid);
        map.put("ptid", ptid);
        return propertyvalueMapper.getByPropertyAndProduct(map);
    }

}