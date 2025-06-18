package com.codingworld.multitenant.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingworld.multitenant.config.TenantDataSource;

@Service
public class ContadorService {

    @Autowired
    private TenantDataSource tenantDataSource;

    public Map<String, Object> contarRegistrosEmTodosBancosParalelo(int pagina, int tamanhoPagina) {
        Map<String, DataSource> dataSources = tenantDataSource.getAllConected();
        List<Map.Entry<String, DataSource>> lista = new ArrayList<>(dataSources.entrySet());

        int inicio = (pagina - 1) * tamanhoPagina;
        int fim = Math.min(inicio + tamanhoPagina, lista.size());

        ExecutorService executor = Executors.newFixedThreadPool(50);
        List<Future<Map.Entry<String, Integer>>> futures = new ArrayList<>();
        List<String> falhas = Collections.synchronizedList(new ArrayList<>());

        for (Map.Entry<String, DataSource> entry : lista.subList(inicio, fim)) {
            String canal = entry.getKey();
            DataSource ds = entry.getValue();

            futures.add(executor.submit(() -> {
                long start = System.currentTimeMillis();
                int total = 0;
                try (Connection conn = ds.getConnection();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM FUNCIONARIO WHERE rescisao IS NULL")) {

                    if (rs.next()) {
                        total = rs.getInt("total");
                    }
                    long elapsed = System.currentTimeMillis() - start;
                    System.out.println("Canal " + canal + " retornou " + total + " em " + elapsed + "ms");

                } catch (SQLException e) {
                    falhas.add(canal);
                    System.err.println("Erro no canal " + canal + ": " + e.getMessage());
                }

                return Map.entry(canal, total);
            }));
        }

        Map<String, Integer> porCanal = new HashMap<>();
        int totalGeral = 0;

        for (Future<Map.Entry<String, Integer>> future : futures) {
            try {
                Map.Entry<String, Integer> result = future.get();
                porCanal.put(result.getKey(), result.getValue());
                totalGeral += result.getValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        Map<String, Object> response = new HashMap<>();
        response.put("pagina", pagina);
        response.put("inicio", inicio);
        response.put("totalGeral", totalGeral);
        response.put("fim", fim);   
        response.put("porCanal", porCanal);
        response.put("falhas", falhas);
        return response;
    }


}

