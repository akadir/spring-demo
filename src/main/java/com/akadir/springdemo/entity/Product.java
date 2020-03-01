package com.akadir.springdemo.entity;

import com.akadir.springdemo.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

/**
 * @author akadir
 * Date: 29.02.2020
 * Time: 16:32
 */
@Data
@Entity(name = "Product")
public class Product extends BaseEntity {
    private String name;
    private int count;
}
