package com.ruoyi.project.count.history.service;


import com.ruoyi.project.count.history.domain.WorkHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author  ricardo
 * Description: repository类
 * Date: 2019/3/10
 */
public interface WorkHistoryRepository extends JpaRepository<WorkHistory,Integer> {
    List<WorkHistory> findByWorkId(Integer workId);
}
