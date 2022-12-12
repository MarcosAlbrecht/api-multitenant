package com.codingworld.multitenant.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codingworld.multitenant.bean.Acessos;
import com.codingworld.multitenant.bean.Mobile;
import com.codingworld.multitenant.service.MobileService;

@RestController
public class MobileController {

    @Autowired
    private MobileService mobileService;

    @RequestMapping(value = "/getmobile/all", method = RequestMethod.GET )
    public ResponseEntity<List<Mobile>> getAll() throws SQLException{
        List<Mobile> mobile = mobileService.getAll();
        return new ResponseEntity<>(mobile, HttpStatus.OK);
    }

    @RequestMapping(value = "/getmobilebyempresa/{empresa}", method = RequestMethod.GET )
    public ResponseEntity<List<Mobile>> getMobileJoin(@PathVariable(value = "empresa") String empresa) throws SQLException{
        List<Mobile> mobile = mobileService.getByEmpresa(empresa);
        return new ResponseEntity<>(mobile, HttpStatus.OK);
    }
    
}
