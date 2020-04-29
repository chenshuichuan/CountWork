package com.ruoyi.project.count.check.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.count.check.domain.Check;
import com.ruoyi.project.count.check.mapper.CheckMapper;
import com.ruoyi.project.count.check.service.ICheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 审核单Service业务层处理
 * 
 * @author beili
 * @date 2020-04-29
 */
@Service
public class CheckServiceImpl implements ICheckService 
{
    @Autowired
    private CheckMapper checkMapper;

    /**
     * 查询审核单
     * 
     * @param id 审核单ID
     * @return 审核单
     */
    @Override
    public Check selectCheckById(Integer id)
    {
        return checkMapper.selectCheckById(id);
    }

    /**
     * 查询审核单列表
     * 
     * @param check 审核单
     * @return 审核单
     */
    @Override
    public List<Check> selectCheckList(Check check)
    {
        return checkMapper.selectCheckList(check);
    }

    /**
     * 新增审核单
     * 
     * @param check 审核单
     * @return 结果
     */
    @Override
    public int insertCheck(Check check)
    {
        check.setCreateTime(DateUtils.getNowDate());
        return checkMapper.insertCheck(check);
    }

    /**
     * 修改审核单
     * 
     * @param check 审核单
     * @return 结果
     */
    @Override
    public int updateCheck(Check check)
    {
        check.setUpdateTime(DateUtils.getNowDate());
        return checkMapper.updateCheck(check);
    }

    /**
     * 删除审核单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCheckByIds(String ids)
    {
        return checkMapper.deleteCheckByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除审核单信息
     * 
     * @param id 审核单ID
     * @return 结果
     */
    @Override
    public int deleteCheckById(Integer id)
    {
        return checkMapper.deleteCheckById(id);
    }
}
