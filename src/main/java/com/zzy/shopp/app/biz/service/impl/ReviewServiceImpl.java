package com.zzy.shopp.app.biz.service.impl;

import com.zzy.shopp.app.biz.mapper.ReviewMapper;
import com.zzy.shopp.app.biz.model.Review;
import com.zzy.shopp.app.biz.service.ReviewService;
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
@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public Review getById(Long id) {
        return reviewMapper.getById(id);
    }

    @Override
    public List<Review> getList(Review entity) {
        List<Review> resut = null;
        resut = reviewMapper.getList(entity);
        return resut;
    }

    @Override
    public List<Review> likeGetList(CommonSelectVo commonSelectVo) {
        PojoPageHandler.doPagingAndSorting(commonSelectVo);
        List<Review> resut = null;
        resut = reviewMapper.likeGetList(commonSelectVo);
        return resut;
    }

    @Override
    public int update(Review entity) {
        return reviewMapper.update(entity);
    }

    @Override
    public int deleteById(Long id) {
        return reviewMapper.deleteById(id);
    }

    @Override
    public int deleteList(Long[] ids) {
        Example example = getExample(ids);
        Review review = Review.builder().isDel(true).build();
        return reviewMapper.updateByExampleSelective(review, example);
    }

    @Override
    public int add(Review entity) {
        return reviewMapper.add(entity);
    }

    @Override
    public int addList(List<Review> entityList) {
        for (Review entity : entityList) {
            entity.setId(IdUtil.generateId());
            entity.setIsDel(false);
        }
        return reviewMapper.addList(entityList);
    }

    //查询符合id集合条件的
    private Example getExample(Long[] idss) {
        if (!Validator.valid(idss)) {
            throw new BusinessException(BizErrorCode.ID_NULL);
        }
        List<Long> ids = Arrays.asList(idss);
        Example example = new Example(Review.class);
        example.createCriteria().andIn("id", ids);
        return example;
    }

}