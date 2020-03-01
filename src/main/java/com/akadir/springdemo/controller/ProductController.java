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

    @PostMapping(value = "create-product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.toProduct(productDTO);
        product = productService.saveProduct(product);
        return ResponseEntity.ok(ProductMapper.INSTANCE.toProductDto(product));
    }

    @GetMapping(value = "get-product")
    public ResponseEntity<ProductDTO> getProductById(@RequestParam Long id) {
        Product product = productService.getProduct(id);
        return ResponseEntity.ok(ProductMapper.INSTANCE.toProductDto(product));
    }

    @GetMapping(value = "get-all")
    public ResponseEntity<List<ProductDTO>> getAllProduct() {
        List<Product> products = productService.getAllProduct();

        return ResponseEntity.ok(ProductMapper.INSTANCE.toProductDTOList(products));
    }
}
