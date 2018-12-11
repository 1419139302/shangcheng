package com.zzy.shopp.app.biz.mapper;

import com.zzy.shopp.app.biz.model.Productimage;
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
public interface ProductimageMapper extends MyBaseMapper<Productimage> {

    Productimage getById(Long id);

    List<Productimage> getList(Productimage entity);

    List<Productimage> likeGetList(CommonSelectVo commonSelectVo);

    int update(Productimage entity);

    int deleteById(Long id);

    int add(Productimage entity);

    int addList(List<Productimage> entityList);

}