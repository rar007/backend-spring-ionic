package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.OrderedItem;
import com.nelioalves.cursomc.domain.PaymentBillet;
import com.nelioalves.cursomc.domain.Request;
import com.nelioalves.cursomc.domain.enums.StatePayment;
import com.nelioalves.cursomc.repositories.OrderedItemRepository;
import com.nelioalves.cursomc.repositories.PaymentRepository;
import com.nelioalves.cursomc.repositories.RequestRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private BilletService billetService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderedItemRepository orderedItemRepository;

    public Request findId(Integer id) {
        return requestRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Object not found! Id: " + id + ", type: " + Request.class.getName()
                )
        );
    }

    public Request insert(Request request) {
        request.setId(null);
        request.setInstance(new Date());

        request.getPayment().setState(StatePayment.SETTLED);
        request.getPayment().setRequest(request);
        if (request.getPayment() instanceof PaymentBillet) {
            PaymentBillet paymentBillet = (PaymentBillet) request.getPayment();
            billetService.paymentBillet(paymentBillet, request.getInstance());
        }
        request = requestRepository.save(request);
        paymentRepository.save(request.getPayment());
        for (OrderedItem orderedItem : request.getItems()) {
            orderedItem.setDiscount(0.0);
            orderedItem.setPrice(productService.findId(orderedItem.getProduct().getId()).getPrice());
            orderedItem.setRequest(request);
        }
        orderedItemRepository.saveAll(request.getItems());
        return request;
    }
}
