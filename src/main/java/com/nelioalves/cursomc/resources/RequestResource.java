package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.domain.Request;
import com.nelioalves.cursomc.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/requests")
public class RequestResource {
    @Autowired
    private RequestService requestService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Request> find(@PathVariable Integer id) {
        return ResponseEntity.ok().body(requestService.findId(id));
    }
}
