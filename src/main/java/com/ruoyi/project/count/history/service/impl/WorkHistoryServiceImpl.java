package com.ruoyi.project.count.history.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.count.history.domain.WorkHistory;
import com.ruoyi.project.count.history.mapper.WorkHistoryMapper;
import com.ruoyi.project.count.history.service.IWorkHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 更改历史数据Service业务层处理
 * 
 * @author beili
 * @date 2020-05-01
 */
@Service
public class WorkHistoryServiceImpl implements IWorkHistoryService 
{
    @Autowired
    private WorkHistoryMapper workHistoryMapper;

    /**
     * 查询更改历史数据
     * 
     * @param id 更改历史数据ID
     * @return 更改历史数据
     */
    @Override
    public WorkHistory selectWorkHistoryById(Integer id)
    {
        return workHistoryMapper.selectWorkHistoryById(id);
    }

    /**
     * 查询更改历史数据列表
     * 
     * @param workHistory 更改历史数据
     * @return 更改历史数据
     */
    @Override
    public List<WorkHistory> selectWorkHistoryList(WorkHistory workHistory)
    {
        return workHistoryMapper.selectWorkHistoryList(workHistory);
    }

    /**
     * 新增更改历史数据
     * 
     * @param workHistory 更改历史数据
     * @return 结果
     */
    @Override
    public int insertWorkHistory(WorkHistory workHistory)
    {
        workHistory.setCreateTime(DateUtils.getNowDate());
        return workHistoryMapper.insertWorkHistory(workHistory);
    }

    /**
     * 修改更改历史数据
     * 
     * @param workHistory 更改历史数据
     * @return 结果
     */
    @Override
    public int updateWorkHistory(WorkHistory workHistory)
    {
        workHistory.setUpdateTime(DateUtils.getNowDate());
        return workHistoryMapper.updateWorkHistory(workHistory);
    }

    /**
     * 删除更改历史数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWorkHistoryByIds(String ids)
    {
        return workHistoryMapper.deleteWorkHistoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除更改历史数据信息
     * 
     * @param id 更改历史数据ID
     * @return 结果
     */
    @Override
    public int deleteWorkHistoryById(Integer id)
    {
        return workHistoryMapper.deleteWorkHistoryById(id);
    }
}
