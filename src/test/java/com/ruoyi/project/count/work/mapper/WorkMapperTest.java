package com.ruoyi.project.count.work.mapper;

import com.ruoyi.project.count.work.domain.Work;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Transient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkMapperTest {
    private static final Logger logger= LoggerFactory.getLogger(WorkMapperTest.class);

    @Autowired
    private WorkMapper workMapper;

    @Transient
    @Test
    public void deleteWorkByIds() throws Exception {
        String[] ids={"14","15"};
        workMapper.deleteWorkByIds(ids);
    }

    @Test
    public void selectWorkListByIds() throws Exception {
        Map<String,Object> map = new HashMap<>();
        Work work = new Work();
        work.setAcademy("计算机学院");
        work.setCourseCode("21921002");
        work.setCourseName("计算机网络B");
        String[] ids={"17","15"};
        map.put("courseCode",work.getCourseCode());
        map.put("courseName",work.getCourseName());
        map.put("array",ids);

        List<Work> workList = workMapper.selectWorkListByIds(map);
        logger.debug("size = "+workList.size());
    }

}