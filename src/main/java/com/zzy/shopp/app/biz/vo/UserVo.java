package com.zzy.shopp.app.biz.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zzy.shopp.app.validGroup.AddProject;
import com.zzy.shopp.app.validGroup.UpdateProject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体bean
 * @author Heaton
 * <p>
 * 表名：s_user
 * <p>
 * 描述：用户表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户表Vo")
public class UserVo implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户id")
	@NotNull(message = "用户id不能为空", groups = {UpdateProject.class})
	private Long id;

	@ApiModelProperty(value = "用户名")
	@NotNull(message = "用户名不能为空", groups = {AddProject.class})
	private String name;

	@ApiModelProperty(value = "密碼")
	@NotNull(message = "密碼不能为空", groups = {AddProject.class})
	private String password;

	@ApiModelProperty(value = "盐值")
	@NotNull(message = "盐值不能为空", groups = {AddProject.class})
	private String salt;

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
	private Integer isDel;
}
