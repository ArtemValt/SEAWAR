package com.example.bot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_role")
public class Role {
    @Id
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    @Getter
    @Setter
    private Set<User> users;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }


}