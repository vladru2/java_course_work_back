package ru.rsatu.hibernatetutorial.repository;

import ru.rsatu.hibernatetutorial.pojo.entity.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProductsRepository {

    @Inject
    EntityManager entityManager;

    public List<Product> loadProducts() {
        return entityManager.createQuery("select b from Product b", Product.class)
                .getResultList();
    }

    @Transactional
    public Product saveProduct(Product product) {
        if (product.getId() != null) {
            entityManager.merge(product);
        } else {
            entityManager.persist(product);
        }
        entityManager.flush();
        return product;
    }

}
