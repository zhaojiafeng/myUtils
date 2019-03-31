package com.zjf.demo.admin.impl;

import com.zjf.demo.admin.Admin;
import com.zjf.demo.admin.AdminDao;
import com.zjf.demo.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    public List<Admin> findAll() {
        return adminDao.findAll();
    }

}
