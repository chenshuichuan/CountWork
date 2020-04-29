package com.ruoyi.project.system.files.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.count.utils.IProcessService;
import com.ruoyi.project.count.utils.ReadExcel;
import com.ruoyi.project.count.work.domain.Work;
import com.ruoyi.project.count.work.service.WorkRepository;
import com.ruoyi.project.system.dept.service.IDeptService;
import com.ruoyi.project.system.files.domain.Files;
import com.ruoyi.project.system.files.mapper.FilesMapper;
import com.ruoyi.project.system.files.service.FilesRepository;
import com.ruoyi.project.system.files.service.IFilesService;
import com.ruoyi.project.system.user.domain.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 文件上传Service业务层处理
 * 
 * @author beili
 * @date 2020-04-27
 */
@Service
public class FilesServiceImpl implements IFilesService {
    @Autowired
    private FilesMapper filesMapper;
    @Autowired
    private FilesRepository filesRepository;
    @Autowired
    private WorkRepository workRepository;
    @Autowired
    private IDeptService iDeptService;
    @Autowired
    private IProcessService processService;
    /**
     * 查询文件上传信息
     *
     * @param id 文件上传ID
     * @return 文件上传信息
     */
    @Override
    public Files selectFilesById(Integer id) {
        return filesMapper.selectFilesById(id);
    }

    /**
     * 查询文件上传列表
     *
     * @param files 文件上传信息
     * @return 文件上传集合
     */
    @Override
    public List<Files> selectFilesList(Files files) {
        if("admin".equals(ShiroUtils.getLoginName())){
        }
        else{
            files.setCreateByName(ShiroUtils.getLoginName());
        }
        return filesMapper.selectFilesList(files);
    }

    /**
     * 新增文件上传
     *
     * @param files 文件上传信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertFiles(Files files) {
        files.setCreateBy(ShiroUtils.getSysUser().getUserName());
        files.setCreateByName(ShiroUtils.getLoginName());
        return filesMapper.insertFiles(files);
    }

    /**
     * 修改文件上传
     *
     * @param files 文件上传信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateFiles(Files files, Boolean isFile) {
        int rtn = 0;
        //修改新数据
        rtn = filesMapper.updateFiles(files);
        if (rtn > 0) {
            //先判断有没有文件
            Files oldFiles = filesMapper.selectFilesById(files.getId());
            File file = new File(oldFiles.getUrl());
            if (file.isFile()) {
                if (files != null && isFile) {
                    file.delete();
                } else {
                    String url = FileUploadUtils.getDefaultBaseDir() + files.getFileName() + "." + oldFiles.getSuffix();
                    file.renameTo(new File(url ));
                }
            }
        }

        return rtn;
    }

    /**
     * 删除文件上传对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFilesByIds(String ids) {
        int rtn = 0;
        if (ids.length() > 0) {
            String[] id = ids.split(",");
            for (int i = 0; i < id.length; i++) {
                Files files = filesMapper.selectFilesById(Integer.valueOf(id[i]));
                if (files != null) {
                    rtn = filesMapper.deleteFilesByIds(Convert.toStrArray(ids));
                    //删除成功后要移除文件
                    if (rtn > 0) {
                        File file = new File(files.getUrl());
                        if (file.isFile()) {
                            file.delete();
                        }
                    }
                }
            }
        }
        return rtn;
    }

    @Override
    public String checkFileNameUnqiue(Files files) {
        List<Files> filesList = filesMapper.selectFilesList(files);
        if (filesList != null && filesList.size() > 0) {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }

    @Override
    public List<Files> selectFilesListNoSave(Files files) {
        List<Files> list = filesMapper.selectFilesListNoSave(files);

        return list;
    }
    private Work findByOther(Work work){
         /** 通过“学期term+学院academy+grade+courseCode+className”作为唯一数据判断，
          * 进而覆盖更新数据，防止重复解析
          */
        return workRepository.findByTermAndAcademyAndGradeAndCourseCodeAndClassName(
                work.getTerm(),work.getAcademy(),work.getGrade(),work.getCourseCode(),work.getClassName());
    }

    @Override
    public AjaxResult readXlsFile(Files files, User user) {
        String academy =iDeptService.selectDeptById(user.getDept().getParentId()).getDeptName();
        try {
            List<Work> workList = ReadExcel.readExcel(files,academy);
            System.out.println("list size="+workList.size());
            for (Work work: workList) {
                Work sqlData = findByOther(work);
                //如果已经存在，则覆盖原mysql中的数据进行更新
                if (sqlData!=null){
                    work.setId(sqlData.getId());
                }
                Work temp = workRepository.save(work);
                //生成“待管理员初审的审核单
                temp.setCheckId(processService.addAndSaveCheck(temp,temp.getNextStatus(),user.getLoginName()));
                workRepository.save(temp);
            }
            return AjaxResult.success("导入文件成功！");
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        return AjaxResult.error("导入文件失败！");
    }

}
