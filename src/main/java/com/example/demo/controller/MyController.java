package com.example.demo.controller;

import com.example.demo.controller.dto.request.FilterPriceRequest;
import com.example.demo.controller.dto.request.PriceRequest;
import com.example.demo.controller.dto.response.PriceResponse;
import com.example.demo.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@RestController
public class MyController {

    @Autowired public PriceService priceService;

    @GetMapping("/version")
    public String getVersion() {

        return "0.1";
    }

    @PostMapping(value = "/prices")
    public ResponseEntity<List<PriceResponse>> getPriceFiltered(@RequestBody FilterPriceRequest request){
        List<PriceResponse> response = priceService.getPriceFiltered(request);
        return ResponseEntity.of(Optional.of(response));
    }
}