package com.akadir.springdemo.dao.base;

import com.akadir.springdemo.entity.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author akadir
 * Date: 29.02.2020
 * Time: 16:41
 */
@NoRepositoryBean
public interface IBaseDAO<T extends BaseEntity> extends JpaRepository<T, Long> {
}
