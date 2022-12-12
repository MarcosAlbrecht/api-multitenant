package com.codingworld.multitenant.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingworld.multitenant.bean.Acessos;
import com.codingworld.multitenant.bean.Mobile;
import com.codingworld.multitenant.repo.MobileRepository;

@Service
public class MobileService {

    @Autowired
    private MobileRepository mobileRepository;

    public List<Mobile> getAll() throws SQLException {
        return mobileRepository.findAll();

    }

    public List<Mobile> getByEmpresa(String empresa) throws SQLException {
        return mobileRepository.findByAcessosEmpresa(empresa);

    }
    
}
