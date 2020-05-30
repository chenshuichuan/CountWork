package com.ruoyi.project.count.result.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.count.result.domain.Result;
import com.ruoyi.project.count.result.service.IResultService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 工作量Controller
 * 
 * @author beili
 * @date 2020-05-30
 */
@Controller
@RequestMapping("/count/result")
public class ResultController extends BaseController
{
    private String prefix = "count/result";

    @Autowired
    private IResultService resultService;

    @RequiresPermissions("count:result:view")
    @GetMapping()
    public String result()
    {
        return prefix + "/result";
    }

    /**
     * 查询工作量列表
     */
    @RequiresPermissions("count:result:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Result result)
    {
        startPage();
        if("admin".equals(ShiroUtils.getLoginName())){

        }
        else {
            result.setReviewer(ShiroUtils.getLoginName());
        }
        List<Result> list = resultService.selectResultList(result);
        return getDataTable(list);
    }

    /**
     * 导出工作量列表
     */
    @RequiresPermissions("count:result:export")
    @Log(title = "工作量", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Result result)
    {
        List<Result> list = resultService.selectResultList(result);
        ExcelUtil<Result> util = new ExcelUtil<Result>(Result.class);
        return util.exportExcel(list, "result");
    }

    /**
     * 新增工作量
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存工作量
     */
    @RequiresPermissions("count:result:add")
    @Log(title = "工作量", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Result result)
    {
        return toAjax(resultService.insertResult(result));
    }

    /**
     * 修改工作量
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Result result = resultService.selectResultById(id);
        mmap.put("result", result);
        return prefix + "/edit";
    }

    /**
     * 修改保存工作量
     */
    @RequiresPermissions("count:result:edit")
    @Log(title = "工作量", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Result result)
    {
        return toAjax(resultService.updateResult(result));
    }

    /**
     * 删除工作量
     */
    @RequiresPermissions("count:result:remove")
    @Log(title = "工作量", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(resultService.deleteResultByIds(ids));
    }
}
