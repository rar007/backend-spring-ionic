package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Request;
import com.nelioalves.cursomc.repositories.RequestRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public Request findId(Integer id) {
        return requestRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Object not found! Id: " + id + ", type: " + Request.class.getName()
                )
        );
    }
}
