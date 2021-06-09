package com.example.a17026871buiquocthanh;

public class User {
    private String id;
    private String name;
    private String diaChi;


    public User(String id, String name, String diaChi) {
        this.id = id;
        this.name = name;
        this.diaChi = diaChi;
    }

    public User(String name, String diaChi) {
        this.name = name;
        this.diaChi = diaChi;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", diaChi='" + diaChi + '\'' +
                '}';
    }
}
