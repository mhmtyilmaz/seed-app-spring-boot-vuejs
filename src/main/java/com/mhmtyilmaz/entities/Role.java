package com.mhmtyilmaz.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue
    private Long id;
    String name;

    public Role(String name) {
        this.name = name;
    }
}
