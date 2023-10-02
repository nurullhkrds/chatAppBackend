package com.nurullah.questapp.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;

    @Column(name="avatar")
    private int avatar;

}
