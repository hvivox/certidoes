package com.hvivox.certidoes.page;

import com.hvivox.certidoes.BasePage;
import com.hvivox.certidoes.domain.Certidao;
import com.hvivox.certidoes.domain.CertidaoStatus;
import com.hvivox.certidoes.domain.CertidaoTipo;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * ====================================================================
 * PÁGINA DE DEMONSTRAÇÃO DE MODELOS (MODELS) - MÓDULO 3: MODELOS
 * ====================================================================
 * 
 * Esta página demonstra os conceitos de Models no Apache Wicket:
 * 
 * ITEM 1: Revisão de Models
 * - O que são Models
 * - Interface IModel<T>
 * - Por que usar Models
 * - Tipos de Models
 * 
 * ITEM 2: Padrões (Standards)
 * - Model.of() - Modelo simples
 * - PropertyModel - Acessar propriedades
 * - CompoundPropertyModel - Bind automático
 * - Boas práticas
 * - Quando usar cada padrão
 * 
 * OBJETIVO:
 * - Demonstrar conceitos de Models de forma prática
 * - Mostrar exemplos de uso de cada tipo de Model
 * - Servir como referência para desenvolvimento
 * 
 * ACESSE: /modelos-demo
 */
public class ModelosDemoPage extends BasePage {
    private static final long serialVersionUID = 1L;

    // Objeto de exemplo para demonstrar Models
    private Certidao certidaoExemplo;

    // Campos para demonstração de CompoundPropertyModel
    private String nomeExemplo = "";
    private String emailExemplo = "";
    private Integer idadeExemplo = 0;

