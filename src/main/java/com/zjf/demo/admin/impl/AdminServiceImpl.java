package com.zjf.demo.admin.impl;

import com.zjf.demo.admin.Admin;
import com.zjf.demo.admin.AdminDao;
import com.zjf.demo.admin.AdminService;
import org.springframework.data.domain.*;
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

    /**
     * 判断 对象 在 数据库 中 是否存在
     *
     * @param adminId 对象的主键
     * @return 返回布尔值
     */
    @Override
    public boolean exists(Integer adminId) {
        boolean flag = adminDao.existsById(adminId);
        long count = adminDao.count();
        System.out.println(count);
        return flag;
    }

    /**
     * findOne();    em.finc(Class,id)         立即加载         2.0.5的已经变成了findById(id).get()来查询了
     * getOne();     em.getReference(Class,id) 延迟加载
     *
     * 细节：
     * 在使用延迟加载的时候，需要用到事务，如果没有事务，会报错
     * could not initialize proxy - no Session 异常
     *
     * 为了解决这个异常：我们手动在测试方法中加入@Transactional注解
     * 在以后真正的项目中，我们配置了声明式事务，这个注解就不用写了
     *
     * @param adminId admin 的 id
     * @return admin 对象
     */
    @Override
    public Admin findByAdminId(Integer adminId) {
        return adminDao.findById(adminId).orElse(new Admin());
    }

    /**
     * Sort：排序规则（asc | desc），排序的实体类的属性名称
     * Pageable：当前页，每页数量，排序对象（可以为null）
     *
     * @param pageIndex 页数 下标
     * @param pageSize  每页数量
     * @return 数据分页后的标准数据
     */
    @Override
    public Page<Admin> findByPage(Integer pageIndex, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable page = PageRequest.of(pageIndex, pageSize, sort);
        return adminDao.findAll(page);
    }

    @Override
    public List<Admin> findAll() {
        return adminDao.findAll();
    }

}
