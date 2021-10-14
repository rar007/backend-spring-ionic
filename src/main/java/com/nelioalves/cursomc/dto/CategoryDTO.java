package com.nelioalves.cursomc.dto;

import com.nelioalves.cursomc.domain.Category;

public class CategoryDTO {

    private Integer id;
    private String name;

    public CategoryDTO(){}

    public CategoryDTO(Category obj) {
        id = obj.getId();
        name = obj.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
