package com.zzy.shopp.app.biz.mapper;

import com.zzy.shopp.app.biz.model.Property;
import com.zzy.shopp.app.biz.model.Propertyvalue;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;
import com.zzy.shopp.app.lib.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 类说明:
 *
 * @author Heaton
 * Created by noname on 2018-11-30 9:34:06
 */
@Mapper
public interface PropertyvalueMapper extends MyBaseMapper<Propertyvalue> {

    Propertyvalue getById(Long id);

    List<Propertyvalue> getList(Propertyvalue entity);

    List<Propertyvalue> likeGetList(CommonSelectVo commonSelectVo);

    int update(Propertyvalue entity);

    int deleteById(Long id);

    int add(Propertyvalue entity);

    int addList(List<Propertyvalue> entityList);

    List<Property> listByCategory(Long id);

    Propertyvalue getByPropertyAndProduct(Map map);
}