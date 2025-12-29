package com.hvivox.certidoes.domain;

/**
 * ====================================================================
 * ENUM: STATUS DA CERTIDÃO
 * ====================================================================
 *
 */
public enum CertidaoStatus {
    RASCUNHO("Rascunho", "badge-secondary"),
    EMITIDA("Emitida", "badge-success"),
    CANCELADA("Cancelada", "badge-danger");

    /**
     * Descrição formatada do status
     */
    private final String descricao;

    /**
     * Classe CSS do Bootstrap para o badge
     */
    private final String badgeClasse;

    /**
     * Construtor do enum.
     * 
     * @param descricao   Descrição formatada para exibição
     * @param badgeClasse Classe CSS do Bootstrap para o badge (ex: "badge-success")
     */
    CertidaoStatus(String descricao, String badgeClasse) {
        this.descricao = descricao;
        this.badgeClasse = badgeClasse;
    }

    /**
     * Obtém a descrição formatada do status.
     * 
     * EXEMPLO DE USO:
     * CertidaoStatus status = CertidaoStatus.EMITIDA;
     * String descricao = status.getDescricao(); // "Emitida"
     * 
     * @return Descrição formatada (ex: "Rascunho", "Emitida", "Cancelada")
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Obtém a classe CSS do Bootstrap para o badge.
     * 
     * EXEMPLO DE USO:
     * CertidaoStatus status = CertidaoStatus.EMITIDA;
     * String classe = status.getBadgeClasse(); // "badge-success"
     * 
     * @return Classe CSS do badge (ex: "badge-secondary", "badge-success", "badge-danger")
     */
    public String getBadgeClasse() {
        return badgeClasse;
    }

    /**
     * Gera o HTML do badge formatado com Bootstrap.
     * 
     * EXEMPLO DE USO:
     * CertidaoStatus status = CertidaoStatus.EMITIDA;
     * String badgeHtml = status.getBadgeHtml();
     * // Retorna: "<span class='badge badge-success'>Emitida</span>"
     * 
     * @return HTML do badge formatado
     */
    public String getBadgeHtml() {
        return "<span class='badge " + badgeClasse + "'>" + descricao + "</span>";
    }
}
