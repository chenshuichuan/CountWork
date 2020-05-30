package com.ruoyi.project.count.result.service;

import com.ruoyi.project.count.result.domain.Result;
import com.ruoyi.project.count.work.domain.Work;

import java.util.List;

/**
 * 工作量Service接口
 * 
 * @author beili
 * @date 2020-05-30
 */
public interface IResultService 
{
    /**
     * 查询工作量
     * 
     * @param id 工作量ID
     * @return 工作量
     */
    public Result selectResultById(Long id);

    /**
     * 查询工作量列表
     * 
     * @param result 工作量
     * @return 工作量集合
     */
    public List<Result> selectResultList(Result result);

    /**
     * 新增工作量
     * 
     * @param result 工作量
     * @return 结果
     */
    public int insertResult(Result result);

    /**
     * 修改工作量
     * 
     * @param result 工作量
     * @return 结果
     */
    public int updateResult(Result result);

    /**
     * 批量删除工作量
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteResultByIds(String ids);

    /**
     * 删除工作量信息
     * 
     * @param id 工作量ID
     * @return 结果
     */
    public int deleteResultById(Long id);

    public Result countResult(Work work);
}
