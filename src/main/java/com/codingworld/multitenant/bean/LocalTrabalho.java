package com.codingworld.multitenant.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "LOCAL_TRABALHO")
@JsonIgnoreProperties(ignoreUnknown = true)
// @JsonInclude(JsonInclude.Include.NON_NULL)
public class LocalTrabalho {
    private static final long serialVersionUID = -4551953276601557391L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_localtrabalho;
    private String local_trabalho;
    private String tipo_mao_obra_di;
    private String cod_auxiliar;
    private String padrao;

    public Integer getId_localtrabalho() {
        return id_localtrabalho;
    }
    public void setId_localtrabalho(Integer id_localtrabalho) {
        this.id_localtrabalho = id_localtrabalho;
    }
    public String getLocal_trabalho() {
        return local_trabalho;
    }
    public void setLocal_trabalho(String local_trabalho) {
        this.local_trabalho = local_trabalho;
    }
    public String getTipo_mao_obra_di() {
        return tipo_mao_obra_di;
    }
    public void setTipo_mao_obra_di(String tipo_mao_obra_di) {
        this.tipo_mao_obra_di = tipo_mao_obra_di;
    }
    public String getCod_auxiliar() {
        return cod_auxiliar;
    }
    public void setCod_auxiliar(String cod_auxiliar) {
        this.cod_auxiliar = cod_auxiliar;
    }
    public String getPadrao() {
        return padrao;
    }
    public void setPadrao(String padrao) {
        this.padrao = padrao;
    }
}
