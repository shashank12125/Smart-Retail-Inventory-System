package com.shashankcharpe.inventory.controller;


import com.shashankcharpe.inventory.service.BillingService;
import com.shashankcharpe.inventory.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    private final ProductService productService;
    private final BillingService billingService;

    public DashboardController(ProductService productService, BillingService billingService) {
        this.productService = productService;
        this.billingService = billingService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // total products
        model.addAttribute("totalProducts",productService.getAllProducts().size());

        // low stock products
        model.addAttribute("lowStockCount",productService.getLowStockProducts().size());

        // top product
        var topProducts = billingService.getTopSellingProducts();
        if(!topProducts.isEmpty()) {
            model.addAttribute("topProduct",topProducts.get(0)[0]);
        } else {
            model.addAttribute("topProduct","N/A");
        }

        return "dashboard";
    }

}
