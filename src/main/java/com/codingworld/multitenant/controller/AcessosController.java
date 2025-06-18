package com.codingworld.multitenant.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codingworld.multitenant.config.DataSourceConfig;
import com.codingworld.multitenant.service.AcessosService;

@RestController
public class AcessosController {
    
    @Autowired
    private AcessosService acessosService;
    
    @RequestMapping(value = "/getacessos/all", method = RequestMethod.GET)
    public ResponseEntity<List<DataSourceConfig>> getAll() throws SQLException {
        List<DataSourceConfig> cities = acessosService.getAll();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
}
