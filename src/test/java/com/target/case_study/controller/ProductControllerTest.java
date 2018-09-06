package com.target.case_study.controller;

import com.target.case_study.domain.Product;
import com.target.case_study.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.NotFoundException;

import static org.junit.Assert.*;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @Mock
    private ProductService productServiceMock;

    private ProductController productController;

    private Integer productId = 7;

    private String errorMessage = "Oh no!";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productController = new ProductController(productServiceMock);
    }

    @Test
    public void getProductSuccessTest() {
        ResponseEntity<?> expected = new ResponseEntity<>(getProduct(), HttpStatus.OK);

        try {
            Mockito.when(productServiceMock.getProductInfo(productId)).thenReturn(getProduct());
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        ResponseEntity<?> actual = productController.getProduct(productId);

        Product expectedProduct = (Product) expected.getBody();
        Product actualProduct = (Product) actual.getBody();

        assertEquals(expected.getStatusCode(), actual.getStatusCode());
        assertEquals(expectedProduct.getId(), actualProduct.getId());
    }

    @Test
    public void getProductReturnsIOExceptionMessageWith500Status() {
        IOException exception = new IOException(errorMessage);
        ResponseEntity<?> expected = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        try {
            Mockito.when(productServiceMock.getProductInfo(productId)).thenThrow(exception);
        } catch (IOException e) {
            exception.printStackTrace();
        }

        ResponseEntity<?> actual = productController.getProduct(productId);

        assertEquals(expected.getStatusCode(), actual.getStatusCode());
        assertEquals(expected.getBody(), actual.getBody());

    }

    @Test
    public void getProductReturnsNotFoundExceptionMessageWith404Status() {
        NotFoundException exception = new NotFoundException(errorMessage);
        ResponseEntity<?> expected = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

        try {
            Mockito.when(productServiceMock.getProductInfo(productId)).thenThrow(exception);
        } catch (IOException e) {
            exception.printStackTrace();
        }

        ResponseEntity<?> actual = productController.getProduct(productId);

        assertEquals(expected.getStatusCode(), actual.getStatusCode());
        assertEquals(expected.getBody(), actual.getBody());

    }

    @Test
    public void updateProductSuccess() {
        ResponseEntity<?> expected = new ResponseEntity<>(HttpStatus.OK);

        ResponseEntity<?> actual = productController.updateProduct(productId, getProduct());

        assertEquals(expected, actual);
    }


    private Product getProduct() {
        Product result = new Product();
        result.setId(productId);

        return result;
    }

}
