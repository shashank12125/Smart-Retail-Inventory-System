package com.shashankcharpe.inventory.service;
import com.shashankcharpe.inventory.model.*;
import com.shashankcharpe.inventory.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BillingService {
    private final ProductRepository productRepository;
    private final BillRepository billRepository;

    public BillingService(ProductRepository productRepository, BillRepository billRepository) {
        this.productRepository = productRepository;
        this.billRepository = billRepository;
    }

    // new update
    public java.util.List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Bill getBillById(Long id) {
        return billRepository.findById(id).orElseThrow(() -> new RuntimeException("Bill Not Found"));
    }


    @Transactional
    public Bill createBill(java.util.Map<Long, Integer> productQuantities) {

        Bill bill = new Bill();
        bill.setBillDate(LocalDateTime.now());

        double totalAmount = 0;
        java.util.List<BillItem> items = new java.util.ArrayList<>();

        for(var entry : productQuantities.entrySet()) {
            Long productId = entry.getKey();
            int quantity = entry.getValue();

            if(quantity <= 0)
                continue;

            Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product Not Found"));
            if(!product.isActive()) {
                throw new RuntimeException("Product is Inactive");
            }

            double subtotal = product.getPrice() * quantity;

            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);

            BillItem item = new BillItem();
            item.setBill(bill);
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setSubtotal(subtotal);

            items.add(item);
            totalAmount += subtotal;
        }

        bill.setItems(items);
        bill.setTotalAmount(totalAmount);

        return billRepository.save(bill);

    }

}
