package com.zzy.shopp.app.biz.service;

import com.zzy.shopp.app.biz.model.Review;
import com.zzy.shopp.app.biz.vo.CommonSelectVo;

import java.util.List;

/**
 * 类说明:
 *
 * @author Heaton
 * Created by noname on 2018-11-30 9:34:06
 */
public interface ReviewService {


    public Review getById(Long id);


    public List<Review> getList(Review entity);


    public List<Review> likeGetList(CommonSelectVo commonSelectVo);


    public int update(Review entity);


    public int deleteById(Long id);


    public int deleteList(Long[] ids);


    public int add(Review entity);


    public int addList(List<Review> entityList);

}