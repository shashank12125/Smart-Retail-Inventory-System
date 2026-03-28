package com.shashankcharpe.inventory.repository;
import com.shashankcharpe.inventory.model.BillItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillItemRepository extends JpaRepository<BillItem, Long>{

    @Query("SELECT bi.product.name, SUM(bi.quantity) FROM BillItem bi GROUP BY bi.product.name ORDER BY SUM(bi.quantity) DESC")
    List<Object[]> findTopSellingProducts();

}
