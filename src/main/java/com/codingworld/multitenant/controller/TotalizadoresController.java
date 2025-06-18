package com.codingworld.multitenant.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codingworld.multitenant.service.ContadorService;

@RestController
@RequestMapping("/totalizadores")
public class TotalizadoresController {
    @Autowired
    private ContadorService contadorService;

    @GetMapping("/count/funcionario")
    public ResponseEntity<Map<String, Object>> contarTodos(
        @RequestParam(defaultValue = "1") int pagina,
        @RequestParam(defaultValue = "100") int tamanhoPagina) {

        Map<String, Object> resultado = contadorService.contarRegistrosEmTodosBancosParalelo(pagina, tamanhoPagina);
        return ResponseEntity.ok(resultado);
    }
}
