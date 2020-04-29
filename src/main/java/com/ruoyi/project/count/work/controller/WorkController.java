package com.ruoyi.project.count.work.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.count.work.domain.Work;
import com.ruoyi.project.count.work.service.IWorkService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    private String prefix = "count/work";

    @Autowired
    private IWorkService workService;

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
        List<Work> list = workService.selectWorkList(work);
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
}
