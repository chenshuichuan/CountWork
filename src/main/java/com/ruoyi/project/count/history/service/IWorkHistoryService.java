package com.ruoyi.project.count.history.service;

import com.ruoyi.project.count.history.domain.WorkHistory;

import java.util.List;

/**
 * 更改历史数据Service接口
 * 
 * @author beili
 * @date 2020-05-01
 */
public interface IWorkHistoryService 
{
    /**
     * 查询更改历史数据
     * 
     * @param id 更改历史数据ID
     * @return 更改历史数据
     */
    public WorkHistory selectWorkHistoryById(Integer id);

    /**
     * 查询更改历史数据列表
     * 
     * @param workHistory 更改历史数据
     * @return 更改历史数据集合
     */
    public List<WorkHistory> selectWorkHistoryList(WorkHistory workHistory);

    /**
     * 新增更改历史数据
     * 
     * @param workHistory 更改历史数据
     * @return 结果
     */
    public int insertWorkHistory(WorkHistory workHistory);

    /**
     * 修改更改历史数据
     * 
     * @param workHistory 更改历史数据
     * @return 结果
     */
    public int updateWorkHistory(WorkHistory workHistory);

    /**
     * 批量删除更改历史数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWorkHistoryByIds(String ids);

    /**
     * 删除更改历史数据信息
     * 
     * @param id 更改历史数据ID
     * @return 结果
     */
    public int deleteWorkHistoryById(Integer id);
}
