package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Address;
import com.nelioalves.cursomc.domain.City;
import com.nelioalves.cursomc.domain.Client;
import com.nelioalves.cursomc.domain.enums.TypeClient;
import com.nelioalves.cursomc.dto.ClientDTO;
import com.nelioalves.cursomc.dto.ClientNewDTO;
import com.nelioalves.cursomc.repositories.AddressRepository;
import com.nelioalves.cursomc.repositories.ClientRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Client findId(Integer id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Object not found! Id: " + id + ", type: " + Client.class.getName()
                )
        );
    }

    @Transactional
    public Client insert(Client client) {
        client.setId(null);
        client = clientRepository.save(client);
        addressRepository.saveAll(client.getAddresses());
        return client;
    }

    public Client update(Client client) {
        return clientRepository.save(updateClient(client));
    }

    public void delete(Integer id) {
        findId(id);
        try {
            clientRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException error) {
            throw new DataIntegrityException("Nao é possivel excluir uma cliente por " +
                    "ter mais de uma entidade associada");
        }
    }

    public List<ClientDTO> findAll() {
        List<ClientDTO> clientDTOList = clientRepository.findAll().stream()
                .map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
        return clientDTOList;
    }

    public Page<ClientDTO> findPage(Integer page, Integer linesPerLine, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerLine, Sort.Direction.valueOf(direction), orderBy);
        Page<ClientDTO> clientDTOPage = clientRepository.findAll(pageRequest).map(obj -> new ClientDTO(obj));
        return clientDTOPage;
    }

    public Client fromDto(ClientDTO clientDTO) {
        Client client = new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null);
        return client;
    }

    public Client fromDTO(ClientNewDTO clientDTO) {
        Client client = new Client(null, clientDTO.getName(),
                clientDTO.getEmail(), clientDTO.getCpfOrCnpj(), TypeClient.toEnum(clientDTO.getType()));
        City city = new City(clientDTO.getCityId(), null, null);
        Address address = new Address(null, clientDTO.getStreet(),
                clientDTO.getNumber(), clientDTO.getComplement(), clientDTO.getDistrict(),
                clientDTO.getZipCode(), client, city);
        client.getAddresses().add(address);
        client.getFone().add(clientDTO.getFone1());
        if(clientDTO.getFone2() != null) {
            client.getFone().add(clientDTO.getFone2());
        }
        if (clientDTO.getFone3() != null) {
            client.getFone().add(clientDTO.getFone3());
        }

        return client;
    }

    private Client updateClient(Client client) {
        Client newClient = findId(client.getId());

        newClient.setName(client.getName());
        newClient.setEmail(client.getEmail());
        return newClient;
    }
}
