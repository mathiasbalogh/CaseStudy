package com.target.case_study.service;

import com.target.case_study.domain.Product;
import com.target.case_study.domain.ProductPrice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class ParsingServiceTest {

    private ParsingService parsingService = new ParsingService();

    @Test
    public void buildProductFromApiResponse() {
        Product actual = null;
        Product expected = getProduct();

        try {
            actual = parsingService.buildProductFromApiResponse(getApiResponse());
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());

    }

    private String getApiResponse() {

        return "{\"product\":{\"id\":7,\"name\":\"Title\",\"current_price\":{\"value\":8.99,\"currency_code\":\"USD\"}, \"item\":{\"product_description\":{\"title\":\"Title\"}}}}";
    }

    private Product getProduct() {
        Product result = new Product();
        ProductPrice productPrice = new ProductPrice();
        Product.Item item = new Product.Item();
        Product.Item.ProductDescription productDescription = new Product.Item.ProductDescription();

        productDescription.setTitle("Title");

        item.setProduct_description(productDescription);

        productPrice.setValue(8.99);
        productPrice.setCurrency_code("USD");

        result.setId(7);
        result.setCurrent_price(productPrice);
        result.setItem(item);
        result.setName("Title");

        return result;
    }

}
