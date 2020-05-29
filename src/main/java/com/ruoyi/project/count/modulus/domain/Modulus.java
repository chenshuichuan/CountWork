package com.ruoyi.project.count.modulus.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

/**
 * 系数管理对象 cw_modulus
 * 
 * @author beili
 * @date 2020-05-29
 */
@Entity
@Table(name = "cw_modulus")
public class Modulus
{
    private static final long serialVersionUID = 1L;

    /** Id */
    /** id */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id" )
    private Long id;

    /** 系数名称 */
    @Excel(name = "系数名称")
    @Column(name = "name" )
    private String name;

    /** 系数值 */
    @Excel(name = "系数值")
    @Column(name = "value" )
    private Float value;
    /** 系数值 */
    @Excel(name = "系数值")
    @Column(name = "remark" )
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setValue(Float value)
    {
        this.value = value;
    }

    public Float getValue()
    {
        return value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("value", getValue())
            .append("remark", getRemark())
            .toString();
    }
}
