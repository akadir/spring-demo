package com.akadir.springdemo.dto;

import lombok.Data;

/**
 * @author akadir
 * Date: 29.02.2020
 * Time: 17:04
 */
@Data
public class ProductDTO {
    private Long id;
    private String name;
    private int count;
}