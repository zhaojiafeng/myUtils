package com.zjf.demo.admin;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author zhaojiafeng
 */
public interface AdminService {

    /**
     * 判断是否存在
     *
     * @param adminId admin 的id
     * @return 返回boolean
     */
    boolean exists(Integer adminId);

    /**
     * 根据adminId查询
     *
     * @param adminId admin的Id
     * @return 返回查询结果
     */
    Admin findByAdminId(Integer adminId);

    /**
     * 分页查询
     *
     * @param pageIndex 页数
     * @param pageSize  每页数量
     * @return 返回结果
     */
    Page<Admin> findByPage(Integer pageIndex, Integer pageSize);

    /**
     * 查询 用户info
     *
     * @return admin的list集合
     */
    List<Admin> findAll();

}
