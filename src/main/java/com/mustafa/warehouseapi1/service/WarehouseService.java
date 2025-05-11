package com.mustafa.warehouseapi1.service;

import com.mustafa.warehouseapi1.model.Product;
import com.mustafa.warehouseapi1.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@ApplicationScoped
public class WarehouseService {
    private final ReentrantLock lock = new ReentrantLock();

    @Inject
    private ProductRepository productRepository;

    public Product getProduct(Long id) {
        lock.lock();
        try {
            return productRepository.find(id);
        } finally {
            lock.unlock();
        }
    }

    public List<Product> getAllProducts() {
        lock.lock();
        try {
            return productRepository.findAll();
        } finally {
            lock.unlock();
        }
    }

    @Transactional
    public void addProduct(Product product) {
        lock.lock();
        try {
            productRepository.save(product);
        } finally {
            lock.unlock();
        }
    }

    @Transactional
    public void updateProduct(Long id, Product product) {
        lock.lock();
        try {
            Product existingProduct = productRepository.find(id);
            if (existingProduct != null) {
                product.setId(id);
                productRepository.update(product);
            }
        } finally {
            lock.unlock();
        }
    }

    @Transactional
    public void deleteProduct(Long id) {
        lock.lock();
        try {
            Product product = productRepository.find(id);
            if (product != null) {
                productRepository.delete(product);
            }
        } finally {
            lock.unlock();
        }
    }

    public List<Product> getProductsByCategory(String category) {
        lock.lock();
        try {
            return productRepository.findByCategory(category);
        } finally {
            lock.unlock();
        }
    }
} 