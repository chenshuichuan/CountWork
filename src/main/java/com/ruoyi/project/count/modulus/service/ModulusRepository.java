package com.ruoyi.project.count.modulus.service;


import com.ruoyi.project.count.modulus.domain.Modulus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author  ricardo
 * Description: repository类
 * Date: 2019/3/10
 */
public interface ModulusRepository extends JpaRepository<Modulus,Long> {
    Modulus findByName(String name);
}
