package com.example.LMS.service;


import com.example.LMS.exception.BadRequestException;
import com.example.LMS.model.ApiClient;
import com.example.LMS.repository.ApiClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ApiClientService {

    private final ApiClientRepository apiClientRepository;

    public ApiClientService(ApiClientRepository apiClientRepository) {
        this.apiClientRepository = apiClientRepository;
    }

    public ApiClient validateKey(String apiKey) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new BadRequestException("Missing X-API-KEY");
        }
        return apiClientRepository.findByApiKey(apiKey)
                .orElseThrow(() -> new BadRequestException("Invalid X-API-KEY"));
    }

}
