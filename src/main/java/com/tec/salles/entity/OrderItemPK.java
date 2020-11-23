package com.tec.salles.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Embeddable
public class OrderItemPK implements Serializable {

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="`order_id`")
    private Order order;

    public OrderItemPK() {}

    public OrderItemPK(Product product, Order order) {
        this.product = product;
        this.order = order;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPK that = (OrderItemPK) o;
        return getProduct().equals(that.getProduct()) &&
                getOrder().equals(that.getOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct(), getOrder());
    }
}
