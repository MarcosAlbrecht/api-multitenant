package com.codingworld.multitenant.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codingworld.multitenant.bean.Acessos;
import com.codingworld.multitenant.bean.Mobile;

public interface MobileRepository extends JpaRepository<Mobile, Integer> {
    List<Mobile> findByAcessosEmpresa(String empresa);    
    

}
