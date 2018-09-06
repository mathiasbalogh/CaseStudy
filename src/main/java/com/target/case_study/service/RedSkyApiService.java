package com.target.case_study.service;

import com.target.case_study.domain.Product;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.io.IOException;

@Service
public class RedSkyApiService extends ExternalConnectionService {

    public RedSkyApiService() {
        this.setUrl("https://redsky.target.com/v2/pdp/tcin/");
    }

    /**
     * Gets product info from RedSkyApi
     *
     * @param id
     * @return
     * @throws IOException
     */
    public Product getProductInfo(Integer id) throws IOException {
        Product result;
        String productInfo = this.getPayload(this.url + id);
        if (productInfo.isEmpty()) {
            throw new NotFoundException("Product with id:" + id + " not found.");
        }
        result = this.parsingService.buildProductFromApiResponse(productInfo);
        result.setId(id);

        return result;
    }

}
