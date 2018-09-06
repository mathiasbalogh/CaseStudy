package com.target.case_study.service;

import com.target.case_study.domain.Product;
import com.target.case_study.domain.ProductPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.io.IOException;

@Service
public class ProductService {

    @Autowired
    private RedSkyApiService redSkyApiService;

    @Autowired
    private PricingService pricingService;

    /**
     * Gets Product info from Red Sky Api and MongoDB
     *
     * @param id
     * @return
     * @throws IOException
     * @throws NotFoundException
     */
    public Product getProductInfo(Integer id) throws IOException, NotFoundException {

        Product result = redSkyApiService.getProductInfo(id);

        result.setCurrent_price(pricingService.getProductPrice(id));

        return result;
    }

    /**
     * Updates Product info
     * Updates Pricing Info only
     * TODO Save new product info back to Red Sky Api
     *
     * @param product
     */
    public void updateProductInfo(Product product) {
        ProductPrice productPrice = product.getCurrent_price();
        productPrice.setId(product.getId());

        pricingService.updateProductPrice(productPrice);
    }
}
