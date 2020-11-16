package com.tec.salles.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "`order`")
public class Order implements Serializable {

    @Id
    private String id;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date date;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany(mappedBy="id.order")
    private Set<OrderItem> items = new HashSet<>();

    public Order() {}

    public Order(String id, Date date, Customer customer) {
        this.id = id;
        this.date = date;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public Order setId(String id) {
        this.id = id;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Order setDate(Date date) {
        this.date = date;
        return this;
    }

    public Customer getOrder() {
        return customer;
    }

    public Order setOrder(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public Order setItems(Set<OrderItem> items) {
        this.items = items;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
