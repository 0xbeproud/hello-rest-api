package com.weproud.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Logan. k
 */
@NoArgsConstructor
@Getter
@Entity
public class Hello extends BaseEntity{
    private static final long serialVersionUID = -4845414466340802378L;
    private String name;

    public Hello(final String name) {
        this.name = name;
    }
}
