package com.shashankcharpe.inventory.repository;
import com.shashankcharpe.inventory.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long>{

}
