package com.grafik.grafikManager;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;


    public Long getId(){return id;}
    public String getName(){return name;}


    public void setId(Long id){this.id = id;}
    public void setName(String name){this.name = name;}
}
