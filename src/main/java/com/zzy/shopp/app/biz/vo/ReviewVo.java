package com.zzy.shopp.app.biz.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zzy.shopp.app.validGroup.UpdateProject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体bean
 *
 * @author Heaton
 * <p>
 * 表名：review
 * <p>
 * 描述：评论表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "评论表Vo")
@Table(name = "review")
public class ReviewVo implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评价id")
    @NotNull(message = "评价id不能为空", groups = {UpdateProject.class})
    private Long id;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "用户关联id")
    private Long uid;

    @ApiModelProperty(value = "产品关联id")
    private Long pid;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    @ApiModelProperty(value = "删除时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deleteTime;

    @ApiModelProperty(value = "是否删除")
    private Integer idel;
}
