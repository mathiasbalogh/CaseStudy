package com.target.case_study.service;

import com.target.case_study.domain.ProductPrice;
import com.target.case_study.repository.ProductPriceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

import javax.ws.rs.NotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class PricingServiceTest {

    @Mock
    private ProductPriceRepository productPriceRepositoryMock;

    private PricingService pricingService;

    private Double value = 7.77;

    private String currencyCode = "HUF";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        pricingService = new PricingService(productPriceRepositoryMock);
    }

    @Test
    public void getProductPriceSuccess() {
        Mockito.when(productPriceRepositoryMock.findById(Mockito.anyInt())).thenReturn(getProductPrice());

        ProductPrice actual = pricingService.getProductPrice(7);

        assertEquals(getProductPrice().get_id(), actual.get_id());

    }

    @Test(expected = NotFoundException.class)
    public void getProductPriceFailure() {
        Mockito.when(productPriceRepositoryMock.findById(Mockito.anyInt())).thenReturn(null);

        pricingService.getProductPrice(7);

    }

    private ProductPrice getProductPrice() {
        ProductPrice result = new ProductPrice();

        result.setValue(value);
        result.setCurrency_code(currencyCode);

        return result;
    }

}
