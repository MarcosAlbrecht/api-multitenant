package com.codingworld.multitenant.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingworld.multitenant.bean.Acessos;
import com.codingworld.multitenant.repo.AcessosRepository;


@Service
public class AcessosService {
    
    @Autowired
    private AcessosRepository acessosRepository;

    public List<Acessos> getAll() throws SQLException {
        return acessosRepository.findAll();

    }
}
