package com.zjf.demo.role.impl;

import com.zjf.demo.role.Role;
import com.zjf.demo.role.RoleDao;
import com.zjf.demo.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
