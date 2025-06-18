package com.codingworld.multitenant.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingworld.multitenant.bean.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {

    Funcionario findByNome(String nome);

    void deleteByNome(String nome);
}
