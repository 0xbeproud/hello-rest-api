package com.weproud.entities;

import lombok.Getter;

import javax.persistence.Entity;

/**
 * Logan. k
 */
@Getter
@Entity
public class User extends BaseEntity {

    private String name;
    private int age;
    private String email;

    public User(final String name, final int age, final String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
