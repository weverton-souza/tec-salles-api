package com.tec.salles.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    private String id;

    private String name;

    private String code;

    private String email;

    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy="customer")
    private List<Order> orders = new ArrayList<>();

    public Customer() {}

    public Customer(String id, String name, String email, String phone, String code) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public Customer setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public String getCode() {
        return code;
    }

    public Customer setCode(String code) {
        this.code = code;
        return this;
    }

    public Customer setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Customer setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
