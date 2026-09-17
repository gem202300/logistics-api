package com.ivan.logisticsapi.dto;

public class WarehouseResponse {

    private Long id;
    private String name;
    private String address;
    private String city;
    private int capacity;

    public WarehouseResponse() {}

    public WarehouseResponse(Long id, String name, String address, String city, int capacity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
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