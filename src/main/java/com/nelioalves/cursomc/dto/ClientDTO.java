package com.nelioalves.cursomc.dto;

import com.nelioalves.cursomc.domain.Client;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull
    @NotBlank
    @Length(min = 5, max = 120, message = "O nome deve conter 5 e no maximo 120 caracteres")
    private String name;

    @Email(message = "informe um email valido")
    private String email;

    public ClientDTO(){}

    public ClientDTO(Client client){
        id = client.getId();
        name = client.getName();
        email = client.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
