package com.shashankcharpe.inventory.repository;
import com.shashankcharpe.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>{

    List<Product> findByActiveTrue();

}
