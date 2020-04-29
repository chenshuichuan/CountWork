package com.ruoyi.project.count.check.service;

import com.ruoyi.project.count.check.domain.Check;

import java.util.List;

/**
 * 审核单Service接口
 * 
 * @author beili
 * @date 2020-04-29
 */
public interface ICheckService 
{
    /**
     * 查询审核单
     * @param id 审核单ID
     * @return 审核单
     */
    public Check selectCheckById(Integer id);

    /**
     * 查询审核单列表
     * @param check 审核单
     * @return 审核单集合
     */
    public List<Check> selectCheckList(Check check);

    /**
     * 新增审核单
     * @param check 审核单
     * @return 结果
     */
    public int insertCheck(Check check);

    /**
     * 修改审核单
     * @param check 审核单
     * @return 结果
     */
    public int updateCheck(Check check);

    /**
     * 批量删除审核单
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCheckByIds(String ids);

    /**
     * 删除审核单信息
     * @param id 审核单ID
     * @return 结果
     */
    public int deleteCheckById(Integer id);

}
