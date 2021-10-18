package com.nelioalves.cursomc.services.validation;
import com.nelioalves.cursomc.domain.Client;
import com.nelioalves.cursomc.dto.ClientDTO;
import com.nelioalves.cursomc.repositories.ClientRepository;
import com.nelioalves.cursomc.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private HttpServletRequest request;

    Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

    Integer uriId = Integer.parseInt(map.get("id"));

    @Override
    public void initialize(ClientUpdate ann) { // Empty for necessity
    }

    @Override
    public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        Client aux = clientRepository.findByEmail(objDto.getEmail());
        if (aux != null && !aux.getId().equals(uriId)) {
            list.add(new FieldMessage("email", "E-mail já existente"));
        }


        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
