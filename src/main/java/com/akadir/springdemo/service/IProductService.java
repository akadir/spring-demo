package com.akadir.springdemo.service;

import com.akadir.springdemo.entity.Product;

import java.util.List;

/**
 * @author akadir
 * Date: 29.02.2020
 * Time: 16:51
 */
public interface IProductService {
    Product saveProduct(Product product);

    Product getProduct(Long id);

    List<Product> getAllProduct();

}
