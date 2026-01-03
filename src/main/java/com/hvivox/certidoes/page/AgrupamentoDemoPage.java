package com.hvivox.certidoes.page;

import com.hvivox.certidoes.BasePage;
import com.hvivox.certidoes.documentacao.AgrupamentoDocumentacao;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.Arrays;
import java.util.List;

/**
 * ====================================================================
 * PÁGINA DE DEMONSTRAÇÃO: AGRUPAMENTO DE COMPONENTES
 * ====================================================================
 * 
 * Esta página demonstra técnicas de agrupamento de componentes no Wicket.
 * 
 * MÓDULO 5: COMPOSIÇÃO DE PÁGINAS E COMPONENTES
 * 
 * EXEMPLOS DEMONSTRADOS:
 * 1. WebMarkupContainer - Agrupamento básico
 * 2. Form - Agrupamento de campos de formulário
 * 3. ListView - Agrupamento repetitivo
 * 4. Controle de visibilidade de grupos
 * 5. Hierarquia de componentes
 * 
 * ACESSE: /agrupamento-demo
 */
public class AgrupamentoDemoPage extends BasePage {
    private static final long serialVersionUID = 1L;

    // Campos do formulário
    private String nome = "";
    private String email = "";
    private String telefone = "";
    private String rua = "";
    private String cidade = "";
    private String cep = "";

    // Lista de exemplo
    private List<ItemExemplo> itens = Arrays.asList(
            new ItemExemplo("Item 1", "Descrição do item 1", "Ativo"),
            new ItemExemplo("Item 2", "Descrição do item 2", "Inativo"),
            new ItemExemplo("Item 3", "Descrição do item 3", "Ativo"),
            new ItemExemplo("Item 4", "Descrição do item 4", "Pendente"));

