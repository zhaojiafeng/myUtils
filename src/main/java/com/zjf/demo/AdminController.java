package com.zjf.demo;

import com.zjf.demo.admin.Admin;
import com.zjf.demo.admin.AdminService;
import com.zjf.demo.module.Module;
import com.zjf.demo.module.ModuleService;
import com.zjf.demo.role.Role;
import com.zjf.demo.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ModuleService moduleService;

    public List<Admin> findAll() {
        CompletableFuture<Integer> future1 = new CompletableFuture<>();
        new Thread(() -> {
            System.out.println("异步商品统计---->");
            Integer moduleCount;
            try {
                List<Module> moduleList = moduleService.findAll();
                moduleCount = moduleList.size();
            } catch (Exception e) {
                moduleCount = null;
            }
            // 告诉completableFuture任务已经完成
            future1.complete(moduleCount);
        }).start();

        CompletableFuture<Integer> future2 = new CompletableFuture<>();
        new Thread(() -> {
            System.out.println("异步商品统计---->");
            Integer moduleCount;
            try {
                List<Role> moduleList = roleService.findAll();
                moduleCount = moduleList.size();
            } catch (Exception e) {
                moduleCount = null;
            }
            // 告诉completableFuture任务已经完成
            future2.complete(moduleCount);
        }).start();

        return adminService.findAll();
    }



}
