package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.domain.Category;
import com.nelioalves.cursomc.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> find(@PathVariable Integer id) {
        return ResponseEntity.ok().body(categoryService.findId(id));
    }
}
