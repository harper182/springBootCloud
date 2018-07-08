package com.harper.tw.entity;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by hbowang on 7/5/17.
 */
public class User {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;

    public User(){

    }
    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
