package com.target.case_study.controller;


import com.target.case_study.domain.Product;
import com.target.case_study.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@CrossOrigin
@RestController
public class ProductController {


    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/products/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getProduct(@PathVariable("productId") Integer id) {
        ResponseEntity result;
        try {
            Product product = productService.getProductInfo(id);
            result = new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IOException exception) {
            result = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException exception) {
            result = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
        return result;
    }

    @RequestMapping(value = "/products/{productId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> updateProduct(@PathVariable("productId") Integer id, @RequestBody Product request) {
        ResponseEntity result;

        try {
            productService.updateProductInfo(request);
            result = new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return result;
    }
}
