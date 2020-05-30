package com.ruoyi.project.count.result.service;


import com.ruoyi.project.count.result.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author  ricardo
 * Description: repository类
 * Date: 2019/3/10
 */
public interface ResultRepository extends JpaRepository<Result,Long> {
}
