package com.nelioalves.cursomc.domain;

import com.nelioalves.cursomc.domain.enums.StatePayment;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PaymentBillet extends Payment{
    private static final long serialVersionUID = 1L;

    private Date dueDate;
    private Date payday;

    public PaymentBillet(){}

    public PaymentBillet(Integer id, StatePayment state, Request request, Date dueDate, Date payday) {
        super(id, state, request);
        this.dueDate = dueDate;
        this.payday = payday;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPayday() {
        return payday;
    }

    public void setPayday(Date payday) {
        this.payday = payday;
    }
}
