package com.zjf.demo.role;

import lombok.Data;

import javax.persistence.*;

/**
 * @author zhaojiafeng
 */
@Data
@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String roleName;

}
