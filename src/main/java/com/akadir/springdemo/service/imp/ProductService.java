package com.akadir.springdemo.service.imp;

import com.akadir.springdemo.dao.IProductDAO;
import com.akadir.springdemo.entity.Product;
import com.akadir.springdemo.exception.DataNotFoundException;
import com.akadir.springdemo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author akadir
 * Date: 29.02.2020
 * Time: 16:50
 */
@Service
public class ProductService implements IProductService {
    private IProductDAO productDAO;

    @Autowired
    public ProductService(IProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Product saveProduct(Product product) {
        return productDAO.saveAndFlush(product);
    }

    public Product getProduct(Long id) {
        Optional<Product> product = Optional.ofNullable(productDAO.getOne(id));

        if (!product.isPresent()) {
            throw new DataNotFoundException();
        }

        return product.get();
    }

    public List<Product> getAllProduct() {
        return productDAO.findAll();
    }
}
