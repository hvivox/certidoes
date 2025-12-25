package com.hvivox.certidoes.domain;

import java.io.Serializable;

public class Certidao implements Serializable {
    private Long id;
    private String numero;
    private CertidaoTipo tipo;
    private String interessado;
    private String dataEmissao; // dd/MM/yyyy (simples pra começar)
    private CertidaoStatus status;

    public Certidao() {}

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public CertidaoTipo getTipo() { return tipo; }
    public void setTipo(CertidaoTipo tipo) { this.tipo = tipo; }

    public String getInteressado() { return interessado; }
    public void setInteressado(String interessado) { this.interessado = interessado; }

    public String getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(String dataEmissao) { this.dataEmissao = dataEmissao; }

    public CertidaoStatus getStatus() { return status; }
    public void setStatus(CertidaoStatus status) { this.status = status; }
}
