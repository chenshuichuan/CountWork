package com.ruoyi.project.count.modulus.service.impl;

import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.count.modulus.domain.Modulus;
import com.ruoyi.project.count.modulus.mapper.ModulusMapper;
import com.ruoyi.project.count.modulus.service.IModulusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系数管理Service业务层处理
 * 
 * @author beili
 * @date 2020-05-29
 */
@Service
public class ModulusServiceImpl implements IModulusService 
{
    @Autowired
    private ModulusMapper modulusMapper;

    /**
     * 查询系数管理
     * 
     * @param id 系数管理ID
     * @return 系数管理
     */
    @Override
    public Modulus selectModulusById(Long id)
    {
        return modulusMapper.selectModulusById(id);
    }

    /**
     * 查询系数管理列表
     * 
     * @param modulus 系数管理
     * @return 系数管理
     */
    @Override
    public List<Modulus> selectModulusList(Modulus modulus)
    {
        return modulusMapper.selectModulusList(modulus);
    }

    /**
     * 新增系数管理
     * 
     * @param modulus 系数管理
     * @return 结果
     */
    @Override
    public int insertModulus(Modulus modulus)
    {
        return modulusMapper.insertModulus(modulus);
    }

    /**
     * 修改系数管理
     * 
     * @param modulus 系数管理
     * @return 结果
     */
    @Override
    public int updateModulus(Modulus modulus)
    {
        return modulusMapper.updateModulus(modulus);
    }

    /**
     * 删除系数管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteModulusByIds(String ids)
    {
        return modulusMapper.deleteModulusByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除系数管理信息
     * 
     * @param id 系数管理ID
     * @return 结果
     */
    @Override
    public int deleteModulusById(Long id)
    {
        return modulusMapper.deleteModulusById(id);
    }
}
