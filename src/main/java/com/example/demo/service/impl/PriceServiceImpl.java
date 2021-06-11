package com.example.demo.service.impl;

import com.example.demo.controller.dto.request.FilterPriceRequest;
import com.example.demo.controller.dto.request.PriceRequest;
import com.example.demo.controller.dto.response.PriceResponse;
import com.example.demo.mapper.PriceEntityMapper;
import com.example.demo.repository.PricesRepository;
import com.example.demo.repository.entities.PriceEntity;
import com.example.demo.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PriceServiceImpl implements PriceService {
    @Autowired
    PricesRepository pricesRepository;

    @Override
    public List<PriceResponse> getPriceFiltered(FilterPriceRequest request) {
        List<PriceEntity> entityResponse = pricesRepository
                .GetFilteredPrice(
                    request.getBrandId(),
                    request.getPriceListId(),
                    request.getProductId(),
                    request.getApplicationDate(),
                    request.getApplicationDate()
                );
        List<PriceResponse> response = new ArrayList<>();

        entityResponse.forEach( i->{
            PriceResponse responseItem=  PriceEntityMapper.fromPriceEntity2PriceResponse(i);
            response.add(responseItem);
        });
        if(!(response ==null) ||response.isEmpty() )
            return response;
        return null;
    }
}
