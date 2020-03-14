package com.akadir.springdemo.controller;

import com.akadir.springdemo.annotation.Loggable;
import com.akadir.springdemo.dto.ProductDTO;
import com.akadir.springdemo.entity.Product;
import com.akadir.springdemo.entity.enumeration.LoggableType;
import com.akadir.springdemo.mapper.ProductMapper;
import com.akadir.springdemo.service.imp.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author akadir
 * Date: 29.02.2020
 * Time: 16:57
 */
@RestController
@Loggable(type = LoggableType.CONTROLLER)
public class ProductController {
    private ProductService productService;
    private ProductMapper productMapper;

    @Autowired
    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping(value = "/products")
    public ResponseEntity<ProductDTO> newProduct(@RequestBody ProductDTO productDTO) {
        Product product = productMapper.toProduct(productDTO);
        product = productService.saveProduct(product);
        return ResponseEntity.ok(productMapper.toProductDto(product));
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<ProductDTO> one(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        return ResponseEntity.ok(productMapper.toProductDto(product));
    }

    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductDTO>> all() {
        List<Product> products = productService.getAllProduct();

        return ResponseEntity.ok(productMapper.toProductDTOList(products));
    }
}
