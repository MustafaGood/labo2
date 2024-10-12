package com.mustafa.warehouseapi1.service;

import com.mustafa.warehouseapi1.model.Product;
import com.mustafa.warehouseapi1.repository.ProductRepository;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class ProductService {

    @Inject
    private ProductRepository productRepository;

    public Product getProduct(Long id) {
        return productRepository.find(id);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Long id, Product product) {
        productRepository.update(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.find(id);
        if (product != null) {
            productRepository.delete(product);
        }
    }
}
