package com.ruoyi.project.system.files.service;

import com.ruoyi.project.system.files.domain.Files;
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
public class FilesRepositoryTest {
    private static final Logger logger= LoggerFactory.getLogger(FilesRepositoryTest.class);
    @Autowired
    private FilesRepository filesRepository;
    @Test
    public void findBySuffix() throws Exception {
        List<Files> filesList = filesRepository.findBySuffix("xlsx");
        logger.debug("list.size = "+filesList.size());
    }

}