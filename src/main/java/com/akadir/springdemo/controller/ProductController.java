package com.akadir.springdemo.controller;

import com.akadir.springdemo.dto.ProductDTO;
import com.akadir.springdemo.entity.Product;
import com.akadir.springdemo.mapper.ProductMapper;
import com.akadir.springdemo.service.imp.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author akadir
 * Date: 29.02.2020
 * Time: 16:57
 */
@RestController
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/products")
    public ResponseEntity<ProductDTO> newProduct(@RequestBody ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.toProduct(productDTO);
        product = productService.saveProduct(product);
        return ResponseEntity.ok(ProductMapper.INSTANCE.toProductDto(product));
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<ProductDTO> one(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        return ResponseEntity.ok(ProductMapper.INSTANCE.toProductDto(product));
    }

    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductDTO>> all() {
        List<Product> products = productService.getAllProduct();

        return ResponseEntity.ok(ProductMapper.INSTANCE.toProductDTOList(products));
    }
}
