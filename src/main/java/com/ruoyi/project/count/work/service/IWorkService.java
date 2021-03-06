package com.ruoyi.project.count.work.service;

import com.ruoyi.project.count.work.domain.Work;

import java.util.List;

/**
 * 教学工作量Service接口
 * 
 * @author beili
 * @date 2020-04-28
 */
public interface IWorkService 
{
    /**
     * 查询教学工作量
     * 
     * @param id 教学工作量ID
     * @return 教学工作量
     */
    public Work selectWorkById(Integer id);

    /**
     * 查询教学工作量列表
     * 
     * @param work 教学工作量
     * @return 教学工作量集合
     */
    public List<Work> selectWorkList(Work work);

    /**
     * 新增教学工作量
     * 
     * @param work 教学工作量
     * @return 结果
     */
    public int insertWork(Work work);

    /**
     * 修改教学工作量
     * 
     * @param work 教学工作量
     * @return 结果
     */
    public int updateWork(Work work);

    /**
     * 批量删除教学工作量
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWorkByIds(String ids);

    /**
     * 删除教学工作量信息
     * 
     * @param id 教学工作量ID
     * @return 结果
     */
    public int deleteWorkById(Integer id);

    public List<Work> selectWorkListByIds(Work work,String[]ids);
}
