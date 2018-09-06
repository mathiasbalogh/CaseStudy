package com.target.case_study.service;

import com.target.case_study.domain.ProductPrice;
import com.target.case_study.repository.ProductPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;


@Service
public class PricingService {

    @Autowired
    private ProductPriceRepository productPriceRepository;

    public PricingService(ProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }

    /**
     * Gets Product price from DB
     *
     * @param id
     * @return
     */
    public ProductPrice getProductPrice(Integer id) {
        ProductPrice result = productPriceRepository.findById(id);
        if (result == null) {
            throw new NotFoundException("Pricing for product with id: "+ id + " not found.");
        }
        return result;
    }

    /**
     * Updates Product price in DB
     *
     * @param productPrice
     */
    public void updateProductPrice(ProductPrice productPrice) {
        ProductPrice currentPrice = productPriceRepository.findById(productPrice.getId());
        if (currentPrice.getId() != null) {
            productPrice.set_id(currentPrice.get_id());
        }
        productPriceRepository.save(productPrice);
    }
}
