package com.ruoyi.project.count.check.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * 审核单对象 cw_check
 * 
 * @author beili
 * @date 2020-04-29
 */
@Entity
@Table(name = "cw_check")
public class Check
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id" )
    private Integer id;

    /** 当前所处状态 */
    @Excel(name = "当前所处状态")
    @Column(name = "status" )
    private Integer status;

    /** 审核人 */
    @Excel(name = "审核人")
    @Column(name = "reviewer" )
    private String reviewer;

    /** 关联的work */
    @Excel(name = "关联的work")
    @Column(name = "work_id" )
    private Integer workId;

    /** 是否审核 */
    @Excel(name = "是否审核")
    @Column(name = "checked" )
    private Integer checked;

    /** 是否通过 */
    @Excel(name = "是否通过")
    @Column(name = "passed" )
    private Integer passed;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time" )
    private Date createTime;
    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time" )
    private Date updateTime;

    /** 备注 */
    @Excel(name = "备注")
    @Column(name = "remark" )
    private String remark;
    public Check(){

    }
    public Check(Integer status, String reviewer, Integer workId, Integer checked, String remark, Integer passed,
                 Date createTime, Date updateTime) {
        this.status = status;
        this.reviewer = reviewer;
        this.workId = workId;
        this.checked = checked;
        this.passed = passed;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setReviewer(String reviewer) 
    {
        this.reviewer = reviewer;
    }

    public String getReviewer() 
    {
        return reviewer;
    }
    public void setWorkId(Integer workId)
    {
        this.workId = workId;
    }

    public Integer getWorkId()
    {
        return workId;
    }
    public void setChecked(Integer checked) 
    {
        this.checked = checked;
    }

    public Integer getChecked() 
    {
        return checked;
    }
    public void setPassed(Integer passed) 
    {
        this.passed = passed;
    }

    public Integer getPassed() 
    {
        return passed;
    }

    public boolean checked(){
        return 1 == checked;
    }
    public boolean passed(){
        return 1 == passed;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("status", getStatus())
            .append("reviewer", getReviewer())
            .append("workId", getWorkId())
            .append("checked", getChecked())
            .append("remark", getRemark())
            .append("passed", getPassed())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
