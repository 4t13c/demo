package com.example.demo.mapper;

import com.example.demo.controller.dto.request.PriceRequest;
import com.example.demo.controller.dto.response.PriceResponse;
import com.example.demo.repository.entities.PriceEntity;

import java.util.Optional;

public class PriceEntityMapper {

    public static PriceResponse fromPriceEntity2PriceResponse(PriceEntity entity){
        PriceResponse response= new PriceResponse();
        response.setPrice(entity.getPrice())
                .setProductId(entity.getProductId())
                .setPriceListId(entity.getPriceListId())
                .setBrandId(entity.getBrandId())
                .setCurr(entity.getCurr())
                .setPriority(entity.getPriority())
                .setEndDate(entity.getEndDate())
                .setStartDate(entity.getStartDate());

        return response;
    }
    public static PriceEntity fromPriceRequest2PriceEntity(PriceRequest request){
        PriceEntity entity = new PriceEntity();
        entity.setPriceListId(request.getPriceListId())
                .setPrice(request.getPrice())
                .setCurr(request.getCurr())
                .setPriority(request.getPriority())
                .setBrandId(request.getBrandId())
                .setProductId(request.getProductId())
                .setStartDate(request.getStartDate())
                .setEndDate(request.getEndDate());
        return entity;

    }
}
