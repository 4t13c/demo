package com.example.demo.repository.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "PRICES")
public class PriceEntity {
    @NotNull
    Integer brand_Id;
    @NotNull
    LocalDateTime start_Date;
    @NotNull
    LocalDateTime end_Date;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer price_List;
    @NotNull
    Integer product_Id;
    @NotNull
    Integer priority;
    @NotNull
    double price;
    @NotNull
    String curr;

    public Integer getBrand_Id() {
        return brand_Id;
    }

    public void setBrand_Id(Integer brand_Id) {
        this.brand_Id = brand_Id;
    }

    public LocalDateTime getStart_Date() {
        return start_Date;
    }

    public void setStart_Date(LocalDateTime start_Date) {
        this.start_Date = start_Date;
    }

    public LocalDateTime getEnd_Date() {
        return end_Date;
    }

    public void setEnd_Date(LocalDateTime end_Date) {
        this.end_Date = end_Date;
    }

    public Integer getPrice_List() {
        return price_List;
    }

    public void setPrice_List(Integer price_List) {
        this.price_List = price_List;
    }

    public Integer getProduct_Id() {
        return product_Id;
    }

    public void setProduct_Id(Integer product_Id) {
        this.product_Id = product_Id;
    }

    public Integer getBrandId() {
        return brand_Id;
    }

    public PriceEntity setBrandId(Integer brandId) {
        this.brand_Id = brandId;
        return this;
    }

    public LocalDateTime getStartDate() {
        return start_Date;
    }

    public PriceEntity setStartDate(LocalDateTime startDate) {
        this.start_Date = startDate;
        return this;
    }

    public LocalDateTime getEndDate() {
        return end_Date;
    }

    public PriceEntity setEndDate(LocalDateTime endDate) {
        this.end_Date = endDate;
        return this;
    }

    public Integer getPriceListId() {
        return price_List;
    }

    public PriceEntity setPriceListId(Integer priceListId) {
        this.price_List = priceListId;
        return this;
    }

    public Integer getProductId() {
        return product_Id;
    }

    public PriceEntity setProductId(Integer productId) {
        this.product_Id = productId;
        return this;
    }

    public Integer getPriority() {
        return priority;

    }

    public PriceEntity setPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public PriceEntity setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getCurr() {
        return curr;
    }

    public PriceEntity setCurr(String curr) {
        this.curr = curr;
        return this;
    }

    @Override
    public String toString() {
        return "PriceEntity{" +
                "brandId=" + brand_Id +
                ", startDate=" + start_Date +
                ", endDate=" + end_Date +
                ", priceListId=" + price_List +
                ", productId=" + product_Id +
                ", priority=" + priority +
                ", price=" + price +
                ", curr='" + curr + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceEntity that = (PriceEntity) o;
        return Double.compare(that.price, price) == 0 && Objects.equals(brand_Id, that.brand_Id) && Objects.equals(start_Date, that.start_Date) && Objects.equals(end_Date, that.end_Date) && Objects.equals(price_List, that.price_List) && Objects.equals(product_Id, that.product_Id) && Objects.equals(priority, that.priority) && Objects.equals(curr, that.curr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand_Id, start_Date, end_Date, price_List, product_Id, priority, price, curr);
    }
}
