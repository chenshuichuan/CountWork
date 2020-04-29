package com.ruoyi.project.count.work.service;


import com.ruoyi.project.count.work.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author  ricardo
 * Description: repository类
 * Date: 2019/3/10
 */
public interface WorkRepository extends JpaRepository<Work,Integer> {
    Work findByTermAndAcademyAndGradeAndCourseCodeAndClassName
            (String term, String academy, String Grade, String courseCode, String className);
}
