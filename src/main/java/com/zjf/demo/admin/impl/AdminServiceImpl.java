package com.zjf.demo.admin.impl;

import com.zjf.demo.admin.Admin;
import com.zjf.demo.admin.AdminDao;
import com.zjf.demo.admin.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaojiafeng
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminDao adminDao;

    public AdminServiceImpl(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public List<Admin> findAll() {
        return adminDao.findAll();
    }

}
