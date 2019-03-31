package com.zjf.demo.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminDao extends JpaRepository<Admin,Integer> {

    List<Admin> findAll();

}
