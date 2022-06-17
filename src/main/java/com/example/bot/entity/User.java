package com.example.bot.entity;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "t_user")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    @Size(min=2, message = "Не меньше 2 знаков")
    @Getter @Setter
    private String username;
    @Size(min=2, message = "Не меньше 2 знаков")
    @Getter @Setter
    private String password;
    @Transient
    @Getter @Setter
    private String passwordConfirm;
    @ManyToMany(fetch = FetchType.EAGER)
    @Getter @Setter
    private Set<Role> roles;

    public User() {
    }



}