package com.example.LMS.seed;


import com.example.LMS.model.ApiClient;
import com.example.LMS.model.LoanProduct;
import com.example.LMS.repository.ApiClientRepository;
import com.example.LMS.repository.LoanProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedDataRunner implements CommandLineRunner {

    private final LoanProductRepository loanProductRepository;
    private final ApiClientRepository apiClientRepository;

    public SeedDataRunner(LoanProductRepository loanProductRepository,
                          ApiClientRepository apiClientRepository) {
        this.loanProductRepository = loanProductRepository;
        this.apiClientRepository = apiClientRepository;
    }

    @Override
    public void run(String... args) {
        if (loanProductRepository.count() == 0) {
            loanProductRepository.save(new LoanProduct("LAMF Overdraft", 14.5, 0.50, 10000, 500000));
            loanProductRepository.save(new LoanProduct("LAMF Term Loan", 16.0, 0.45, 10000, 300000));
        }

        if (apiClientRepository.count() == 0) {
            apiClientRepository.save(new ApiClient("DemoFintech", "DEMO_PARTNER_KEY_123"));
        }


    }

}
