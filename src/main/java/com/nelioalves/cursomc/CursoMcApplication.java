package com.nelioalves.cursomc;

import com.nelioalves.cursomc.domain.Address;
import com.nelioalves.cursomc.domain.Category;
import com.nelioalves.cursomc.domain.City;
import com.nelioalves.cursomc.domain.Client;
import com.nelioalves.cursomc.domain.Payment;
import com.nelioalves.cursomc.domain.PaymentBillet;
import com.nelioalves.cursomc.domain.PaymentCard;
import com.nelioalves.cursomc.domain.Product;
import com.nelioalves.cursomc.domain.Request;
import com.nelioalves.cursomc.domain.State;
import com.nelioalves.cursomc.domain.enums.StatePayment;
import com.nelioalves.cursomc.domain.enums.TypeClient;
import com.nelioalves.cursomc.repositories.AddressRepository;
import com.nelioalves.cursomc.repositories.CategoryRepository;
import com.nelioalves.cursomc.repositories.CityRepository;
import com.nelioalves.cursomc.repositories.ClientRepository;
import com.nelioalves.cursomc.repositories.PaymentRepository;
import com.nelioalves.cursomc.repositories.ProductRepository;
import com.nelioalves.cursomc.repositories.RequestRepository;
import com.nelioalves.cursomc.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RequestRepository requestRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursoMcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Informatica");
        Category cat2 = new Category(null, "Escritorio");

        Product p1 = new Product(null, "Computador", 2000.00);
        Product p2 = new Product(null, "Impressora", 800.00);
        Product p3 = new Product(null, "Mouse", 80.00);

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2));

        p1.getCategories().addAll(Arrays.asList(cat1));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2));
        p3.getCategories().addAll(Arrays.asList(cat1));

        categoryRepository.saveAll(Arrays.asList(cat1, cat2));
        productRepository.saveAll(Arrays.asList(p1, p2, p3));

        State sta1 = new State(null, "Minas Gerais");
        State sta2 = new State(null, "Sao Paulo");

        City city1 = new City(null, "Uberlandia", sta1);
        City city2 = new City(null, "Sao Paulo", sta2);
        City city3 = new City(null, "Campinas", sta2);

        sta1.getCities().addAll(Arrays.asList(city1));
        sta2.getCities().addAll(Arrays.asList(city2, city3));

        stateRepository.saveAll(Arrays.asList(sta1,sta2));
        cityRepository.saveAll(Arrays.asList(city1, city2, city3));

        Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", TypeClient.PHYSICALPERSON);
        Address a1 = new Address(null, "Rua Flores", "300", "Apt 203", "Jardim", "38220834", cli1, city1);
        Address a2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, city2);

        cli1.getFone().addAll(Arrays.asList("27363323", "93838393"));
        clientRepository.saveAll(Arrays.asList(cli1));
        addressRepository.saveAll(Arrays.asList(a1, a2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Request req1 = new Request(null, sdf.parse("30/09/2017 10:32"), cli1, a1);
        Request req2 = new Request(null, sdf.parse("10/10/2017 19:35"), cli1, a2);

        Payment pay1 = new PaymentCard(null, StatePayment.SETTLED, req1, 6);
        req1.setPayment(pay1);
        Payment pay2 = new PaymentBillet(null, StatePayment.PENDING, req2, sdf.parse("20/10/2017 00:00"), null);
        req2.setPayment(pay2);

        requestRepository.saveAll(Arrays.asList(req1, req2));
        paymentRepository.saveAll(Arrays.asList(pay1, pay2));
    }
}
