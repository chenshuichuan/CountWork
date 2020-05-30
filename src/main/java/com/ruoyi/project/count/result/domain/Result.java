package com.ruoyi.project.count.result.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 工作量对象 cw_result
 * 
 * @author beili
 * @date 2020-05-30
 */
@Entity
@Table(name = "cw_result")
public class Result
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    @Column(name = "id" )
    private Long id;

    /** 系数类别 */
    @Excel(name = "系数类别")
    @Column(name = "modulus_kind" )
    private String modulusKind;

    /** 课程名称 */
    @Excel(name = "课程名称")
    @Column(name = "course_name" )
    private String courseName;

    /** 班级名称 */
    @Excel(name = "班级名称")
    @Column(name = "class_name" )
    private String className;

    /** 学时 */
    @Excel(name = "学时")
    @Column(name = "hours" )
    private Float hours;

    /** 班级人数 */
    @Excel(name = "班级人数")
    @Column(name = "number" )
    private Integer number;

    /** 工作量 */
    @Excel(name = "工作量")
    @Column(name = "result" )
    private Double result;

    /** 更新时间 */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 审核人 */
    @Excel(name = "审核人")
    @Column(name = "reviewer" )
    private String reviewer;
    public Result(){

    }
    public Result(Long id, String modulusKind, String courseName,
                  String className, Float hours, Integer number,
                  Double result, Date updateTime, String reviewer) {
        this.id = id;
        this.modulusKind = modulusKind;
        this.courseName = courseName;
        this.className = className;
        this.hours = hours;
        this.number = number;
        this.result = result;
        this.updateTime = updateTime;
        this.reviewer = reviewer;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setModulusKind(String modulusKind) 
    {
        this.modulusKind = modulusKind;
    }

    public String getModulusKind() 
    {
        return modulusKind;
    }
    public void setCourseName(String courseName) 
    {
        this.courseName = courseName;
    }

    public String getCourseName() 
    {
        return courseName;
    }
    public void setClassName(String className) 
    {
        this.className = className;
    }

    public String getClassName() 
    {
        return className;
    }
    public void setHours(Float hours)
    {
        this.hours = hours;
    }

    public Float getHours()
    {
        return hours;
    }
    public void setNumber(Integer number) 
    {
        this.number = number;
    }

    public Integer getNumber() 
    {
        return number;
    }
    public void setResult(Double result) 
    {
        this.result = result;
    }

    public Double getResult() 
    {
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("modulusKind", getModulusKind())
            .append("courseName", getCourseName())
            .append("className", getClassName())
            .append("hours", getHours())
            .append("number", getNumber())
            .append("result", getResult())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
