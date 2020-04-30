package com.ruoyi.project.count.work.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.count.check.domain.Check;
import com.ruoyi.project.count.check.service.CheckRepository;
import com.ruoyi.project.count.utils.IProcessService;
import com.ruoyi.project.count.work.domain.Work;
import com.ruoyi.project.count.work.service.IWorkService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 教学工作量Controller
 * 
 * @author beili
 * @date 2020-04-28
 */
@Controller
@RequestMapping("/count/work")
public class WorkController extends BaseController
{
    private static final org.slf4j.Logger logger= LoggerFactory.getLogger(WorkController.class);
    private String prefix = "count/work";

    @Autowired
    private IWorkService workService;
    @Autowired
    private CheckRepository checkRepository;

    @Autowired
    private IProcessService processService;

    @RequiresPermissions("count:work:view")
    @GetMapping()
    public String work()
    {
        return prefix + "/work";
    }

    /**
     * 查询教学工作量列表
     */
    @RequiresPermissions("count:work:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Work work)
    {
        startPage();
        List<Work> list = new ArrayList<>();
        if("admin".equals(ShiroUtils.getLoginName())){
            list =workService.selectWorkList(work);
        }
        else{
            List<Check> checkList =
                    checkRepository.findByReviewer(ShiroUtils.getLoginName());
           if(checkList.size()>0){
               String[] ids = new String[checkList.size()];
               for (int i = 0; i < checkList.size(); i++) {
                   ids[i] = String.valueOf(checkList.get(i).getWorkId());
               }
               list = workService.selectWorkListByIds(work,ids);
           }
           for (Work work1 :list) {
                List<Check> checks = checkRepository.findByReviewerAndWorkId(
                        ShiroUtils.getLoginName(),work1.getId());
                if(checks!=null&&checks.size()>0){
                    work1.setIsOk(checks.get(0).getPassed());
                }else {
                    logger.error("审核单数据错误！请检查数据");
                }
           }
        }
        return getDataTable(list);
    }

    /**
     * 导出教学工作量列表
     */
    @RequiresPermissions("count:work:export")
    @Log(title = "教学工作量", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Work work)
    {
        List<Work> list = workService.selectWorkList(work);
        ExcelUtil<Work> util = new ExcelUtil<Work>(Work.class);
        return util.exportExcel(list, "work");
    }

    /**
     * 新增教学工作量
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存教学工作量
     */
    @RequiresPermissions("count:work:add")
    @Log(title = "教学工作量", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Work work)
    {
        return toAjax(workService.insertWork(work));
    }

    /**
     * 修改教学工作量
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        Work work = workService.selectWorkById(id);
        mmap.put("work", work);
        return prefix + "/edit";
    }

    /**
     * 修改保存教学工作量
     */
    @RequiresPermissions("count:work:edit")
    @Log(title = "教学工作量", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Work work)
    {
        return toAjax(workService.updateWork(work));
    }

    /**
     * 删除教学工作量
     */
    @RequiresPermissions("count:work:remove")
    @Log(title = "教学工作量", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(workService.deleteWorkByIds(ids));
    }


    /**
     * 审核教学工作量
     */
    @GetMapping("/check/{id}")
    public String check(@PathVariable("id") Integer id, ModelMap mmap)
    {
        Work work = workService.selectWorkById(id);
        List<Check> checks = checkRepository.findByReviewerAndCheckedAndWorkId(
                ShiroUtils.getLoginName(),0,work.getId());
        if(checks!=null&&checks.size()==1){
            mmap.put("check",checks.get(0));
        }else {
            logger.error("审核单数据错误！请检查数据");
        }
        mmap.put("work", work);
        return prefix + "/check";
    }
    /**
     * 保存审核
     */
    @RequiresPermissions("count:work:edit")
    @Log(title = "保存审核", businessType = BusinessType.UPDATE)
    @PostMapping("/checkSave")
    @ResponseBody
    public AjaxResult checkSave(Check check)
    {
        logger.debug(check.toString());
        return toAjax(processService.dealCheck(ShiroUtils.getSysUser(),check));
    }
}
