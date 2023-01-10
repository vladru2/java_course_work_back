package ru.rsatu.hibernatetutorial.service;

import ru.rsatu.hibernatetutorial.pojo.entity.Product;
import ru.rsatu.hibernatetutorial.repository.ProductsRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductsRepository productsRepository;

    public List<Product> loadProductList() {
        return productsRepository.loadProducts();
    }

    public Product saveProduct(Product product) {
        return productsRepository.saveProduct(product);
    }
}
