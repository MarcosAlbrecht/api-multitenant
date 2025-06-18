package com.codingworld.multitenant.config;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "ACESSOS")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataSourceConfig implements Serializable {
    private static final long serialVersionUID = 5104181924076372196L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String chave;
    private String server;
    private String database;
    private String idn_bionotifica;
    private String ativo;


    public String getChave() {
        return chave;
    }


    public void setChave(String chave) {
        this.chave = chave;
    }


    public String getServer() {
        return server;
    }


    public void setServer(String server) {
        this.server = server;
    }


    public String getDatabase() {
        return database;
    }


    public void setDatabase(String database) {
        this.database = database;
    }


    public String getIdn_bionotifica() {
        return idn_bionotifica;
    }


    public void setIdn_bionotifica(String idn_bionotifica) {
        this.idn_bionotifica = idn_bionotifica;
    }


    public String getAtivo() {
        return ativo;
    }


    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

}
