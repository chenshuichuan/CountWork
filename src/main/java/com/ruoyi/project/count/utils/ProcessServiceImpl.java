package com.ruoyi.project.count.utils;

import com.ruoyi.project.count.check.domain.Check;
import com.ruoyi.project.count.check.service.CheckRepository;
import com.ruoyi.project.count.check.service.ICheckService;
import com.ruoyi.project.count.result.domain.Result;
import com.ruoyi.project.count.result.service.IResultService;
import com.ruoyi.project.count.result.service.ResultRepository;
import com.ruoyi.project.count.work.domain.Work;
import com.ruoyi.project.count.work.service.IWorkService;
import com.ruoyi.project.count.work.service.WorkRepository;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class ProcessServiceImpl implements IProcessService {
    private static final Logger logger= LoggerFactory.getLogger(ProcessServiceImpl.class);
    @Autowired
    private ICheckService checkService;
    @Autowired
    private CheckRepository checkRepository;
    @Autowired
    private IWorkService workService;
    @Autowired
    private WorkRepository workRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IResultService resultService;
    @Autowired
    private ResultRepository resultRepository;

    @Override
    public Integer addAndSaveCheck(Work work, Integer status,String reviewer) {
        logger.debug("生成审核单：workId="+work.getId());
        Integer checked =0;
        String remark = "";
        Integer passed =0;
        Date dateTime = new Date();
        Check check = new Check(status,reviewer,work.getId(),checked,remark,passed,dateTime,dateTime);
        check = checkRepository.save(check);
        logger.debug("生成审核单：checkId="+check.getId());
        return check.getId();
    }

    /**
     * 审核
     * @param user 审核人员
     * @param check 审核人员已经审核过的审核单(审核单里标明是否通过本次审核)
     * @return 审核单
     */
    @Override
    public int dealCheck(User user, Check check) {
        check.setChecked(1);
        check.setUpdateTime(new Date());
        check = checkRepository.save(check);

        Work work = workService.selectWorkById(check.getWorkId());
        Integer nextStatus = getRealNextStatus(work,check);
        if(null == nextStatus)
            return 0;
        //审核结束
        else if(5==nextStatus){
            work.setStatus(nextStatus);
            work.setIsOk(check.getPassed());
            work.setUpdateTime(new Date());
            //计算工作量
            Result result = resultService.countResult(work);
            resultRepository.save(result);
        }
        //审核仍要继续，仍要生成下一status 的审核单
        else{
            work.setStatus(nextStatus);
            work.setNextStatus(nextStatus+1);
            work.setUpdateTime(new Date());

            //生成下一审核单
            work.setCheckId(addAndSaveCheck(work,work.getNextStatus(),getReviewer(work)));
        }
        workRepository.save(work);
        return 1;
    }
    /**根据当前审核单判断下一审核单的真正取值*/
    private Integer getRealNextStatus(Work work,Check check){
        //未审核的审核单无效，无法判断
        if(!check.checked()){
            logger.error("未审核的审核单无效，无法判断下一流程");
            return null;
        }
        if(check.passed()){
            return work.getNextStatus();
        }else{
            return 2;
        }
    }
    /**
     * 根据work中的nextStatus 获取对应的reviewer
     * */
    private String getReviewer(Work work){

        switch (work.getNextStatus()){
            case 2: return work.getReviewer();
            case 3:{
                //根据管理员初审单找到管理员
                Check firstCheck = checkRepository.findByStatusAndWorkId(1,work.getId()).get(0);
                return firstCheck.getReviewer();
            }
            case 4:{
                //根据work的academy+“审核员”找到学院管理员账号
                return work.getAcademy()+"审核员";
            }
            default:return work.getReviewer();
        }

    }
}
