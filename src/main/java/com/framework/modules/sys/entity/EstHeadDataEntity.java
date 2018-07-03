package com.framework.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MIT
 * @email framework@gmail.com
 * @date 2018-06-26 14:15:09
 */
@TableName("est_head_data")
public class EstHeadDataEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId
    private Long id;
    /**
     * 数据材料编号
     */
    private String dataCode;
    /**
     * 数据材料名称
     */
    private String dataName;
    /**
     *
     */
    private Integer deleted;
    /**
     *
     */
    private Integer version;
    /**
     *
     */
    private Date createTime;

    /**
     * 设置：主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：数据材料编号
     */
    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    /**
     * 获取：数据材料编号
     */
    public String getDataCode() {
        return dataCode;
    }

    /**
     * 设置：数据材料名称
     */
    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    /**
     * 获取：数据材料名称
     */
    public String getDataName() {
        return dataName;
    }

    /**
     * 设置：
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取：
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置：
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 获取：
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置：
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：
     */
    public Date getCreateTime() {
        return createTime;
    }
}