    public AgrupamentoDemoPage(PageParameters parameters) {
        super();

        // ============================================================
        // DOCUMENTAÇÃO
        // ============================================================
        Label docLabel = new Label("documentacao", AgrupamentoDocumentacao.getConteudo());
        docLabel.setEscapeModelStrings(false);
        add(docLabel);

        // ============================================================
        // EXEMPLO 1: WebMarkupContainer - Agrupamento Básico
        // ============================================================

        // Container para dados pessoais
        WebMarkupContainer dadosPessoaisContainer = new WebMarkupContainer("dadosPessoaisContainer");
        dadosPessoaisContainer.setOutputMarkupId(true);
        dadosPessoaisContainer.setOutputMarkupPlaceholderTag(true);
        add(dadosPessoaisContainer);

        // Adicionar campos ao container
        dadosPessoaisContainer.add(new Label("labelDadosPessoais", "Dados Pessoais"));
        dadosPessoaisContainer.add(new Label("infoDadosPessoais",
                "Este grupo contém campos relacionados a dados pessoais"));

        // Container para endereço
        WebMarkupContainer enderecoContainer = new WebMarkupContainer("enderecoContainer");
        enderecoContainer.setOutputMarkupId(true);
        enderecoContainer.setOutputMarkupPlaceholderTag(true);
        add(enderecoContainer);

        enderecoContainer.add(new Label("labelEndereco", "Endereço"));
        enderecoContainer.add(new Label("infoEndereco",
                "Este grupo contém campos relacionados ao endereço"));

        // Links para alternar visibilidade
        add(new AjaxLink<Void>("toggleDadosPessoais") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                dadosPessoaisContainer.setVisible(!dadosPessoaisContainer.isVisible());
                target.add(dadosPessoaisContainer);
                info("Visibilidade de 'Dados Pessoais' alternada!");
                target.add(getPage().get("feedback"));
            }
        });

        add(new AjaxLink<Void>("toggleEndereco") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                enderecoContainer.setVisible(!enderecoContainer.isVisible());
                target.add(enderecoContainer);
                info("Visibilidade de 'Endereço' alternada!");
                target.add(getPage().get("feedback"));
            }
        });

        // ============================================================
        // EXEMPLO 2: Form - Agrupamento de Campos
        // ============================================================

        Form<Void> formAgrupado = new Form<Void>("formAgrupado") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit() {
                success("Formulário submetido com sucesso!");
                success(String.format("Nome: %s, Email: %s, Telefone: %s", nome, email, telefone));
                success(String.format("Rua: %s, Cidade: %s, CEP: %s", rua, cidade, cep));
            }
        };
        add(formAgrupado);

        // Grupo 1: Dados Pessoais (dentro do formulário)
        WebMarkupContainer grupoDadosPessoais = new WebMarkupContainer("grupoDadosPessoais");
        formAgrupado.add(grupoDadosPessoais);

        grupoDadosPessoais.add(new TextField<>("nome", new PropertyModel<>(this, "nome")));
        grupoDadosPessoais.add(new TextField<>("email", new PropertyModel<>(this, "email")));
        grupoDadosPessoais.add(new TextField<>("telefone", new PropertyModel<>(this, "telefone")));

        // Grupo 2: Endereço (dentro do formulário)
        WebMarkupContainer grupoEndereco = new WebMarkupContainer("grupoEndereco");
        formAgrupado.add(grupoEndereco);

        grupoEndereco.add(new TextField<>("rua", new PropertyModel<>(this, "rua")));
        grupoEndereco.add(new TextField<>("cidade", new PropertyModel<>(this, "cidade")));
        grupoEndereco.add(new TextField<>("cep", new PropertyModel<>(this, "cep")));

        // Botão de submissão
        formAgrupado.add(new Button("submit"));

        // ============================================================
        // EXEMPLO 3: ListView - Agrupamento Repetitivo
        // ============================================================

        ListView<ItemExemplo> listView = new ListView<ItemExemplo>("itensLista", itens) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<ItemExemplo> item) {
                ItemExemplo itemExemplo = item.getModelObject();

                // Agrupar componentes para cada item
                item.add(new Label("itemNome", itemExemplo.getNome()));
                item.add(new Label("itemDescricao", itemExemplo.getDescricao()));
                item.add(new Label("itemStatus", itemExemplo.getStatus()));

                // Adicionar classe CSS baseada no status à linha <tr>
                String cssClass = "";
                switch (itemExemplo.getStatus()) {
                    case "Ativo":
                        cssClass = "table-success";
                        break;
                    case "Inativo":
                        cssClass = "table-danger";
                        break;
                    case "Pendente":
                        cssClass = "table-warning";
                        break;
                }

                // Adicionar classe CSS à linha usando AttributeModifier
                if (!cssClass.isEmpty()) {
                    item.add(org.apache.wicket.AttributeModifier.append("class", cssClass));
                }
            }
        };
        add(listView);

        // ============================================================
        // EXEMPLO 4: Hierarquia de Componentes
        // ============================================================

        // Nível 1: Container principal
        WebMarkupContainer hierarchyContainer = new WebMarkupContainer("hierarchyContainer");
        add(hierarchyContainer);

        // Nível 2: Sub-containers
        WebMarkupContainer nivel2a = new WebMarkupContainer("nivel2a");
        WebMarkupContainer nivel2b = new WebMarkupContainer("nivel2b");
        hierarchyContainer.add(nivel2a);
        hierarchyContainer.add(nivel2b);

        // Nível 3: Componentes finais
        nivel2a.add(new Label("componenteA1", "Componente A1"));
        nivel2a.add(new Label("componenteA2", "Componente A2"));

        nivel2b.add(new Label("componenteB1", "Componente B1"));
        nivel2b.add(new Label("componenteB2", "Componente B2"));
    }

    /**
     * Classe auxiliar para demonstração
     * 
     * IMPORTANTE: Deve implementar Serializable pois será armazenada
     * no componente ListView e o Wicket serializa as páginas entre requisições.
     */
    public static class ItemExemplo implements java.io.Serializable {
        private static final long serialVersionUID = 1L;

        private String nome;
        private String descricao;
        private String status;

        public ItemExemplo(String nome, String descricao, String status) {
            this.nome = nome;
            this.descricao = descricao;
            this.status = status;
        }

        public String getNome() {
            return nome;
        }

        public String getDescricao() {
            return descricao;
        }

        public String getStatus() {
            return status;
        }
    }
}
