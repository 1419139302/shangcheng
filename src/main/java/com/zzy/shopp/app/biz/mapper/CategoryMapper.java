package com.zzy.shopp.app.biz.mapper;

import com.zzy.shopp.app.biz.model.Category;
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
public interface CategoryMapper extends MyBaseMapper<Category> {

    Category getById(Long id);

    List<Category> getList(Category entity);

    List<Category> likeGetList(CommonSelectVo commonSelectVo);

    List<Category> likeGetListAndChilds(CommonSelectVo commonSelectVo);

    int update(Category entity);

    int deleteById(Long id);

    int add(Category entity);

    int addList(List<Category> entityList);

}