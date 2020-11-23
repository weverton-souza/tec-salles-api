package com.tec.salles.service;

import com.tec.salles.entity.*;
import com.tec.salles.repository.UserAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

@Service
public class DBService {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final OrderItemService orderItemService;
    private final CustomerService customerService;
    private final OrderService orderService;
    private final UserAccessRepository userAccessRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DBService(CategoryService categoryService, ProductService productService,
                     OrderItemService orderItemService, CustomerService customerService, OrderService orderService, UserAccessRepository userAccessRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.orderItemService = orderItemService;
        this.customerService = customerService;
        this.orderService = orderService;
        this.userAccessRepository = userAccessRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Transient
    public void instantiateTestDatabase() throws ParseException {

        Category cat1 = new Category(null, "Informática", true);
        Category cat2 = new Category(null, "Escritório", true);
        Category cat3 = new Category(null, "Cama mesa e banho", true);
        Category cat4 = new Category(null, "Eletrônicos", true);
        Category cat5 = new Category(null, "Jardinagem", true);
        Category cat6 = new Category(null, "Decoração", true);
        Category cat7 = new Category(null, "Perfumaria", true);

        Product p1 = new Product(null, "Computador", 2000.09, "P001");
        Product p2 = new Product(null, "Impressora", 800.89, "P002");
        Product p3 = new Product(null, "Mouse", 80.50, "P003");
        Product p4 = new Product(null, "Mesa de escritório", 300.60, "P004");
        Product p5 = new Product(null, "Toalha", 50.00, "P005");
        Product p6 = new Product(null, "Colcha", 200.00, "P006");
        Product p7 = new Product(null, "TV true color", 1200.00, "P007");
        Product p8 = new Product(null, "Roçadeira", 800.00, "P008");
        Product p9 = new Product(null, "Abajour", 100.34, "P009");
        Product p10 = new Product(null, "Pendente", 180.99, "P010");
        Product p11 = new Product(null, "Shampoo", 90.35, "P011");

        p1.getCategories().addAll(Arrays.asList(cat1, cat4));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategories().addAll(Arrays.asList(cat1, cat4));
        p4.getCategories().addAll(Collections.singletonList(cat2));
        p5.getCategories().addAll(Collections.singletonList(cat3));
        p6.getCategories().addAll(Collections.singletonList(cat3));
        p7.getCategories().addAll(Collections.singletonList(cat4));
        p8.getCategories().addAll(Collections.singletonList(cat5));
        p9.getCategories().addAll(Collections.singletonList(cat6));
        p10.getCategories().addAll(Collections.singletonList(cat6));
        p11.getCategories().addAll(Collections.singletonList(cat7));

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2, p4));
        cat3.getProducts().addAll(Arrays.asList(p5, p6));
        cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProducts().addAll(Collections.singletonList(p8));
        cat6.getProducts().addAll(Arrays.asList(p9, p10));
        cat7.getProducts().addAll(Collections.singletonList(p11));

        Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7).forEach(this.categoryService::saveOrUpdate);
        Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11).forEach(this.productService::saveOrUpdate);


        Customer cli1 = new Customer(null, "Weverton Souza", "wevertonadm@gmail.com", "36378912377", "C001");
        Customer cli2 = new Customer(null, "Felipe Medeiros", "felipeps@gmail.com", "36378912377", "C002");
        this.customerService.saveOrUpdate(cli1);
        this.customerService.saveOrUpdate(cli2);

        UserAccess userAccess = new UserAccess(UUID.randomUUID().toString(), "wevertonad@gmail", "weverton.souza", bCryptPasswordEncoder.encode("123"), "ADMIN");
        this.userAccessRepository.save(userAccess);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Order ped1 = new Order(null, sdf.parse("30/09/2017"), cli1);
        Order ped2 = new Order(null, sdf.parse("10/10/2017"), cli1);

        Arrays.asList(ped1, ped2).forEach(this.orderService::saveOrUpdate);

        OrderItem ip1 = new OrderItem(p1, ped1, 0.00, 1, 2000.00);
        OrderItem ip2 = new OrderItem(p3, ped1, 0.00, 2, 80.00);
        OrderItem ip3 = new OrderItem(p2, ped2, 100.00, 1, 800.00);

        ped1.getItems().addAll(Arrays.asList(ip1, ip2));
        ped2.getItems().addAll(Collections.singletonList(ip3));

        p1.getItems().addAll(Collections.singletonList(ip1));
        p2.getItems().addAll(Collections.singletonList(ip3));
        p3.getItems().addAll(Collections.singletonList(ip2));

        Arrays.asList(ip1, ip2, ip3).forEach(this.orderItemService::saveOrUpdate);
    }

    public static String gRandomCode() {
        String letters = "ACEFGHJKLMNPQRUVWXY";
        String digits = "0123456789";

        SecureRandom random = new SecureRandom();

        StringBuilder sb = new StringBuilder(4);
            sb
                    .append(digits.charAt(random.nextInt(digits.length())))
                    .append(digits.charAt(random.nextInt(digits.length())))
                    .append(letters.charAt(random.nextInt(letters.length())))
                    .append(digits.charAt(random.nextInt(digits.length())));

        return sb.toString();

    }
}
