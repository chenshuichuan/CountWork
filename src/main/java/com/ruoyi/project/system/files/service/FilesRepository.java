package com.ruoyi.project.system.files.service;


import com.ruoyi.project.system.files.domain.Files;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author  ricardo
 * Description: repository类
 * Date: 2019/3/10
 */
public interface FilesRepository extends JpaRepository<Files,Integer> {
    List<Files> findByCreateByName(String name);
}
