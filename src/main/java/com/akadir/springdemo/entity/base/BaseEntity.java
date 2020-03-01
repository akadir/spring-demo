package com.akadir.springdemo.entity.base;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author akadir
 * Date: 29.02.2020
 * Time: 16:32
 */
@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    protected Long id;
    @CreatedDate
    protected Date createdAt;
}
