package com.zjf.demo.module;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleDao extends JpaRepository<Module,Integer> {

    List<Module> findAll();

}
