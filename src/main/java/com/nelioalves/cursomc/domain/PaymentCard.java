package com.nelioalves.cursomc.domain;

import com.nelioalves.cursomc.domain.enums.StatePayment;

import javax.persistence.Entity;

@Entity
public class PaymentCard extends Payment {
    private static final long serialVersionUID = 1L;

    private Integer numberOfInstallments;

    public PaymentCard(){}

    public PaymentCard(Integer id, StatePayment state, Request request, Integer numberOfInstallments) {
        super(id, state, request);
        this.numberOfInstallments = numberOfInstallments;
    }

    public Integer getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(Integer numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }
}
