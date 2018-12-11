package com.zzy.shopp.app.biz.mapper;

import com.zzy.shopp.app.biz.model.Product;
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
public interface ProductMapper extends MyBaseMapper<Product> {

    Product getById(Long id);

    List<Product> getList(Product entity);

    List<Product> likeGetList(CommonSelectVo commonSelectVo);

    int update(Product entity);

    int deleteById(Long id);

    int add(Product entity);

    int addList(List<Product> entityList);

}