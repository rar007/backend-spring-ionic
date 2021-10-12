package com.nelioalves.cursomc.domain;

import com.nelioalves.cursomc.domain.enums.TypeClient;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String cpfOrCnpj;
    private Integer type;

    @ElementCollection
    @CollectionTable(name = "fone")
    private Set<String> fone = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private List<Address> addresses = new ArrayList<>();

    public Client(){}

    public Client(Integer id, String name, String email, String cpfOrCnpj, Integer type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOrCnpj = cpfOrCnpj;
        this.type = type;
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

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public TypeClient getType() {
        return TypeClient.toEnum(type);
    }

    public void setType(TypeClient type) {
        this.type = type.getCod();
    }

    public Set<String> getFone() {
        return fone;
    }

    public void setFone(Set<String> fone) {
        this.fone = fone;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id.equals(client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
