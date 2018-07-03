package com.framework.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 部门管理
 */
@TableName("sys_dept")
@ApiModel(value = "部门对象", description = "sys_dept")
public class SysDeptEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    // 部门ID
    @TableId
    @ApiModelProperty(value = "部门id", name = "deptId", required = true)
    private Long deptId;
    // 上级部门ID，一级部门为0
    @ApiModelProperty(value = "部门上级id", name = "parentId", required = true)
    private Long parentId;
    // 部门名称
    @ApiModelProperty(value = "部门名称", name = "name", required = true)
    private String name;
    // 上级部门名称
    @ApiModelProperty(value = "部门上级名称", name = "parentName", required = true)
    @TableField(exist = false)
    private String parentName;
    // 排序
    @ApiModelProperty(value = "部门排序", name = "orderNum", required = true)
    private Integer orderNum;

    @TableLogic
    @ApiModelProperty(value = "部门是否删除", name = "delFlag", required = true)
    private Integer delFlag;
    /**
     * ztree属性
     */
    @TableField(exist = false)
    private Boolean open;
    @TableField(exist = false)
    private List<?> list;

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getDeptId() {
        return deptId;
    }

    /**
     * 设置：上级部门ID，一级部门为0
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：上级部门ID，一级部门为0
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置：部门名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：部门名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：排序
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取：排序
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
