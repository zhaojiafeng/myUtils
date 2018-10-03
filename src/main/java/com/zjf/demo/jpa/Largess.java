package com.zjf.demo.jpa;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "largess")
public class Largess implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //1.sql语句中的字段和表名都应该和数据库相应,而不是类中的字段,
    //若带有参数如la.id= id,这个=id才是类中属性
    //2.操作字段一定要用别名
    @Formula("select max(la.id) from largess as la")
    private int maxId;
    @Formula("select COUNT(la.id) from largess la")
    private int count;
    @Transient
    private String img;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}