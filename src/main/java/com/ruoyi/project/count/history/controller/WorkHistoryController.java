package com.ruoyi.project.count.history.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.count.history.domain.WorkHistory;
import com.ruoyi.project.count.history.service.IWorkHistoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 更改历史数据Controller
 * 
 * @author beili
 * @date 2020-05-01
 */
@Controller
@RequestMapping("/count/history")
public class WorkHistoryController extends BaseController
{
    private String prefix = "count/history";

    @Autowired
    private IWorkHistoryService workHistoryService;

    @RequiresPermissions("count:history:view")
    @GetMapping()
    public String history()
    {
        return prefix + "/history";
    }

    /**
     * 查询更改历史数据列表
     */
    @RequiresPermissions("count:history:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WorkHistory workHistory)
    {
        startPage();
        List<WorkHistory> list = workHistoryService.selectWorkHistoryList(workHistory);
        return getDataTable(list);
    }

    /**
     * 导出更改历史数据列表
     */
    @RequiresPermissions("count:history:export")
    @Log(title = "更改历史数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WorkHistory workHistory)
    {
        List<WorkHistory> list = workHistoryService.selectWorkHistoryList(workHistory);
        ExcelUtil<WorkHistory> util = new ExcelUtil<WorkHistory>(WorkHistory.class);
        return util.exportExcel(list, "history");
    }

    /**
     * 新增更改历史数据
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存更改历史数据
     */
    @RequiresPermissions("count:history:add")
    @Log(title = "更改历史数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WorkHistory workHistory)
    {
        return toAjax(workHistoryService.insertWorkHistory(workHistory));
    }

    /**
     * 修改更改历史数据
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WorkHistory workHistory = workHistoryService.selectWorkHistoryById(id);
        mmap.put("workHistory", workHistory);
        return prefix + "/edit";
    }

    /**
     * 修改保存更改历史数据
     */
    @RequiresPermissions("count:history:edit")
    @Log(title = "更改历史数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WorkHistory workHistory)
    {

        return toAjax(workHistoryService.updateWorkHistory(workHistory));
    }

    /**
     * 删除更改历史数据
     */
    @RequiresPermissions("count:history:remove")
    @Log(title = "更改历史数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(workHistoryService.deleteWorkHistoryByIds(ids));
    }
}
