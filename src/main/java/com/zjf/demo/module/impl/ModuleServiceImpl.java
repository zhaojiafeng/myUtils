package com.zjf.demo.module.impl;

import com.zjf.demo.module.Module;
import com.zjf.demo.module.ModuleDao;
import com.zjf.demo.module.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaojiafeng
 */
@Service
public class ModuleServiceImpl implements ModuleService {

    private final ModuleDao moduleDao;

    public ModuleServiceImpl(ModuleDao moduleDao) {
        this.moduleDao = moduleDao;
    }

    @Override
    public List<Module> findAll() {
        return moduleDao.findAll();
    }
}
