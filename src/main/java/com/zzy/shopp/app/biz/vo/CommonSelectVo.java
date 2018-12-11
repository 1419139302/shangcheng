package com.zzy.shopp.app.biz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(description = "公用查询对象")
@Builder
public class CommonSelectVo {
    @ApiModelProperty(value = "页码")
    private Integer pageNum;
    @ApiModelProperty(value = "每页条数")
    private Integer pageSize;
    @ApiModelProperty(value = "排序字段")
    private String sort;
    @ApiModelProperty(value = "排序方式")
    private String order;
    @ApiModelProperty(value = "查询条件")
    private String queryCriteria;
    @ApiModelProperty(value = "关联id")
    private Long id;
    @ApiModelProperty(value = "图片类型")
    private String type;
}
