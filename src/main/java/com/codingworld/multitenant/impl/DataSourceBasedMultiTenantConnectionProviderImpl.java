package com.codingworld.multitenant.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.codingworld.multitenant.config.TenantDataSource;
import com.codingworld.multitenant.exceptions.TenantNotFoundException;

@Component
public class DataSourceBasedMultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
    private static final String DEFAULT_TENANT_ID = "ACESSOS";
    @Autowired
    private DataSource defaultDS;

    @Autowired
    private ApplicationContext context;

    private Map<String, DataSource> map = new HashMap<>();

    boolean init = false;

    @PostConstruct
    public void load() {
        map.put(DEFAULT_TENANT_ID, defaultDS);
    }

    @Override
    protected DataSource selectAnyDataSource() {
        return map.get(DEFAULT_TENANT_ID);
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
    if (!init) {
        init = true;
        TenantDataSource tenantDataSource = context.getBean(TenantDataSource.class);
        map.putAll(tenantDataSource.getAll());
    }

    if (tenantIdentifier == null || tenantIdentifier.isEmpty()) {
        throw new TenantNotFoundException();
    }
    DataSource dataSource;
    if (tenantIdentifier.equals("public")) {
        dataSource = map.get(DEFAULT_TENANT_ID);    
    }else{
        dataSource = map.get(tenantIdentifier);  
    }
    

    if (dataSource == null && !tenantIdentifier.equals("public")) {
        throw new TenantNotFoundException();
    }

    return dataSource;
}
}
