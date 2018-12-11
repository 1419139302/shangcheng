package com.zzy.shopp.app.biz.mapper;

import com.zzy.shopp.app.biz.model.Review;
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
public interface ReviewMapper extends MyBaseMapper<Review> {

    Review getById(Long id);

    List<Review> getList(Review entity);

    List<Review> likeGetList(CommonSelectVo commonSelectVo);

    int update(Review entity);

    int deleteById(Long id);

    int add(Review entity);

    int addList(List<Review> entityList);

}