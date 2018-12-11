package com.zzy.shopp.app.biz.mapper;

import com.zzy.shopp.app.biz.model.Orderitem;
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
public interface OrderitemMapper extends MyBaseMapper<Orderitem> {

    Orderitem getById(Long id);

    List<Orderitem> getList(Orderitem entity);

    List<Orderitem> likeGetList(CommonSelectVo commonSelectVo);

    int update(Orderitem entity);

    int deleteById(Long id);

    int add(Orderitem entity);

    int addList(List<Orderitem> entityList);

}