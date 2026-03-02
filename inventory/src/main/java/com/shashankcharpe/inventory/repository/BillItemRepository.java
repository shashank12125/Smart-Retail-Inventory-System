package com.shashankcharpe.inventory.repository;
import com.shashankcharpe.inventory.model.BillItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillItemRepository extends JpaRepository<BillItem, Long>{

}
