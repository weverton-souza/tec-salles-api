package com.tec.salles.service;

import com.tec.salles.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;

@Service
public class DBService {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final OrderItemService orderItemService;
    private final CustomerService customerService;
    private final OrderService orderService;

    @Autowired
    public DBService(CategoryService categoryService, ProductService productService,
                     OrderItemService orderItemService, CustomerService customerService, OrderService orderService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.orderItemService = orderItemService;
        this.customerService = customerService;
        this.orderService = orderService;
    }


    public void instantiateTestDatabase() throws ParseException {
        Category cat1 = new Category(null, "Informática", true);
        Category cat2 = new Category(null, "Escritório", true);
        Category cat3 = new Category(null, "Cama mesa e banho", true);
        Category cat4 = new Category(null, "Eletrônicos", true);
        Category cat5 = new Category(null, "Jardinagem", true);
        Category cat6 = new Category(null, "Decoração", true);
        Category cat7 = new Category(null, "Perfumaria", true);

        Product p1 = new Product(null, "Computador", 2000.09);
        Product p2 = new Product(null, "Impressora", 800.89);
        Product p3 = new Product(null, "Mouse", 80.50);
        Product p4 = new Product(null, "Mesa de escritório", 300.60);
        Product p5 = new Product(null, "Toalha", 50.00);
        Product p6 = new Product(null, "Colcha", 200.00);
        Product p7 = new Product(null, "TV true color", 1200.00);
        Product p8 = new Product(null, "Roçadeira", 800.00);
        Product p9 = new Product(null, "Abajour", 100.34);
        Product p10 = new Product(null, "Pendente", 180.99);
        Product p11 = new Product(null, "Shampoo", 90.35);

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

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2, p4));
        cat3.getProducts().addAll(Arrays.asList(p5, p6));
        cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProducts().addAll(Collections.singletonList(p8));
        cat6.getProducts().addAll(Arrays.asList(p9, p10));
        cat7.getProducts().addAll(Collections.singletonList(p11));

        Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7).forEach(this.categoryService::saveOrUpdate);
        Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11).forEach(this.productService::saveOrUpdate);


        Customer cli1 = new Customer(null, "Weverton Souza", "wevertonadm@gmail.com", "36378912377", "asd");
        this.customerService.saveOrUpdate(cli1);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Order ped1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1);
        Order ped2 = new Order(null, sdf.parse("10/10/2017 19:35"), cli1);

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
}
