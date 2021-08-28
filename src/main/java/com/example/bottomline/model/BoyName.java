package com.example.bottomline.model;

import javax.persistence.*;

@Entity
@Table
public class BoyName {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final int id;
    @Column(nullable = false)
    private final String name;


    public BoyName(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
