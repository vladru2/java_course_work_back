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
        Product product2 = entityManager.find(Product.class, product.getId());
        if (product2 == null) {
            if (product.getId() != null) {
                entityManager.merge(product);
            } else {
                entityManager.persist(product);
            }
            product2 = product;
            entityManager.flush();
        } else {
            product2.setName(product.getName());
            product2.setCity(product.getCity());
            entityManager.merge(product2);
            entityManager.flush();
        }
        return product2;
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
