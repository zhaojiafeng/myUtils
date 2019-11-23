package com.zjf.demo.admin;

import lombok.Data;

import javax.persistence.*;
import java.io.*;

/**
 * @author zhaojiafeng
 */
@Data
@Entity
@Table(name = "admin")
public class Admin implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String adminName;
    private String password;
    private Integer age;
    private Integer deleteFlag;
    private String telephone;
    private String email;

    /**
     * 浅复制
     * 原型模式
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * 深复制
     * 要实现深复制，需要采用流的形式读入当前对象的二进制输入，再写出二进制数据对应的对象。
     */
    protected Object deepClone() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Admin() {
        this.adminName = "test";
    }

    public Admin(Integer id, String adminName, Integer age) {
        this.id = id;
        this.adminName = adminName;
        this.age = age;
    }

    private Admin(AdminBuilder builder) {
        this.adminName = builder.adminName;
        this.password = builder.password;
        this.age = builder.age;
        this.deleteFlag = builder.deleteFlag;
        this.telephone = builder.telephone;
        this.email = builder.email;
    }

    @Data
    static class AdminBuilder {
        String adminName;
        String password;
        Integer age;
        Integer deleteFlag;
        String telephone;
        String email;
    }
}
