package com.codingworld.multitenant.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "FUNCIONARIO")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
//@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "string")})
//@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class Funcionario implements Serializable {
    private static final long serialVersionUID = -4551953276601557391L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_funcionario;
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER) // ou LAZY se quiser carregar sob demanda
    @JoinColumn(name = "ID_LOCALTRABALHO") // nome da coluna FK na tabela funcionario
    
    private LocalTrabalho local_trabalho;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

        
    public LocalTrabalho getLocalTrabalho() {
        return local_trabalho;
    }

    public void setLocalTrabalho(LocalTrabalho local_trabalho) {
        this.local_trabalho = local_trabalho;
    }

    public Integer getId() {
        return id_funcionario;
    }    

    public void setId(Integer id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getName() {
        return nome;
    }

    public void setName(String nome) {
        this.nome = nome;
    }
}
