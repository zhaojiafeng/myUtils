package com.zjf.demo.admin;

import java.util.List;

/**
 * @author zhaojiafeng
 */
public interface AdminService {

    /**
     * 查询 用户info
     * @return admin的list集合
     */
    List<Admin> findAll();

}
