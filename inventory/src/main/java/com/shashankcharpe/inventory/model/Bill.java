package com.shashankcharpe.inventory.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime billDate;
    private double totalAmount;

    @OneToMany(mappedBy = "bill",cascade = CascadeType.ALL)
    private List<BillItem> items;

}
