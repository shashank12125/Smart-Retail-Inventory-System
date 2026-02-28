package com.shashankcharpe.inventory.repository;
import com.shashankcharpe.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{



}
