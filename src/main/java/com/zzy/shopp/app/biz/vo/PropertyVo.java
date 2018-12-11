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
 * 表名：property
 * <p>
 * 描述：属性表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "属性表Vo")
@Table(name = "property")
public class PropertyVo implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "属性id")
    @NotNull(message = "属性id不能为空", groups = {UpdateProject.class})
    private Long id;

    @ApiModelProperty(value = "分类关联id")
    private Long cid;

    @ApiModelProperty(value = "属性名")
    private String name;

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
