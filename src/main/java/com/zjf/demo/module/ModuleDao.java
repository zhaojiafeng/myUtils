package com.zjf.demo.module;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhaojiafeng
 */
public interface ModuleDao extends JpaRepository<Module,Integer> {

    /**
     * 查询所有权限
     */
    @Override
    List<Module> findAll();

}
