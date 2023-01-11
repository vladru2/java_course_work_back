package ru.rsatu.hibernatetutorial.repository;

import ru.rsatu.hibernatetutorial.pojo.entity.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
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

    @Transactional
    public List<Product> deleteProduct(Long id) {
        Product product = entityManager.find(Product.class, id);
        if (product == null) {
            throw new NotFoundException();
        }
        entityManager.remove(product);
        entityManager.flush();
        return loadProducts();
    }
}
