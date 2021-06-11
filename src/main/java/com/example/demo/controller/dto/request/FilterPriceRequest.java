package com.example.demo.controller.dto.request;

import com.example.demo.utils.CustomLocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.Objects;

public class FilterPriceRequest {
    private Integer brandId;
    private Integer priceListId;
    private Integer productId;
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime applicationDate;

    public Integer getPriceListId() {
        return priceListId;
    }

    public FilterPriceRequest setPriceListId(Integer priceListId) {
        this.priceListId = priceListId;
        return this;
    }

    public Integer getProductId() {
        return productId;
    }

    public FilterPriceRequest setProductId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public FilterPriceRequest setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
        return this;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public FilterPriceRequest setBrandId(Integer brandId) {
        this.brandId = brandId;
        return this;
    }

    @Override
    public String toString() {
        return "PriceRequest{" +
                "brandId=" + brandId +
                ", priceListId=" + priceListId +
                ", productId=" + productId +
                ", applicationDate=" + applicationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterPriceRequest that = (FilterPriceRequest) o;
        return Objects.equals(brandId, that.brandId) && Objects.equals(priceListId, that.priceListId) && Objects.equals(productId, that.productId) && Objects.equals(applicationDate, that.applicationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, priceListId, productId, applicationDate);
    }
}