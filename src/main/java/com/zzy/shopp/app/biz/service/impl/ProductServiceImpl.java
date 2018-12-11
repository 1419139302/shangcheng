package com.zzy.shopp.app.biz.service.impl;

import com.zzy.shopp.app.biz.mapper.ProductMapper;
import com.zzy.shopp.app.biz.model.Category;
import com.zzy.shopp.app.biz.model.Product;
import com.zzy.shopp.app.biz.service.ProductService;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;
import com.zzy.shopp.app.exception.BizErrorCode;
import com.zzy.shopp.app.exception.BusinessException;
import com.zzy.shopp.app.util.PojoPageHandler;
import com.zzy.shopp.app.util.Validator;
import com.zzy.shopp.app.util.id.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 类说明:
 *
 * @author Heaton
 * Created by noname on 2018-11-30 9:34:06
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product getById(Long id) {
        return productMapper.getById(id);
    }

    @Override
    public List<Product> getList(Product entity) {
        List<Product> resut = null;
        resut = productMapper.getList(entity);
        return resut;
    }

    @Override
    public List<Product> likeGetList(CommonSelectVo commonSelectVo) {
        PojoPageHandler.doPagingAndSorting(commonSelectVo);
        List<Product> resut = null;
        resut = productMapper.likeGetList(commonSelectVo);
        return resut;
    }

    @Override
    public int update(Product entity) {
        entity.setUpdateTime(new Date());
        return productMapper.update(entity);
    }

    @Override
    public int deleteById(Long id) {
        return productMapper.deleteById(id);
    }

    @Override
    public int deleteList(Long[] ids) {
        Example example = getExample(ids);
        Product product = Product.builder().isDel(true).build();
        return productMapper.updateByExampleSelective(product, example);
    }

    @Override
    public int add(Product entity) {
        return productMapper.add(entity);
    }

    @Override
    public int addList(List<Product> entityList) {
        for (Product entity : entityList) {
            entity.setId(IdUtil.generateId());
            entity.setCreateTime(new Date());
            entity.setIsDel(false);
        }
        return productMapper.addList(entityList);
    }

    //查询符合id集合条件的
    private Example getExample(Long[] idss) {
        if (!Validator.valid(idss)) {
            throw new BusinessException(BizErrorCode.ID_NULL);
        }
        List<Long> ids = Arrays.asList(idss);
        Example example = new Example(Product.class);
        example.createCriteria().andIn("id", ids);
        return example;
    }

    public void fillByRow(List<Category> categorys) {
        int productNumberEachRow = 8;
        for (Category category : categorys) {
            List<Product> products = category.getProducts();
            List<List<Product>> productsByRow = new ArrayList<>();
            for (int i = 0; i < products.size(); i += productNumberEachRow) {
                int size = i + productNumberEachRow;
                size = size > products.size() ? products.size() : size;
                List<Product> productsOfEachRow = products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            category.setProductsByRow(productsByRow);
        }
    }
}