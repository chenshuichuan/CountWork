package com.ruoyi.project.count.work.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 教学工作量对象 cw_work
 * 
 * @author beili
 * @date 2020-04-28
 */
public class Work extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Integer id;

    /** 学期 */
    @Excel(name = "学期")
    private String term;

    /** 学院 */
    @Excel(name = "学院")
    private String academy;

    /** 年级 */
    @Excel(name = "年级")
    private String grade;

    /** 课程代码 */
    @Excel(name = "课程代码")
    private String courseCode;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 专业名称 */
    @Excel(name = "专业名称")
    private String major;

    /** 班级名称 */
    @Excel(name = "班级名称")
    private String className;

    /** 学分 */
    @Excel(name = "学分")
    private Float credit;

    /** 学时 */
    @Excel(name = "学时")
    private Float hours;

    /** 实验学时 */
    @Excel(name = "实验学时")
    private Float experimentHours;

    /** 审核人 */
    @Excel(name = "审核人")
    private String reviewer;

    /** 是否通过 */
    @Excel(name = "是否通过")
    private Integer isOk;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Integer status;

    /** 下一步骤 */
    @Excel(name = "下一步骤")
    private Integer nextStatus;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setTerm(String term) 
    {
        this.term = term;
    }

    public String getTerm() 
    {
        return term;
    }
    public void setAcademy(String academy) 
    {
        this.academy = academy;
    }

    public String getAcademy() 
    {
        return academy;
    }
    public void setGrade(String grade) 
    {
        this.grade = grade;
    }

    public String getGrade() 
    {
        return grade;
    }
    public void setCourseCode(String courseCode) 
    {
        this.courseCode = courseCode;
    }

    public String getCourseCode() 
    {
        return courseCode;
    }
    public void setCourseName(String courseName) 
    {
        this.courseName = courseName;
    }

    public String getCourseName() 
    {
        return courseName;
    }
    public void setMajor(String major) 
    {
        this.major = major;
    }

    public String getMajor() 
    {
        return major;
    }
    public void setClassName(String className) 
    {
        this.className = className;
    }

    public String getClassName() 
    {
        return className;
    }
    public void setCredit(Float credit)
    {
        this.credit = credit;
    }

    public Float getCredit()
    {
        return credit;
    }
    public void setHours(Float hours)
    {
        this.hours = hours;
    }

    public Float getHours()
    {
        return hours;
    }
    public void setExperimentHours(Float experimentHours)
    {
        this.experimentHours = experimentHours;
    }

    public Float getExperimentHours()
    {
        return experimentHours;
    }
    public void setReviewer(String reviewer) 
    {
        this.reviewer = reviewer;
    }

    public String getReviewer() 
    {
        return reviewer;
    }
    public void setIsOk(Integer isOk) 
    {
        this.isOk = isOk;
    }

    public Integer getIsOk() 
    {
        return isOk;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setNextStatus(Integer nextStatus)
    {
        this.nextStatus = nextStatus;
    }

    public Integer getNextStatus()
    {
        return nextStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("term", getTerm())
            .append("academy", getAcademy())
            .append("grade", getGrade())
            .append("courseCode", getCourseCode())
            .append("courseName", getCourseName())
            .append("major", getMajor())
            .append("className", getClassName())
            .append("credit", getCredit())
            .append("hours", getHours())
            .append("experimentHours", getExperimentHours())
            .append("reviewer", getReviewer())
            .append("isOk", getIsOk())
            .append("status", getStatus())
            .append("nextStatus", getNextStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
