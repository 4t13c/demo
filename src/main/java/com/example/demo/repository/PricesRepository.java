package com.example.demo.repository;

import com.example.demo.repository.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PricesRepository extends JpaRepository<PriceEntity, Long> {
    @Query(value = "SELECT * FROM PRICES WHERE brand_Id = ?1 OR price_List = ?2 OR product_Id = ?3 OR START_DATE <= ?4 OR end_Date>= ?5", nativeQuery = true)
    public List<PriceEntity> GetFilteredPrice(Integer brandId, Integer priceListId, Integer productId, LocalDateTime applicationDate1, LocalDateTime applicationDate2);
    //public Optional<PriceEntity> findByBrandIdOrPriceListIdOrProductIdOrEndDateGreaterThanEqualOrStartDateLessThanEqual(Integer brandId, Integer priceListId, Integer productId, LocalDateTime applicationDate1, LocalDateTime applicationDate2);
}
