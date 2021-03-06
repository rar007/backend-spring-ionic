package com.nelioalves.cursomc;

import com.nelioalves.cursomc.domain.Address;
import com.nelioalves.cursomc.domain.Category;
import com.nelioalves.cursomc.domain.City;
import com.nelioalves.cursomc.domain.Client;
import com.nelioalves.cursomc.domain.OrderedItem;
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
import com.nelioalves.cursomc.repositories.OrderedItemRepository;
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

    @Autowired
    private OrderedItemRepository orderedItemRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursoMcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Informatica");
        Category cat2 = new Category(null, "Escritorio");
        Category cat3 = new Category(null, "Cama Mesa Banho");
        Category cat4 = new Category(null, "Eletronicos");
        Category cat5 = new Category(null, "Jardinagem");
        Category cat6 = new Category(null, "Decoracao");
        Category cat7 = new Category(null, "Perfumaria");

        Product p1 = new Product(null, "Computador", 2000.00);
        Product p2 = new Product(null, "Impressora", 800.00);
        Product p3 = new Product(null, "Mouse", 80.00);
        Product p4 = new Product(null, "Mesa de Escritorio", 300.00);
        Product p5 = new Product(null, "Toalha", 50.00);
        Product p6 = new Product(null, "Colca", 200.00);
        Product p7 = new Product(null, "TV true color", 1200.00);
        Product p8 = new Product(null, "Rocadeira", 800.00);
        Product p9 = new Product(null, "Abajour", 100.00);
        Product p10 = new Product(null, "Pendente", 180.00);
        Product p11 = new Product(null, "Shampoo", 90.00);

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2, p4));
        cat3.getProducts().addAll(Arrays.asList(p5, p6));
        cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProducts().addAll(Arrays.asList(p8));
        cat6.getProducts().addAll(Arrays.asList(p9, p10));
        cat7.getProducts().addAll(Arrays.asList(p11));

        p1.getCategories().addAll(Arrays.asList(cat1, cat4));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategories().addAll(Arrays.asList(cat1, cat4));
        p4.getCategories().addAll(Arrays.asList(cat2));
        p5.getCategories().addAll(Arrays.asList(cat3));
        p6.getCategories().addAll(Arrays.asList(cat3));
        p7.getCategories().addAll(Arrays.asList(cat4));
        p8.getCategories().addAll(Arrays.asList(cat5));
        p9.getCategories().addAll(Arrays.asList(cat6));
        p10.getCategories().addAll(Arrays.asList(cat6));
        p11.getCategories().addAll(Arrays.asList(cat7));


        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

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

        OrderedItem ordI1 = new OrderedItem(req1, p1, 0.00, 1, 2000.00);
        OrderedItem ordI2 = new OrderedItem(req1, p3, 0.00, 2, 80.00);
        OrderedItem ordI3 = new OrderedItem(req2, p2, 100.00, 1, 800.00);

        req1.getItems().addAll(Arrays.asList(ordI1, ordI2));
        req2.getItems().addAll(Arrays.asList(ordI3));

        p1.getItems().addAll(Arrays.asList(ordI1));
        p2.getItems().addAll(Arrays.asList(ordI3));
        p3.getItems().addAll(Arrays.asList(ordI2));

        orderedItemRepository.saveAll(Arrays.asList(ordI1, ordI2, ordI3));
    }
}
