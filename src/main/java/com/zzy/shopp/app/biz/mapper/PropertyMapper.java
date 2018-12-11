package com.zzy.shopp.app.biz.mapper;

import com.zzy.shopp.app.biz.model.Property;
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
public interface PropertyMapper extends MyBaseMapper<Property> {

    Property getById(Long id);

    List<Property> getList(Property entity);

    List<Property> likeGetList(CommonSelectVo commonSelectVo);

    int update(Property entity);

    int deleteById(Long id);

    int add(Property entity);

    int addList(List<Property> entityList);

}