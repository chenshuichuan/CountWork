package com.ruoyi.project.count.utils;

import com.ruoyi.project.count.check.domain.Check;
import com.ruoyi.project.count.work.domain.Work;
import com.ruoyi.project.system.user.domain.User;

/**
 *
 * @author beili
 * @date 2020-04-29
 */
public interface IProcessService
{
    /**
     * 生成审核单
     * @param work 审核单ID
     * @param status 要生成的审核单的状态
     * @param reviewer 审核单的审核人
     * @return 审核单
     */
    public Integer addAndSaveCheck(Work work, Integer status,String reviewer) ;

    /**
     * 审核
     * @param user 审核人员
     * @param check 审核人员已经审核过的审核单(审核单里标明是否通过本次审核)
     * @return 审核单
     */
    public int dealCheck(User user,Check check);
}
