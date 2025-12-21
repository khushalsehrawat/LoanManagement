package com.example.LMS.service;


import com.example.LMS.dto.product.CreateProductRequest;
import com.example.LMS.exception.NotFoundException;
import com.example.LMS.model.LoanProduct;
import com.example.LMS.repository.LoanProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final LoanProductRepository loanProductRepository;

    public ProductService(LoanProductRepository loanProductRepository) {
        this.loanProductRepository = loanProductRepository;
    }

    public LoanProduct create(CreateProductRequest req) {
        LoanProduct p = new LoanProduct(
                req.getName(),
                req.getInterestRate(),
                req.getMaxLtv(),
                req.getMinAmount(),
                req.getMaxAmount()
        );
        return loanProductRepository.save(p);
    }

    public List<LoanProduct> getAll() {
        return loanProductRepository.findAll();
    }

    public LoanProduct getById(Long id) {
        return loanProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("LoanProduct not found: " + id));
    }

}
