package com.hvivox.certidoes.domain;

/**
 * ====================================================================
 * ENUM: TIPO DE CERTIDÃO
 * ====================================================================
 * 
 * Refatorado seguindo boas práticas (como anexos 05/06):
 * - Propriedades e construtores no enum
 * - Métodos implementados dentro do enum
 * - Centraliza lógica de negócio
 * - Elimina boilerplate de formatação
 */
public enum CertidaoTipo {
    NEGATIVA("Negativa"),
    POSITIVA("Positiva"),
    POSITIVA_COM_EFEITO_DE_NEGATIVA("Positiva com Efeito de Negativa");

    /**
     * Descrição formatada do tipo de certidão
     */
    private final String descricao;

    /**
     * Construtor do enum.
     * 
     * @param descricao Descrição formatada para exibição
     */
    CertidaoTipo(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Obtém a descrição formatada do tipo de certidão.
     * 
     * EXEMPLO DE USO:
     * CertidaoTipo tipo = CertidaoTipo.POSITIVA;
     * String descricao = tipo.getDescricao(); // "Positiva"
     * 
     * @return Descrição formatada (ex: "Negativa", "Positiva", etc.)
     */
    public String getDescricao() {
        return descricao;
    }
}
