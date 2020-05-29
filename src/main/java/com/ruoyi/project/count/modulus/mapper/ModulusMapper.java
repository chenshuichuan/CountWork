package com.ruoyi.project.count.modulus.mapper;

import com.ruoyi.project.count.modulus.domain.Modulus;

import java.util.List;

/**
 * 系数管理Mapper接口
 * 
 * @author beili
 * @date 2020-05-29
 */
public interface ModulusMapper 
{
    /**
     * 查询系数管理
     * 
     * @param id 系数管理ID
     * @return 系数管理
     */
    public Modulus selectModulusById(Long id);

    /**
     * 查询系数管理列表
     * 
     * @param modulus 系数管理
     * @return 系数管理集合
     */
    public List<Modulus> selectModulusList(Modulus modulus);

    /**
     * 新增系数管理
     * 
     * @param modulus 系数管理
     * @return 结果
     */
    public int insertModulus(Modulus modulus);

    /**
     * 修改系数管理
     * 
     * @param modulus 系数管理
     * @return 结果
     */
    public int updateModulus(Modulus modulus);

    /**
     * 删除系数管理
     * 
     * @param id 系数管理ID
     * @return 结果
     */
    public int deleteModulusById(Long id);

    /**
     * 批量删除系数管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteModulusByIds(String[] ids);
}
