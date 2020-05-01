package com.ruoyi.project.count.check.service;


import com.ruoyi.project.count.check.domain.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CheckRepository extends JpaRepository<Check,Integer> {
    List<Check> findByStatusAndWorkId(Integer status, Integer workId);
    @Query(value="select * from cw_check where work_id=?1 order by id desc,update_time desc",nativeQuery=true)
    List<Check> findByWorkId(Integer workId);
    List<Check> findByReviewerAndChecked(String reviewer,Integer checked);
    List<Check> findByReviewer(String reviewer);
    List<Check> findByReviewerAndCheckedAndWorkId(String reviewer,Integer checked,Integer workId);

    @Query(value="select * from cw_check where reviewer=?1 and work_id=?2  order by update_time desc",nativeQuery=true)
    List<Check> findByReviewerAndWorkId(String reviewer,Integer workId);
}
