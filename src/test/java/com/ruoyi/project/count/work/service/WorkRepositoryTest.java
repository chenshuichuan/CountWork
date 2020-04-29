package com.ruoyi.project.count.work.service;

import com.ruoyi.project.count.work.domain.Work;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkRepositoryTest {
    private static final Logger logger= LoggerFactory.getLogger(WorkRepositoryTest.class);
    @Autowired
    private WorkRepository workRepository;
    @Test
    public void findByTermAndAcademyAndGradeAndCourseCodeAndClassName() throws Exception {
        Work data = new Work(new Date(),"1111","1111");
        data.setGrade("111");
        data.setCourseCode("111");
        data.setClassName("11");
        Work work = workRepository.findByTermAndAcademyAndGradeAndCourseCodeAndClassName(
                data.getTerm(),data.getAcademy(),data.getGrade(),data.getCourseCode(),data.getClassName());
        if(null!= work){
            logger.debug("isOk="+work.getIsOk());
        }
    }

}