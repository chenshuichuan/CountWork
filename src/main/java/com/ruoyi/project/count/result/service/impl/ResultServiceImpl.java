package com.ruoyi.project.count.result.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.count.modulus.domain.Modulus;
import com.ruoyi.project.count.modulus.service.ModulusRepository;
import com.ruoyi.project.count.result.domain.Result;
import com.ruoyi.project.count.result.mapper.ResultMapper;
import com.ruoyi.project.count.result.service.IResultService;
import com.ruoyi.project.count.work.domain.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 工作量Service业务层处理
 * 
 * @author beili
 * @date 2020-05-30
 */
@Service
public class ResultServiceImpl implements IResultService 
{
    @Autowired
    private ResultMapper resultMapper;
    @Autowired
    private ModulusRepository modulusRepository;
    /**
     * 查询工作量
     * 
     * @param id 工作量ID
     * @return 工作量
     */
    @Override
    public Result selectResultById(Long id)
    {
        return resultMapper.selectResultById(id);
    }

    /**
     * 查询工作量列表
     * 
     * @param result 工作量
     * @return 工作量
     */
    @Override
    public List<Result> selectResultList(Result result)
    {
        return resultMapper.selectResultList(result);
    }

    /**
     * 新增工作量
     * 
     * @param result 工作量
     * @return 结果
     */
    @Override
    public int insertResult(Result result)
    {
        return resultMapper.insertResult(result);
    }

    /**
     * 修改工作量
     * 
     * @param result 工作量
     * @return 结果
     */
    @Override
    public int updateResult(Result result)
    {
        result.setUpdateTime(DateUtils.getNowDate());
        return resultMapper.updateResult(result);
    }

    /**
     * 删除工作量对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteResultByIds(String ids)
    {
        return resultMapper.deleteResultByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除工作量信息
     * 
     * @param id 工作量ID
     * @return 结果
     */
    @Override
    public int deleteResultById(Long id)
    {
        return resultMapper.deleteResultById(id);
    }

    @Override
    public Result countResult(Work work) {
        Modulus modulus = modulusRepository.findByName(work.getModulusKind());
        Result result = new Result(work.getId().longValue(), work.getModulusKind(),work.getCourseName()
        ,work.getClassName(),work.getHours()+work.getExperimentHours(),work.getNumber(),
                0.0,new Date(),work.getReviewer());
        float weekHours = getWeekHours(work.getWeekHours());
        double count =0.0;
        if("本科授课".equals(work.getModulusKind())){
            count = (work.getHours()+work.getExperimentHours())*(90+work.getNumber())/120.0;
        }
        else if("软件".equals(work.getModulusKind()) || "硬件".equals(work.getModulusKind())){
            count = (90+work.getNumber())*(work.getHours()/weekHours)*24*modulus.getValue()/120.0;
        }
        else {
            count = work.getHours()*modulus.getValue();
        }
        result.setResult(count);
        return result;
    }
    private float getWeekHours(String stringHours){
        int startIndex = stringHours.indexOf("(");
        int endIndex = stringHours.indexOf(")");
        return Float.valueOf(stringHours.substring(startIndex+1,endIndex));
    }
}
