package com.example.LMS.controller;


import com.example.LMS.dto.product.CreateProductRequest;
import com.example.LMS.model.LoanProduct;
import com.example.LMS.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public LoanProduct create(@RequestBody CreateProductRequest request)
    {
        return productService.create(request);
    }

    @GetMapping
    public List<LoanProduct> list(){
        return productService.getAll();
    }


}
