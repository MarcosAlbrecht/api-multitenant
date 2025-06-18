package com.codingworld.multitenant.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingworld.multitenant.config.DataSourceConfig;
import com.codingworld.multitenant.repo.DataSourceConfigRepository;


@Service
public class AcessosService {
    
    @Autowired
    private DataSourceConfigRepository acessosRepository;

    public List<DataSourceConfig> getAll() throws SQLException {
        return acessosRepository.findAll();

    }
}
