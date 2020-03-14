package com.akadir.springdemo.mapper;

import com.akadir.springdemo.dto.ProductDTO;
import com.akadir.springdemo.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @author akadir
 * Date: 29.02.2020
 * Time: 17:03
 */
@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductMapper {
    ProductDTO toProductDto(Product product);

    List<ProductDTO> toProductDTOList(List<Product> productList);

    @Mapping(target = "createdAt", ignore = true)
    Product toProduct(ProductDTO productDTO);
}
