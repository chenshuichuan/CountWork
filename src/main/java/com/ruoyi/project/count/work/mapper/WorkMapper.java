package com.ruoyi.project.count.work.mapper;

import com.ruoyi.project.count.work.domain.Work;

import java.util.List;
import java.util.Map;

/**
 * 教学工作量Mapper接口
 * 
 * @author beili
 * @date 2020-04-28
 */
public interface WorkMapper 
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
     * 删除教学工作量
     * 
     * @param id 教学工作量ID
     * @return 结果
     */
    public int deleteWorkById(Integer id);

    /**
     * 批量删除教学工作量
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWorkByIds(String[] ids);

    public List<Work> selectWorkListByIds(Map<String,Object> map);
}