    public ModelosDemoPage() {
        super();

        // Criar certidão de exemplo
        criarCertidaoExemplo();

        // ============================================================
        // TÍTULO DA PÁGINA
        // ============================================================
        add(new Label("tituloDemoPage", "Demonstração Prática de Models (Módulo 3)"));
        add(new Label("subtitulo", 
                "Esta página demonstra exemplos práticos de uso de Models. " +
                "A documentação completa está disponível na página 'Componentes Wicket' no menu de Documentações."));

        // ============================================================
        // EXEMPLOS PRÁTICOS: MODEL.OF()
        // ============================================================
        add(new Label("exemploModelOfTitulo", "Exemplos Práticos: Model.of()"));

        // Exemplo 1: String simples
        add(new Label("exemploString", Model.of("Este é um texto estático")));

        // Exemplo 2: Número
        add(new Label("exemploNumero", Model.of(42)));

        // Exemplo 3: Valor calculado
        int soma = 10 + 20;
        add(new Label("exemploSoma", Model.of("10 + 20 = " + soma)));

        // Exemplo 4: Valor de propriedade (extraído do objeto)
        add(new Label("exemploPropriedadeExtraida", 
                Model.of(certidaoExemplo.getNumero())));

        // ============================================================
        // EXEMPLOS PRÁTICOS: PROPERTYMODEL
        // ============================================================
        add(new Label("exemploPropertyModelTitulo", "Exemplos Práticos: PropertyModel"));

        // Exemplo 1: Propriedade simples
        add(new Label("exemploPropertyNumero", 
                new PropertyModel<>(certidaoExemplo, "numero")));

        // Exemplo 2: Propriedade aninhada (se houver)
        add(new Label("exemploPropertyInteressado", 
                new PropertyModel<>(certidaoExemplo, "interessado")));

        // Exemplo 3: Propriedade de enum
        add(new Label("exemploPropertyTipo", 
                new PropertyModel<>(certidaoExemplo, "tipo")));

        // Exemplo 4: PropertyModel dinâmico (atualiza quando objeto muda)
        Label labelDinamico = new Label("exemploPropertyDinamico", 
                new PropertyModel<>(certidaoExemplo, "numero"));
        add(labelDinamico);

        // ============================================================
        // EXEMPLOS PRÁTICOS: COMPOUNDPROPERTYMODEL
        // ============================================================
        add(new Label("exemploCompoundTitulo", "Exemplos Práticos: CompoundPropertyModel"));

        // Criar formulário com CompoundPropertyModel
        // O Model é aplicado ao formulário, e os campos se vinculam automaticamente
        Form<ModelosDemoPage> formExemplo = new Form<ModelosDemoPage>("formExemplo", 
                new CompoundPropertyModel<>(this)) {
            @Override
            protected void onSubmit() {
                // Os valores já foram vinculados automaticamente aos campos
                getSession().info("Nome: " + nomeExemplo);
                getSession().info("Email: " + emailExemplo);
                getSession().info("Idade: " + idadeExemplo);
            }
        };
        add(formExemplo);

        // Campos do formulário - bind automático pelo wicket:id
        formExemplo.add(new TextField<>("nomeExemplo")); // Vincula a this.nomeExemplo
        formExemplo.add(new TextField<>("emailExemplo")); // Vincula a this.emailExemplo
        formExemplo.add(new TextField<>("idadeExemplo")); // Vincula a this.idadeExemplo

        // Labels para mostrar os valores (também com bind automático)
        formExemplo.add(new Label("labelNome", new PropertyModel<>(this, "nomeExemplo")));
        formExemplo.add(new Label("labelEmail", new PropertyModel<>(this, "emailExemplo")));
        formExemplo.add(new Label("labelIdade", new PropertyModel<>(this, "idadeExemplo")));

        // ============================================================
        // COMPARAÇÃO: MODEL.OF() VS PROPERTYMODEL
        // ============================================================
        add(new Label("comparacaoTitulo", "Comparação: Model.of() vs PropertyModel"));

        // Model.of() - valor estático (não atualiza se objeto mudar)
        add(new Label("comparacaoModelOf", 
                Model.of("Número (Model.of): " + certidaoExemplo.getNumero())));

        // PropertyModel - valor dinâmico (atualiza se objeto mudar)
        add(new Label("comparacaoPropertyModel", 
                new PropertyModel<>(certidaoExemplo, "numero")));

        // ============================================================
        // DEMONSTRAÇÃO: MODEL COM OBJETO COMPLETO
        // ============================================================
        add(new Label("modeloCompletoTitulo", "Model com Objeto Completo"));

        // Criar Model com objeto completo
        IModel<Certidao> certidaoModel = Model.of(certidaoExemplo);

        // Usar PropertyModel para acessar propriedades do Model
        add(new Label("modeloCompletoNumero", 
                new PropertyModel<>(certidaoModel, "numero")));
        add(new Label("modeloCompletoInteressado", 
                new PropertyModel<>(certidaoModel, "interessado")));
        add(new Label("modeloCompletoTipo", 
                new PropertyModel<>(certidaoModel, "tipo")));
    }

    /**
     * Cria uma certidão de exemplo para demonstração
     */
    private void criarCertidaoExemplo() {
        certidaoExemplo = new Certidao();
        certidaoExemplo.setId(999L);
        certidaoExemplo.setNumero("DEMO-001/2024");
        certidaoExemplo.setTipo(CertidaoTipo.POSITIVA);
        certidaoExemplo.setInteressado("João da Silva - Exemplo");
        certidaoExemplo.setDataEmissao("01/01/2024");
        certidaoExemplo.setStatus(CertidaoStatus.EMITIDA);
    }

    // Getters e Setters para CompoundPropertyModel
    public String getNomeExemplo() {
        return nomeExemplo;
    }

    public void setNomeExemplo(String nomeExemplo) {
        this.nomeExemplo = nomeExemplo;
    }

    public String getEmailExemplo() {
        return emailExemplo;
    }

    public void setEmailExemplo(String emailExemplo) {
        this.emailExemplo = emailExemplo;
    }

    public Integer getIdadeExemplo() {
        return idadeExemplo;
    }

    public void setIdadeExemplo(Integer idadeExemplo) {
        this.idadeExemplo = idadeExemplo;
    }
}

