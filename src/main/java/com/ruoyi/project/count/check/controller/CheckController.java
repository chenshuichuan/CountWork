package com.ruoyi.project.count.check.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.count.check.domain.Check;
import com.ruoyi.project.count.check.service.ICheckService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 审核单Controller
 * 
 * @author beili
 * @date 2020-04-29
 */
@Controller
@RequestMapping("/count/check")
public class CheckController extends BaseController
{
    private String prefix = "count/check";

    @Autowired
    private ICheckService checkService;

    @RequiresPermissions("count:check:view")
    @GetMapping()
    public String check()
    {
        return prefix + "/check";
    }

    /**
     * 查询审核单列表
     */
    @RequiresPermissions("count:check:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Check check)
    {
        startPage();
        if("admin".equals(ShiroUtils.getLoginName())){
        }
        else{
            check.setReviewer(ShiroUtils.getLoginName());
        }
        List<Check> list = checkService.selectCheckList(check);
        return getDataTable(list);
    }

    /**
     * 导出审核单列表
     */
    @RequiresPermissions("count:check:export")
    @Log(title = "审核单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Check check)
    {
        List<Check> list = checkService.selectCheckList(check);
        ExcelUtil<Check> util = new ExcelUtil<Check>(Check.class);
        return util.exportExcel(list, "check");
    }

    /**
     * 新增审核单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存审核单
     */
    @RequiresPermissions("count:check:add")
    @Log(title = "审核单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Check check)
    {
        return toAjax(checkService.insertCheck(check));
    }

    /**
     * 修改审核单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        Check check = checkService.selectCheckById(id);
        mmap.put("check", check);
        return prefix + "/edit";
    }

    /**
     * 修改保存审核单
     */
    @RequiresPermissions("count:check:edit")
    @Log(title = "审核单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Check check)
    {
        return toAjax(checkService.updateCheck(check));
    }

    /**
     * 删除审核单
     */
    @RequiresPermissions("count:check:remove")
    @Log(title = "审核单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(checkService.deleteCheckByIds(ids));
    }
}
