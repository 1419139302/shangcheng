package com.zzy.shopp.app.biz.service.impl;

import com.zzy.shopp.app.biz.mapper.ProductimageMapper;
import com.zzy.shopp.app.biz.model.Productimage;
import com.zzy.shopp.app.biz.service.ProductimageService;
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
@Service("productimageService")
public class ProductimageServiceImpl implements ProductimageService {

    @Autowired
    private ProductimageMapper productimageMapper;

    @Override
    public Productimage getById(Long id) {
        return productimageMapper.getById(id);
    }

    @Override
    public List<Productimage> getList(Productimage entity) {
        List<Productimage> resut = null;
        resut = productimageMapper.getList(entity);
        return resut;
    }

    @Override
    public List<Productimage> likeGetList(CommonSelectVo commonSelectVo) {
        PojoPageHandler.doPagingAndSorting(commonSelectVo);
        List<Productimage> resut = null;
        resut = productimageMapper.likeGetList(commonSelectVo);
        return resut;
    }

    @Override
    public int update(Productimage entity) {
        return productimageMapper.update(entity);
    }

    @Override
    public int deleteById(Long id) {
        return productimageMapper.deleteById(id);
    }

    @Override
    public int deleteList(Long[] ids) {
        Example example = getExample(ids);
        Productimage productimage = Productimage.builder().isDel(true).build();
        return productimageMapper.updateByExampleSelective(productimage, example);
    }

    @Override
    public int add(Productimage entity) {
        entity.setId(IdUtil.generateId());
        entity.setCreateTime(new Date());
        entity.setIsDel(false);
        return productimageMapper.add(entity);
    }

    @Override
    public int addList(List<Productimage> entityList) {
        for (Productimage entity : entityList) {
            entity.setId(IdUtil.generateId());
            entity.setIsDel(false);
        }
        return productimageMapper.addList(entityList);
    }

    //查询符合id集合条件的
    private Example getExample(Long[] idss) {
        if (!Validator.valid(idss)) {
            throw new BusinessException(BizErrorCode.ID_NULL);
        }
        List<Long> ids = Arrays.asList(idss);
        Example example = new Example(Productimage.class);
        example.createCriteria().andIn("id", ids);
        return example;
    }

}