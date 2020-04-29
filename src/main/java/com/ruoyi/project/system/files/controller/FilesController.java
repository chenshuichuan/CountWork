package com.ruoyi.project.system.files.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.files.domain.Files;
import com.ruoyi.project.system.files.service.FilesRepository;
import com.ruoyi.project.system.files.service.IFilesService;
import com.ruoyi.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

/**
 * 文件上传Controller
 * 
 * @author beili
 * @date 2020-04-27
 */
@Controller
@RequestMapping("/system/files")
public class FilesController extends BaseController
{
    private String prefix = "system/files";
    private static final Logger log = LoggerFactory.getLogger(FilesController.class);
    private static String Save_Url = FileUploadUtils.getDefaultBaseDir();
    @Autowired
    private IFilesService filesService;
    @Autowired
    private FilesRepository filesRepository;

    @RequiresPermissions("system:files:view")
    @GetMapping()
    public String files()
    {
        return prefix + "/files";
    }

    /**
     * 查询文件上传列表
     */
    @RequiresPermissions("system:files:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Files files)
    {
        startPage();
        List<Files> list = filesService.selectFilesList(files);
        return getDataTable(list);
    }

    /**
     * 导出文件上传列表
     */
    @RequiresPermissions("system:files:export")
    @Log(title = "文件上传", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Files files)
    {
        List<Files> list = filesService.selectFilesList(files);
        ExcelUtil<Files> util = new ExcelUtil<Files>(Files.class);
        return util.exportExcel(list, "files");
    }

    /**
     * 新增文件上传
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        Files files = new Files();
        mmap.put("files", files);
        return prefix + "/add";
    }
    private Files dealFile(MultipartFile file, Files files) throws Exception {
        if (file == null) return files;

        String suffix = FileUploadUtils.dealName(file.getOriginalFilename());
        if (StringUtils.isEmpty(suffix)) throw new Exception();

        String name = Save_Url + files.getFileName() + "." + suffix;
        files.setUrl(name);
        files.setSuffix(suffix);

        return files;
    }
    /**
     * 新增保存文件上传
     */
    @RequiresPermissions("system:files:add")
    @Log(title = "文件上传", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MultipartFile file, Files files)
    {
        int rtn = 0;
        Boolean isFile = false;
        try {
            files = dealFile(file, files);
            if (files.getUpdateFlag() == 1 && files.getId() > 0) {//修改
                if (file != null) {
                    isFile = true;
                }
                rtn = filesService.updateFiles(files, isFile);
            } else {//新增
                rtn = filesService.insertFiles(files);
            }
            if (file != null) {
                File desc = FileUploadUtils.getAbsoluteFile(Save_Url, files.getUrl());
                file.transferTo(desc);

                User user = ShiroUtils.getSysUser();
                log.debug("当前用户："+user.getLoginName());
                log.debug(user.getDept().toString());
                filesService.readXlsFile(files,user);
            }
        } catch (Exception e) {
            log.error("保存失败，请检查后重试！", e);
            return error(e.getMessage());
        }
        return toAjax(rtn);
    }

    /**
     * 修改文件上传
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        Files files = filesService.selectFilesById(id);
        mmap.put("files", files);
        return prefix + "/edit";
    }

    /**
     * 修改保存文件上传
     */
    @RequiresPermissions("system:files:edit")
    @Log(title = "文件上传", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Files files)
    {
        return toAjax(filesService.updateFiles(files, false));
    }

    /**
     * 删除文件上传
     */
    @RequiresPermissions("system:files:remove")
    @Log(title = "文件上传", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(filesService.deleteFilesByIds(ids));
    }

    /**
     * 检验文件名是否唯一
     *
     * @param files
     * @return
     */
    @PostMapping("/checkFileNameUnqiue")
    @ResponseBody
    public String checkFileNameUnqiue(Files files) {
        return filesService.checkFileNameUnqiue(files);
    }

    /**
     * @Author haien
     * @Description 前端请求下载Excel表格，返回一个输出流
     * @Date 11:34 2018/7/22
     * @Param [request, response]
     * @return void
     **/
    @RequestMapping(value = "/getFileById")
    @Log(title = "文件下载", businessType = BusinessType.OTHER)
    @ResponseBody
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //获得请求文件名
        String encoding = System.getProperty("file.encoding");
        String idStr = request.getParameter("id");
        if(idStr==null||idStr.isEmpty()){
            System.out.println("file id not find!");
            return;
        }
        int id = Integer.parseInt(idStr);
        Files file =  filesService.selectFilesById(id);
        if(file!=null&&file.getUrl()!=null){
            //设置Content-Disposition
            String enFileName = URLEncoder.encode(file.getFileName()+"."+file.getSuffix(),"utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+enFileName);
            //读取目标文件，通过response将目标文件写到客户端
            //读取文件
            String fileName = new String(file.getUrl().getBytes("UTF-8"),encoding);
            InputStream in = new FileInputStream(fileName);
            OutputStream out = response.getOutputStream();
            //写文件
            int b;
            while((b=in.read())!= -1) {
                out.write(b);
            }
            in.close();
            out.close();
        }

    }
    /**
     * 根据选择的文件生成计划(仅xls计划文件)
     * 默认计划文件为xls后缀的文件
     */
    @RequiresPermissions("file:add")
    @PostMapping("/readXlsFile")
    @Log(title = "生成计划", businessType = BusinessType.OTHER)
    @ResponseBody
    public AjaxResult readXlsFile(Integer id) {
        Optional<Files> optionalFiles = filesRepository.findById(id);
        if(optionalFiles.isPresent()){
            Files files = optionalFiles.get();
            User user = ShiroUtils.getSysUser();

            log.debug("当前用户："+user.getLoginName());
            log.debug(user.getDept().toString());
            return filesService.readXlsFile(files,user);
        }
        return AjaxResult.error("未找到要解析的xls文件记录！");
    }
}
