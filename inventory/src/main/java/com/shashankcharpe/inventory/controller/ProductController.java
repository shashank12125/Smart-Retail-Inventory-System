package com.shashankcharpe.inventory.controller;
import com.shashankcharpe.inventory.model.Product;
import com.shashankcharpe.inventory.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            model.addAttribute("products", productService.getAllProducts());
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


}
