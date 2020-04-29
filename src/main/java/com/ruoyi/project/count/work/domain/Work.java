package com.ruoyi.project.count.work.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * 教学工作量对象 cw_work
 * 
 * @author beili
 * @date 2020-04-28
 */
@Entity
@Table(name = "cw_work")
public class Work
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id" )
    private Integer id;

    /** 学期 */
    @Excel(name = "学期")
    @Column(name = "term" )
    private String term;

    /** 学院 */
    @Excel(name = "学院")
    @Column(name = "academy" )
    private String academy;

    /** 年级 */
    @Excel(name = "年级")
    @Column(name = "grade" )
    private String grade;

    /** 课程代码 */
    @Excel(name = "课程代码")
    @Column(name = "course_code" )
    private String courseCode;

    /** 课程名称 */
    @Excel(name = "课程名称")
    @Column(name = "course_name" )
    private String courseName;

    /** 专业名称 */
    @Excel(name = "专业名称")
    @Column(name = "major" )
    private String major;

    /** 班级名称 */
    @Excel(name = "班级名称")
    @Column(name = "class_name" )
    private String className;
    /** 班级人数 */
    @Excel(name = "班级人数")
    @Column(name = "number" )
    private Integer number;
    /** 学分 */
    @Excel(name = "学分")
    @Column(name = "credit" )
    private Float credit;

    /** 学时 */
    @Excel(name = "学时")
    @Column(name = "hours" )
    private Float hours;

    /** 实验学时 */
    @Excel(name = "实验学时")
    @Column(name = "experiment_hours" )
    private Float experimentHours;

    /** 审核人 */
    @Excel(name = "审核人")
    @Column(name = "reviewer" )
    private String reviewer;

    /** 是否通过 */
    @Excel(name = "是否通过")
    @Column(name = "is_ok" )
    private Integer isOk;

    /** 审核状态 */
    @Excel(name = "审核状态")
    @Column(name = "status" )
    private Integer status;

    /** 下一步骤 */
    @Excel(name = "下一步骤")
    @Column(name = "next_status" )
    private Integer nextStatus;
    /** 已统计 */
    @Excel(name = "已统计")
    @Column(name = "counted" )
    private Integer counted;

    /** 创建时间 */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /** 更新时间 */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /****/
    @Column(name = "check_id" )
    private Integer checkId;

    public Work() {
    }
    public Work(Date date,String term, String academy) {
        this.createTime = date;
        this.term = term;
        this.academy = academy;
    }
    public void setSomeStatus(int isOk,int status,int nextStatus,int counted,Date updateTime) {
        this.isOk = isOk;
        this.status = status;
        this.nextStatus = nextStatus;
        this.updateTime = updateTime;
        this.counted = counted;
    }

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCounted() {
        return counted;
    }

    public void setCounted(Integer counted) {
        this.counted = counted;
    }

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
