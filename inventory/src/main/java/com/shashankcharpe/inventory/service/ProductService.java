package com.shashankcharpe.inventory.service;

import com.shashankcharpe.inventory.model.Product;
import com.shashankcharpe.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findByActiveTrue();
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        if(product != null) {
            product.setActive(false);
            productRepository.save(product);
        }
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

}
