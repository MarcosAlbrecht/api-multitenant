package com.codingworld.multitenant.repo;

import com.codingworld.multitenant.config.DataSourceConfig;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DataSourceConfigRepository extends JpaRepository<DataSourceConfig, Long> {
    @Query(value = "select * from acessos where name is not null", nativeQuery = true)
    List<DataSourceConfig> findByActive();
    
    DataSourceConfig findByName(String name);
}
