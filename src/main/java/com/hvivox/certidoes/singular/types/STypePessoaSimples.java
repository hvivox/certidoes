package com.hvivox.certidoes.singular.types;

import com.hvivox.certidoes.singular.packages.CertidoesPackage;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;

import javax.annotation.Nonnull;

/**
 * Módulo 1 - Tarefa 3: Primeiro SType simples (PessoaSimples)
 * 
 * Este é o primeiro exemplo prático de criação de um SType no OpenSingular.
 * 
 * CONCEITOS DEMONSTRADOS:
 * - STypeComposite: Tipo que agrupa múltiplos campos
 * - STypeString: Tipo simples para representar texto
 * - TypeBuilder: Builder para construção fluente de tipos
 * - onLoadType: Método chamado durante inicialização do tipo
 * - Atributos: label, required, maxLength
 * - @SInfoType: Anotação obrigatória para identificar o tipo no OpenSingular
 * 
 * ESTRUTURA:
 * - nome: String obrigatório, máximo 100 caracteres
 * - email: String obrigatório, máximo 255 caracteres
 * 
 * USO:
 * Este tipo pode ser usado para criar instâncias (SInstance) que representam
 * pessoas simples com apenas nome e email.
 * 
 * @author OpenSingular Implementation
 * @version 1.0
 */
@SInfoType(spackage = CertidoesPackage.class, name = "PessoaSimples")
public class STypePessoaSimples extends STypeComposite {

    /**
     * Campo nome - texto obrigatório, máximo 100 caracteres
     */
    public STypeString nome;

    /**
     * Campo email - texto obrigatório, máximo 255 caracteres
     */
    public STypeString email;

    /**
     * Método chamado durante a inicialização do tipo.
     * 
     * Aqui definimos todos os campos que compõem este tipo usando o TypeBuilder.
     * O TypeBuilder fornece métodos fluentes para adicionar campos e configurar atributos.
     * 
     * @param tb TypeBuilder usado para construir o tipo
     */
    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        // Adiciona campo "nome" do tipo String
        nome = addFieldString("nome");
        nome.asAtr().label("Nome");
        nome.asAtr().required();
        nome.asAtr().maxLength(100);
        nome.asAtr().help("Digite o nome completo");

        // Adiciona campo "email" do tipo String
        email = addFieldString("email");
        email.asAtr().label("E-mail");
        email.asAtr().required();
        email.asAtr().maxLength(255);
        email.asAtr().help("Digite um e-mail válido");
    }
}
