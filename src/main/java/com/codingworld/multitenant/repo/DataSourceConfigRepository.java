package com.codingworld.multitenant.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codingworld.multitenant.config.DataSourceConfig;

public interface DataSourceConfigRepository extends JpaRepository<DataSourceConfig, String> {
    @Query(value = "select * from acessos where chave is not null and ativo = 'S'", nativeQuery = true)
    List<DataSourceConfig> findByActive();
    
    DataSourceConfig findByChave(String chave);
}
