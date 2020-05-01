package com.ruoyi.project.count.work.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.count.history.domain.WorkHistory;
import com.ruoyi.project.count.history.service.IWorkHistoryService;
import com.ruoyi.project.count.work.domain.Work;
import com.ruoyi.project.count.work.mapper.WorkMapper;
import com.ruoyi.project.count.work.service.IWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学工作量Service业务层处理
 * 
 * @author beili
 * @date 2020-04-28
 */
@Service
public class WorkServiceImpl implements IWorkService 
{
    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private IWorkHistoryService workHistoryService;
    /**
     * 查询教学工作量
     * 
     * @param id 教学工作量ID
     * @return 教学工作量
     */
    @Override
    public Work selectWorkById(Integer id)
    {
        return workMapper.selectWorkById(id);
    }

    /**
     * 查询教学工作量列表
     * 
     * @param work 教学工作量
     * @return 教学工作量
     */
    @Override
    public List<Work> selectWorkList(Work work)
    {
        return workMapper.selectWorkList(work);
    }

    /**
     * 新增教学工作量
     * 
     * @param work 教学工作量
     * @return 结果
     */
    @Override
    public int insertWork(Work work)
    {
        work.setCreateTime(DateUtils.getNowDate());
        return workMapper.insertWork(work);
    }

    /**
     * 修改教学工作量
     * 
     * @param work 教学工作量
     * @return 结果
     */
    @Override
    public int updateWork(Work work)
    {
        work.setUpdateTime(DateUtils.getNowDate());
        Work oldWork = workMapper.selectWorkById(work.getId());
        WorkHistory workHistory = genWorkHistory(oldWork);
        workHistoryService.insertWorkHistory(workHistory);
        return workMapper.updateWork(work);
    }
    private WorkHistory genWorkHistory(Work work){
        WorkHistory workHistory = new WorkHistory();
        workHistory.setWorkId(work.getId());
        workHistory.setTerm(work.getTerm());
        workHistory.setAcademy(work.getAcademy());
        workHistory.setGrade(work.getGrade());
        workHistory.setCourseCode(work.getCourseCode());
        workHistory.setCourseName(work.getCourseName());
        workHistory.setMajor(work.getMajor());
        workHistory.setClassName(work.getClassName());
        workHistory.setNumber(work.getNumber());
        workHistory.setCredit(work.getCredit());
        workHistory.setHours(work.getHours());
        workHistory.setExperimentHours(work.getExperimentHours());
        workHistory.setReviewer(ShiroUtils.getLoginName());
        workHistory.setUpdateTime(new Date());
        return workHistory;
    }
    /**
     * 删除教学工作量对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWorkByIds(String ids)
    {
        return workMapper.deleteWorkByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除教学工作量信息
     * 
     * @param id 教学工作量ID
     * @return 结果
     */
    @Override
    public int deleteWorkById(Integer id)
    {
        return workMapper.deleteWorkById(id);
    }

    @Override
    public List<Work> selectWorkListByIds(Work work, String[] ids) {
        Map<String,Object> map = new HashMap<>();

        map.put("term",work.getTerm());
        map.put("academy",work.getAcademy());
        map.put("grade",work.getGrade());
        map.put("courseCode",work.getCourseCode());
        map.put("courseName",work.getCourseName());

        map.put("major",work.getMajor());
        map.put("reviewer",work.getReviewer());
        map.put("isOk",work.getIsOk());

        map.put("status",work.getStatus());
        map.put("nextStatus",work.getNextStatus());
        map.put("counted",work.getCounted());

        map.put("array",ids);
        return workMapper.selectWorkListByIds(map);
    }


}
