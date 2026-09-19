package com.ivan.logisticsapi.model;

import jakarta.persistence.*;

@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private int capacity;

    public Warehouse(String name, String address, String city, int capacity) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.capacity = capacity;
    }

    public Warehouse() {}

    public Long getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public int getCapacity() {
        return capacity;
    }
}
