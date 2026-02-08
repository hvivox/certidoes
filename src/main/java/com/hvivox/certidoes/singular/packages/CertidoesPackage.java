package com.hvivox.certidoes.singular.packages;

import org.opensingular.form.SInfoPackage;
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
@SInfoPackage(name = "certidoes")
public class CertidoesPackage extends SPackage {

    /**
     * Construtor padrão do pacote
     * O nome do pacote é definido pela anotação @SInfoPackage
     */
    public CertidoesPackage() {
        super();
    }
}
