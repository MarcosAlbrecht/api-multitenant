package com.codingworld.multitenant.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import com.codingworld.multitenant.repo.DataSourceConfigRepository;

@Component
public class TenantDataSource implements Serializable {

    private HashMap<String, DataSource> dataSources = new HashMap<>();

    @Autowired
    private DataSourceConfigRepository configRepo;

    public DataSource getDataSource(String name) {
        if (dataSources.get(name) != null) {
            return dataSources.get(name);
        }
        DataSource dataSource = createDataSource(name);
        if (dataSource != null) {
            dataSources.put(name, dataSource);
        }
        return dataSource;
    }

    @PostConstruct
    public Map<String, DataSource> getAll() {
        List<DataSourceConfig> configList = configRepo.findByActive();
        Map<String, DataSource> result = new HashMap<>();
        for (DataSourceConfig config : configList) {
            DataSource dataSource = getDataSource(config.getChave());
            result.put(config.getChave(), dataSource);
        }
        return result;
    }

    public Map<String, DataSource> getAllConected() {
        return dataSources;
    }

    private DataSource createDataSource(String chave) {
        DataSourceConfig config = configRepo.findByChave(chave);
        if (config != null) {
            String url = "jdbc:firebirdsql:" + config.getServer() + "/3050:" + config.getDatabase()+"?encoding=ISO8859_1";
            DataSourceBuilder<?> factory = DataSourceBuilder.create();
            factory.driverClassName("org.firebirdsql.jdbc.FBDriver"); // fixo
            factory.username("SYSDBA");
            factory.password("masterkey");
            factory.url(url);
            
            return factory.build();
        }
        return null;
    }

}
