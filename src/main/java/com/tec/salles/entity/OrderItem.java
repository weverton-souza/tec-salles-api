package com.tec.salles.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "`order_item`")
public class OrderItem implements Serializable {

    @JsonIgnore
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();
    private Double discount;
    private Integer amount;
    private Double price;

    public OrderItem() {}

    public OrderItem(Product product, Order order, Double discount, Integer amount, Double price) {
        id.setProduct(product);
        id.setOrder(order);
        this.discount = discount;
        this.amount = amount;
        this.price = price;
    }

    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        this.id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        this.id.setProduct(product);
    }

    public OrderItemPK getId() {
        return id;
    }

    public OrderItem setId(OrderItemPK id) {
        this.id = id;
        return this;
    }

    public Double getDiscount() {
        return discount;
    }

    public OrderItem setDiscount(Double discount) {
        this.discount = discount;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public OrderItem setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public OrderItem setPrice(Double price) {
        this.price = price;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(getId(), orderItem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
