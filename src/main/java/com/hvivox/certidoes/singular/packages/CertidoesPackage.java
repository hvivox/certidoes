package com.hvivox.certidoes.singular.packages;

import org.opensingular.form.SPackage;

/**
 * Módulo 1 - Tarefa 2: Primeiro SPackage
 * 
 * SPackage é um agrupamento lógico de tipos relacionados do OpenSingular.
 * 
 * Este pacote será usado para agrupar todos os tipos relacionados a certidões:
 * - Tipos básicos (Endereco, Pessoa, etc.)
 * - Tipos do domínio (Certidao, Requerente, etc.)
 * 
 * @author OpenSingular Implementation
 * @version 1.0
 */
public class CertidoesPackage extends SPackage {

    /**
     * Nome do pacote (usado para identificação)
     */
    public static final String NAME = "certidoes";

    /**
     * Construtor padrão do pacote
     */
    public CertidoesPackage() {
        super(NAME);
    }
}
