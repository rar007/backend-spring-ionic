package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.domain.Category;
import com.nelioalves.cursomc.dto.CategoryDTO;
import com.nelioalves.cursomc.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> find(@PathVariable Integer id) {
        return ResponseEntity.ok().body(categoryService.findId(id));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Category obj) {
        Category category = categoryService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody Category obj, @PathVariable Integer id) {
        obj.setId(id);
        categoryService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<CategoryDTO> findAll(){
        return categoryService.findAll();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
