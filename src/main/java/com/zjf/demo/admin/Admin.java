package com.zjf.demo.admin;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author zhaojiafeng
 */
@Data
@Entity
@Table(name="admin")
public class Admin {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String adminName;
    private String password;
    private Integer age;
    private Integer deleteFlag;
    private String telephone;
    private String email;

    public Admin() {
    }

    public Admin(String adminName, Integer age) {
        this.adminName = adminName;
        this.age = age;
    }

    public Admin(Integer id, String adminName, Integer age) {
        this.id = id ;
        this.adminName = adminName;
        this.age = age;
    }

    public Admin(Integer id, String adminName, String password, Integer deleteFlag, String telephone, String email) {
        this.id = id;
        this.adminName = adminName;
        this.password = password;
        this.deleteFlag = deleteFlag;
        this.telephone = telephone;
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Admin temp = (Admin) obj;
        return Objects.equals(id, temp.id) &&
                Objects.equals(adminName, temp.adminName) &&
                Objects.equals(password, temp.password) &&
                Objects.equals(age, temp.age) &&
                Objects.equals(deleteFlag, temp.deleteFlag) &&
                Objects.equals(telephone, temp.telephone) &&
                Objects.equals(email, temp.email) ;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adminName='" + adminName + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", deleteFlag=" + deleteFlag +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
