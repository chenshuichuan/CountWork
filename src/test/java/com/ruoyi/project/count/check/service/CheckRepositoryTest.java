package com.ruoyi.project.count.check.service;

import com.ruoyi.project.count.check.domain.Check;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckRepositoryTest {
    private static final Logger logger= LoggerFactory.getLogger(CheckRepositoryTest.class);
    @Autowired
    private CheckRepository checkRepository;
    @Test
    public void findByReviewerAndAndWorkId() throws Exception {
        String reviewer = "网工系审核员";
        Integer workId =20;
        List<Check> checkList = checkRepository.findByReviewerAndWorkId(reviewer,workId);
        logger.debug("size ="+checkList.size());
    }

}