package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Client;
import com.nelioalves.cursomc.repositories.ClientRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client findId(Integer id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Object not found! Id: " + id + ", type: " + Client.class.getName()
                )
        );
    }
}
