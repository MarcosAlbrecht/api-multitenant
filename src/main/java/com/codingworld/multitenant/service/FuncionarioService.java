package com.codingworld.multitenant.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingworld.multitenant.bean.Funcionario;
import com.codingworld.multitenant.repo.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcRepository;

    public void save(Funcionario func) {
        funcRepository.save(func);
    }

    public List<Funcionario> getAll() throws SQLException {
        return funcRepository.findAll();

    }

    public Funcionario get(Long id) {
        return funcRepository.findById(id).get();
    }

    public Funcionario getByName(String name) {
        return funcRepository.findByNome(name);
    }

    public void delete(String name) {
        funcRepository.deleteByNome(name);
    }
}
