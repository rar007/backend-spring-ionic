package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Category;
import com.nelioalves.cursomc.dto.CategoryDTO;
import com.nelioalves.cursomc.repositories.CategoryRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findId(Integer id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Object not found! Id: " + id + ", type: " + Category.class.getName()
                )
        );
    }

    public Category insert(Category obj) {
        return categoryRepository.save(obj);
    }

    public Category update(Category obj) {
        findId(obj.getId());
        return categoryRepository.save(obj);
    }

    public void delete(Integer id) {
        findId(id);
        try {
            categoryRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Nao é possivel excluir uma categoria que possui produtos");
        }
    }

    public List<CategoryDTO> findAll() {
        List<CategoryDTO> categoryDTO = categoryRepository.findAll().stream()
                .map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());
        return categoryDTO;
    }
}
