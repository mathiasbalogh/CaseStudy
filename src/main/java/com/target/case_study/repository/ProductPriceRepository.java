package com.target.case_study.repository;

import com.target.case_study.domain.ProductPrice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends MongoRepository<ProductPrice, String> {

    ProductPrice findById(Integer id);
}
