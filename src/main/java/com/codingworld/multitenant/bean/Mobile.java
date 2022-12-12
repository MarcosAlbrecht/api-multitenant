package com.codingworld.multitenant.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "MOBILE")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mobile implements Serializable {

    @Id
    private Integer id;
    private String chave;
    private String nome;
    private String so;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chave", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    private Acessos acessos;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChave() {
        return this.chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSo() {
        return this.so;
    }

    public void setSo(String so) {
        this.so = so;
    }    

    public Acessos getAcessos() {
        return this.acessos;
    }

    public void setAcessos(Acessos acessos) {
        this.acessos = acessos;
    }
    
}
