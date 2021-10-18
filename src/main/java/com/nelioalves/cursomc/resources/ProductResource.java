package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.domain.Product;
import com.nelioalves.cursomc.dto.ProductDTO;
import com.nelioalves.cursomc.resources.utils.URL;
import com.nelioalves.cursomc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> find(@PathVariable Integer id) {
        return ResponseEntity.ok().body(productService.findId(id));
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categories", defaultValue = "") String categories,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        name = URL.decodeParam(name);
        List<Integer> ids = URL.decoderIntLis(categories);
        Page<Product> list = productService.search(name, ids, page, linesPerPage, orderBy, direction);
        Page<ProductDTO> listDTO = list.map(ProductDTO::new);
        return ResponseEntity.ok().body(listDTO);

    }
}
