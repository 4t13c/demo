package com.example.demo.service;

import com.example.demo.controller.dto.request.FilterPriceRequest;
import com.example.demo.controller.dto.request.PriceRequest;
import com.example.demo.controller.dto.response.PriceResponse;
import com.example.demo.repository.entities.PriceEntity;

import java.util.List;
import java.util.Optional;

public interface PriceService {
    List<PriceResponse> getPriceFiltered(FilterPriceRequest request);
}
