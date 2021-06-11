package com.example.demo.controller.dto.request;

import com.example.demo.utils.CustomLocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.Objects;

public class PriceRequest {
    private Integer brandId;
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime startDate;
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime endDate;
    private Integer priceListId;
    private Integer productId;
    private Integer priority;
    private double price;
    private String curr;

    public Integer getBrandId() {
        return brandId;
    }

    public PriceRequest setBrandId(Integer brandId) {
        this.brandId = brandId;
        return this;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public PriceRequest setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public PriceRequest setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public Integer getPriceListId() {
        return priceListId;
    }

    public PriceRequest setPriceListId(Integer priceListId) {
        this.priceListId = priceListId;
        return this;
    }

    public Integer getProductId() {
        return productId;
    }

    public PriceRequest setProductId(Integer productId) {
        this.productId = productId;

        return this;
    }

    public Integer getPriority() {
        return priority;
    }

    public PriceRequest setPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public PriceRequest setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getCurr() {
        return curr;
    }

    public PriceRequest setCurr(String curr) {
        this.curr = curr;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceRequest that = (PriceRequest) o;
        return Double.compare(that.price, price) == 0 && Objects.equals(brandId, that.brandId) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(priceListId, that.priceListId) && Objects.equals(productId, that.productId) && Objects.equals(priority, that.priority) && Objects.equals(curr, that.curr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, startDate, endDate, priceListId, productId, priority, price, curr);
    }
}