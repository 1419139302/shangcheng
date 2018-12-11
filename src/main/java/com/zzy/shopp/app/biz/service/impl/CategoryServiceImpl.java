package com.zzy.shopp.app.biz.service.impl;

import com.zzy.shopp.app.biz.mapper.CategoryMapper;
import com.zzy.shopp.app.biz.model.Category;
import com.zzy.shopp.app.biz.service.CategoryService;
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
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category getById(Long id) {
        return categoryMapper.getById(id);
    }

    @Override
    public List<Category> getList(Category entity) {
        List<Category> resut = null;
        resut = categoryMapper.getList(entity);
        return resut;
    }

    @Override
    public List<Category> likeGetList(CommonSelectVo commonSelectVo) {
        PojoPageHandler.doPagingAndSorting(commonSelectVo);
        List<Category> resut = null;
        resut = categoryMapper.likeGetList(commonSelectVo);
        return resut;
    }

    @Override
    public List<Category> likeGetListAndChilds(CommonSelectVo commonSelectVo) {
        PojoPageHandler.doPagingAndSorting(commonSelectVo);
        List<Category> resut = null;
        resut = categoryMapper.likeGetListAndChilds(commonSelectVo);
        return resut;
    }

    @Override
    public int update(Category entity) {
        return categoryMapper.update(entity);
    }

    @Override
    public int deleteById(Long id) {
        return categoryMapper.deleteById(id);
    }

    @Override
    public int deleteList(Long[] ids) {
        Example example = getExample(ids);
        Category category = Category.builder().isDel(true).build();
        return categoryMapper.updateByExampleSelective(category, example);
    }

    @Override
    public int add(Category entity) {
        entity.setId(IdUtil.generateId());
        entity.setIsDel(false);
        return categoryMapper.add(entity);
    }

    @Override
    public int addList(List<Category> entityList) {
        for (Category entity : entityList) {
            entity.setId(IdUtil.generateId());
            entity.setIsDel(false);
        }
        return categoryMapper.addList(entityList);
    }

    //查询符合id集合条件的
    private Example getExample(Long[] idss) {
        if (!Validator.valid(idss)) {
            throw new BusinessException(BizErrorCode.ID_NULL);
        }
        List<Long> ids = Arrays.asList(idss);
        Example example = new Example(Category.class);
        example.createCriteria().andIn("id", ids);
        return example;
    }

}