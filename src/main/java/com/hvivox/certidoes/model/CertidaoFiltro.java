package com.hvivox.certidoes.model;

import com.hvivox.certidoes.domain.CertidaoStatus;
import com.hvivox.certidoes.domain.CertidaoTipo;

import java.io.Serializable;
import java.util.Date;

/**
 * MÓDULO 7 - ITEM 58: BUSCA E FILTROS
 * 
 * Modelo que encapsula os critérios de busca e filtros para listagem de certidões.
 * 
 * Este modelo é usado no formulário de busca da CertidaoListPage para permitir
 * que o usuário filtre certidões por diversos critérios.
 */
public class CertidaoFiltro implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Texto de busca livre - busca em número e interessado
     */
    private String textoBusca;
    
    /**
     * Filtro por tipo de certidão
     */
    private CertidaoTipo tipo;
    
    /**
     * Filtro por status da certidão
     */
    private CertidaoStatus status;
    
    /**
     * Filtro por data de emissão - início do período
     */
    private Date dataInicio;
    
    /**
     * Filtro por data de emissão - fim do período
     */
    private Date dataFim;
    
    // ==================== CONSTRUTORES ====================
    
    public CertidaoFiltro() {
        // Construtor padrão
    }
    
    // ==================== GETTERS E SETTERS ====================
    
    public String getTextoBusca() {
        return textoBusca;
    }
    
    public void setTextoBusca(String textoBusca) {
        this.textoBusca = textoBusca;
    }
    
    public CertidaoTipo getTipo() {
        return tipo;
    }
    
    public void setTipo(CertidaoTipo tipo) {
        this.tipo = tipo;
    }
    
    public CertidaoStatus getStatus() {
        return status;
    }
    
    public void setStatus(CertidaoStatus status) {
        this.status = status;
    }
    
    public Date getDataInicio() {
        return dataInicio;
    }
    
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    
    public Date getDataFim() {
        return dataFim;
    }
    
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
    
    // ==================== MÉTODOS UTILITÁRIOS ====================
    
    /**
     * Verifica se algum filtro foi aplicado
     */
    public boolean temFiltroAplicado() {
        return (textoBusca != null && !textoBusca.trim().isEmpty()) ||
               tipo != null ||
               status != null ||
               dataInicio != null ||
               dataFim != null;
    }
    
    /**
     * Limpa todos os filtros
     */
    public void limpar() {
        this.textoBusca = null;
        this.tipo = null;
        this.status = null;
        this.dataInicio = null;
        this.dataFim = null;
    }
    
    @Override
    public String toString() {
        return "CertidaoFiltro{" +
               "textoBusca='" + textoBusca + '\'' +
               ", tipo=" + tipo +
               ", status=" + status +
               ", dataInicio=" + dataInicio +
               ", dataFim=" + dataFim +
               '}';
    }
}

