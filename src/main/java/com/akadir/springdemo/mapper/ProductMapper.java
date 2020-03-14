package com.akadir.springdemo.mapper;

import com.akadir.springdemo.dto.ProductDTO;
import com.akadir.springdemo.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author akadir
 * Date: 29.02.2020
 * Time: 17:03
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toProductDto(Product product);

    List<ProductDTO> toProductDTOList(List<Product> productList);

    Product toProduct(ProductDTO productDTO);
}
