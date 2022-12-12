package com.codingworld.multitenant.repo;

import com.codingworld.multitenant.bean.Acessos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcessosRepository extends JpaRepository<Acessos, String> {
  

}
