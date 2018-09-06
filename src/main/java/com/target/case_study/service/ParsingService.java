package com.target.case_study.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.case_study.domain.Product;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ParsingService {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public ParsingService() {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    /**
     * Builds Product and sets Name
     *
     * @param productInfo
     * @return
     * @throws IOException
     */
    public Product buildProductFromApiResponse(String productInfo) throws IOException {
        Product result;

        JsonNode node = objectMapper.readTree(productInfo);
        String product = node.get("product").toString();
        result = buildProductFromJson(product);
        result.setNameFromProductDescription();

        return result;
    }

    /**
     * Builds a Product Class from Json string
     *
     * @param productInfo
     * @return
     * @throws IOException
     */
    public Product buildProductFromJson(String productInfo) throws IOException {
        Product result;

        result = objectMapper.readValue(productInfo, Product.class);

        return result;
    }

}
