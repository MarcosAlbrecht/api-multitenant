package com.codingworld.multitenant.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codingworld.multitenant.bean.Funcionario;
import com.codingworld.multitenant.service.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Funcionario city) {
        funcService.save(city);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Funcionario>> getAll() throws SQLException {
        List<Funcionario> cities = funcService.getAll();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Funcionario> get(@PathVariable(value = "id") Long id) {
        Funcionario city = funcService.get(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<Funcionario> get(@PathVariable(value = "name") String name) {
        Funcionario city = funcService.getByName(name);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<Funcionario> delete(@PathVariable(value = "name") String name) {
        funcService.delete(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
