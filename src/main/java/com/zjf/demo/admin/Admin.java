package com.zjf.demo.admin;

public class Admin {

    private Integer id;
    private String adminName;
    private String password;
    private Integer deleteFlag;
    private String telephone;
    private String email;

    public Admin() {
    }

    public Admin(Integer id, String adminName, String password, Integer deleteFlag, String telephone, String email) {
        this.id = id;
        this.adminName = adminName;
        this.password = password;
        this.deleteFlag = deleteFlag;
        this.telephone = telephone;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
