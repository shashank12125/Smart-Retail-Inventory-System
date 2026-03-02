package com.shashankcharpe.inventory.controller;

import com.shashankcharpe.inventory.model.Bill;
import com.shashankcharpe.inventory.service.BillingService;
import com.shashankcharpe.inventory.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BillingController {

    private final BillingService billingService;
    private final ProductService productService;

    public BillingController(BillingService billingService, ProductService productService) {
        this.billingService = billingService;
        this.productService = productService;
    }

   // show billing form
    @GetMapping("/create-bill")
    public String showBillForm(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "create-bill";
    }

    // Process Billing
    @PostMapping("/create-bill")
    public String createBill(@RequestParam java.util.Map<String, String> formData, Model model) {
       java.util.Map<Long, Integer> productQuantities = new java.util.HashMap<>();

       for(var entry : formData.entrySet()) {
           if(entry.getKey().startsWith("qty_")) {

               if(entry.getValue() == null || entry.getValue().isBlank()) {
                   continue;
               }

               Long productId = Long.parseLong(entry.getKey().substring(4));
               int quantity = Integer.parseInt(entry.getValue());
               productQuantities.put(productId, quantity);
           }
       }

       Bill bill = billingService.createBill(productQuantities);
       model.addAttribute("bill",bill);

        return "bill-success";
    }

    // new update
    @GetMapping("/bills")
    public String listBills(Model model) {
        model.addAttribute("bills",billingService.getAllBills());
        return "bill-list";
    }

    @GetMapping("/bills/{id}")
    public String viewBill(@PathVariable Long id, Model model) {
        Bill bill = billingService.getBillById(id);
        model.addAttribute("bill", bill);
        return "bill-detail";
    }



}