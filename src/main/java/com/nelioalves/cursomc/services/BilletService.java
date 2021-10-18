package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.PaymentBillet;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BilletService {

    public void paymentBillet(PaymentBillet paymentBillet, Date instanceRequest) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instanceRequest);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        paymentBillet.setDueDate(calendar.getTime());
    }
}
