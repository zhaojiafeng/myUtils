package com.zjf.demo.module;

import lombok.Data;

import javax.persistence.*;

/**
 * @author zhaojiafeng
 */
@Data
@Entity
@Table(name="admin")
public class Module {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String moduleName;

}
