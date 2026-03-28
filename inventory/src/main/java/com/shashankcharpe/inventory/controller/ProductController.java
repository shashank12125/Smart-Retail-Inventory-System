package com.shashankcharpe.inventory.controller;
import com.shashankcharpe.inventory.model.Product;
import com.shashankcharpe.inventory.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;

@Controller
public class ProductController {
        private final ProductService productService;

        public ProductController(ProductService productService) {
            this.productService = productService;
        }

        //showing product form
        @GetMapping("/add-product")
        public String showForm(Model model) {
            model.addAttribute("product",new Product());
            return "add-product";
        }

        //save product
        @PostMapping("/save-product")
        public String saveProduct(@ModelAttribute Product product) {
            productService.saveProduct(product);
            return "redirect:/products";
        }

        // showing product list
        @GetMapping("/products")
        public String listProducts(Model model) {
            List<Product> products = productService.getAllProducts().stream().filter(p -> p != null).toList();
            List<Product> lowStockProducts = productService.getLowStockProducts();

            if(lowStockProducts == null) {
                lowStockProducts = List.of();
            }

            model.addAttribute("products", products);
            model.addAttribute("lowStockProducts",lowStockProducts);
            return "product-list";
        }

        // delete products using id
        @GetMapping("/delete/{id}")
        public String deleteProduct(@PathVariable Long id) {
            productService.deleteProduct(id);
            return "redirect:/products";
        }

        @GetMapping("/")
        public String homeRedirect() {
            return "redirect:/products";
        }

        @GetMapping("/edit/{id}")
        public String editProduct(@PathVariable Long id, Model model) {
            Product product = productService.getProductById(id);
            model.addAttribute("product",product);
            return "add-product";
        }

        @GetMapping("/low-stock")
        @ResponseBody
        public List<Product> getLowStockProducts() {
            return productService.getLowStockProducts();
        }

        @GetMapping("/restock/{id}")
        public String restockForm(@PathVariable Long id, Model model) {
            Product product = productService.getProductById(id);
            model.addAttribute("product",product);
            return "restock";
        }

        @PostMapping("/update-stock")
        public String updateStock(@RequestParam("id") Long id, @RequestParam("quantity") int quantity) {

            System.out.println("ID: "+id);
            System.out.println("Quantity: "+quantity);


            Product product = productService.getProductById(id);

            if(product == null) {
                System.out.println("Product not found");
                return "redirect:/products";
            }

            int newQuantity = product.getQuantity() + quantity;

            product.setQuantity(newQuantity);

            productService.saveProduct(product);

            System.out.println("Updated Quantity: "+newQuantity);

            return "redirect:/products";
        }


}
