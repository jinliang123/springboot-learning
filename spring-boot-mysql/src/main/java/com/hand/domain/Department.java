package com.hand.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jinliang on 2017/9/23.
 * 部门实体类
 */
@Entity
@Table(name = "department")
public class Department implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Department() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
