package com.ruoyi.project.count.check.service;


import com.ruoyi.project.count.check.domain.Check;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CheckRepository extends JpaRepository<Check,Integer> {
    List<Check> findByStatusAndWorkId(Integer status, Integer workId);
    List<Check> findByReviewerAndChecked(String reviewer,Integer checked);
}
