package com.nelioalves.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instance;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "request")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;

    @OneToMany(mappedBy = "id.request")
    private Set<OrderedItem> items = new HashSet<>();

    public Request(){}

    public Request(Integer id, Date instance, Client client, Address deliveryAddress) {
        this.id = id;
        this.instance = instance;
        this.client = client;
        this.deliveryAddress = deliveryAddress;
    }

    public Request(Integer id, Date instance, Payment payment, Client client, Address deliveryAddress) {
        this.id = id;
        this.instance = instance;
        this.payment = payment;
        this.client = client;
        this.deliveryAddress = deliveryAddress;
    }

    public double getTotalValue() {
        double sum = 0.0;
        for (OrderedItem orderedItem : items) {
            sum += orderedItem.getSubTotal();
        }
        return sum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstance() {
        return instance;
    }

    public void setInstance(Date date) {
        this.instance = date;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Set<OrderedItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderedItem> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return id.equals(request.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
