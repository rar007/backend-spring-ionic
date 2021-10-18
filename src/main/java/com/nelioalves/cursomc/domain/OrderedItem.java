package com.nelioalves.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class OrderedItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private OrderedItemPK id = new OrderedItemPK();

    private Double discount;
    private Integer amount;
    private Double price;

    public OrderedItem() {
    }

    public OrderedItem(Request request, Product product, Double discount, Integer amount, Double price) {
        id.setRequest(request);
        id.setProduct(product);
        this.discount = discount;
        this.amount = amount;
        this.price = price;
    }

    public double getSubTotal() {
        return (price - discount) * amount;
    }

    @JsonIgnore
    public Request getRequest(){
        return id.getRequest();
    }

    public void setRequest(Request request) {
        id.setRequest(request);
    }

    public Product getProduct(){
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public OrderedItemPK getId() {
        return id;
    }

    public void setId(OrderedItemPK id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedItem that = (OrderedItem) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
