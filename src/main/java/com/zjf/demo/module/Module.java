package com.zjf.demo.module;

import javax.persistence.*;

@Entity
@Table(name="admin")
public class Module {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String moduleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
