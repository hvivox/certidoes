package com.hvivox.certidoes.page;

import com.hvivox.certidoes.BasePage;
import com.hvivox.certidoes.HomePage;
import com.hvivox.certidoes.component.CertidaoCard;
import com.hvivox.certidoes.domain.Certidao;
import com.hvivox.certidoes.domain.CertidaoStatus;
import com.hvivox.certidoes.domain.CertidaoTipo;
import com.hvivox.certidoes.infra.CertidaoRepository;
import com.hvivox.certidoes.infra.InMemoryCertidaoRepository;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import java.util.Arrays;
import java.util.List;

/**
 * ====================================================================
 * PÁGINA DE DEMONSTRAÇÃO DE COMPONENTES DO WICKET
 * ====================================================================
 * 
 * Esta página demonstra os principais componentes do Apache Wicket:
 * 
 * 1. LABEL - Exibição de texto
 * 2. TEXTFIELD - Campo de texto
 * 3. TEXTAREA - Área de texto multilinha
 * 4. CHECKBOX - Caixa de seleção
 * 5. RADIOGROUP - Grupo de botões de opção
 * 6. DROPDOWNCHOICE - Lista suspensa
 * 7. LISTVIEW - Lista repetitiva
 * 8. LINK - Links de navegação
 * 9. BUTTON - Botões
 * 10. FEEDBACKPANEL - Mensagens de feedback
 * 11. WEBMARKUPCONTAINER - Container genérico
 * 
 * OBJETIVO:
 * - Demonstrar como usar cada componente
 * - Mostrar exemplos práticos
 * - Servir como referência para desenvolvimento
 * 
 * ACESSE: /componentes-demo
 */
public class ComponentesDemoPage extends BasePage {
    private static final long serialVersionUID = 1L;

    // Modelos para os componentes
    private String textoSimples = "";
    private String email = "";
    private String textoLongo = "";
    private Boolean aceito = false;
    private String opcaoSelecionada = "opcao1";
    private String itemSelecionado = "";
    private Integer contador = 0;

    // Lista de exemplo para ListView
    private List<String> itensLista = Arrays.asList("Item 1", "Item 2", "Item 3", "Item 4", "Item 5");

    public ComponentesDemoPage() {
        super();

        // ============================================================
        // 1. LABEL - Exibição de texto
        // ============================================================
        add(new Label("tituloDemoPage", "Demonstração de Componentes Wicket"));
        add(new Label("subtitulo", "Esta página mostra os principais componentes do Apache Wicket"));

        // ============================================================
        // MÓDULO 2 - ITEM 3: DOCUMENTAÇÕES
        // ============================================================
        // Adicionar conteúdo das documentações sobre Arquitetura do Wicket
        // As documentações estão em classes separadas no pacote 'documentacao'

        // Documentação: Session Store
        Label labelDocSessionStore = new Label("docSessionStore",
                com.hvivox.certidoes.documentacao.SessionStoreDocumentacao.getConteudo());
        labelDocSessionStore.setEscapeModelStrings(false); // Permite HTML
        add(labelDocSessionStore);

        // Documentação: Segurança de Thread
        Label labelDocSegurancaThread = new Label("docSegurancaThread",
                com.hvivox.certidoes.documentacao.SegurancaThreadDocumentacao.getConteudo());
        labelDocSegurancaThread.setEscapeModelStrings(false); // Permite HTML
        add(labelDocSegurancaThread);

        // Documentação: MVC do jeito Wicket
        Label labelDocMVC = new Label("docMVC",
                com.hvivox.certidoes.documentacao.MVCDocumentacao.getConteudo());
        labelDocMVC.setEscapeModelStrings(false); // Permite HTML
        add(labelDocMVC);

        // Documentação: Arquitetura do Wicket
        Label labelDocArquitetura = new Label("docArquitetura",
                com.hvivox.certidoes.documentacao.ArquiteturaDocumentacao.getConteudo());
        labelDocArquitetura.setEscapeModelStrings(false); // Permite HTML
        add(labelDocArquitetura);

        // ============================================================
        // MÓDULO 3: DOCUMENTAÇÕES SOBRE MODELOS
        // ============================================================
        // Documentação: Revisão de Models (Módulo 3 - Item 1)
        Label labelDocRevisaoModels = new Label("docRevisaoModels",
                com.hvivox.certidoes.documentacao.ModelosDocumentacao.getConteudoRevisao());
        labelDocRevisaoModels.setEscapeModelStrings(false); // Permite HTML
        add(labelDocRevisaoModels);

        // Documentação: Padrões de Models (Módulo 3 - Item 2)
        Label labelDocPadroesModels = new Label("docPadroesModels",
                com.hvivox.certidoes.documentacao.ModelosDocumentacao.getConteudoPadroes());
        labelDocPadroesModels.setEscapeModelStrings(false); // Permite HTML
        add(labelDocPadroesModels);

        // Link para página de exemplos práticos de Models (dentro do collapse de
        // documentações)
        add(new BookmarkablePageLink<>("linkModelosDemo",
                com.hvivox.certidoes.page.ModelosDemoPage.class));

        // Label com modelo dinâmico (atualiza automaticamente)
        Label labelDinamico = new Label("labelDinamico", new PropertyModel<>(this, "contador"));
        add(labelDinamico);

        // ============================================================
        // 2. TEXTFIELD - Campo de texto simples com Ajax
        // ============================================================
        // Usando Ajax para atualizar o valor em tempo real sem recarregar a página
        TextField<String> campoTexto = new TextField<>("campoTexto",
                new PropertyModel<>(this, "textoSimples"));
        campoTexto.add(StringValidator.minimumLength(3));
        campoTexto.setOutputMarkupId(true); // Necessário para Ajax

        // Label que mostra o valor digitado
        // IMPORTANTE: setOutputMarkupId(true) é obrigatório para atualização via Ajax
        Label valorDigitado = new Label("valorDigitado",
                new PropertyModel<>(this, "textoSimples"));
        valorDigitado.setOutputMarkupId(true); // Necessário para Ajax identificar o componente

        // AjaxFormComponentUpdatingBehavior processa o valor do campo automaticamente
        // antes de executar o onUpdate, garantindo que o modelo seja atualizado
        campoTexto.add(new AjaxFormComponentUpdatingBehavior("keyup") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                // O valor do campo já foi processado e atualizado no modelo
                // Agora atualizamos o label para mostrar o novo valor
                target.add(valorDigitado);
            }

            @Override
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
                // Em caso de erro de validação, ainda atualiza o label
                target.add(valorDigitado);
            }
        });

        // Também atualiza quando o campo perde o foco (blur)
        campoTexto.add(new AjaxFormComponentUpdatingBehavior("blur") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.add(valorDigitado);
            }

            @Override
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
                target.add(valorDigitado);
            }
        });

        add(campoTexto);
        add(valorDigitado);

        // ============================================================
        // 3. TEXTFIELD COM VALIDAÇÃO DE EMAIL
        // ============================================================
        TextField<String> campoEmail = new TextField<>("campoEmail",
                new PropertyModel<>(this, "email"));
        campoEmail.setRequired(true);
        campoEmail.add(EmailAddressValidator.getInstance());
        add(campoEmail);

        // ============================================================
        // 4. TEXTAREA - Área de texto multilinha
        // ============================================================
        TextArea<String> areaTexto = new TextArea<>("areaTexto",
                new PropertyModel<>(this, "textoLongo"));
        areaTexto.setRequired(false);
        add(areaTexto);

        // ============================================================
        // 5. CHECKBOX - Caixa de seleção
        // ============================================================
        CheckBox checkbox = new CheckBox("checkbox",
                new PropertyModel<>(this, "aceito"));
        add(checkbox);

        Label statusCheckbox = new Label("statusCheckbox",
                new PropertyModel<>(this, "aceito"));
        add(statusCheckbox);

        // ============================================================
        // 6. RADIOGROUP - Grupo de botões de opção
        // ============================================================
        List<String> opcoes = Arrays.asList("Opção 1", "Opção 2", "Opção 3");

        RadioGroup<String> radioGroup = new RadioGroup<>("radioGroup",
                new PropertyModel<>(this, "opcaoSelecionada"));

        // Adicionar cada opção como Radio
        for (int i = 0; i < opcoes.size(); i++) {
            String opcao = "opcao" + (i + 1);
            Radio<String> radio = new Radio<>(opcao, Model.of(opcao));
            radio.setLabel(Model.of(opcoes.get(i)));
            radioGroup.add(radio);
        }

        add(radioGroup);

        Label opcaoSelecionadaLabel = new Label("opcaoSelecionadaLabel",
                new PropertyModel<>(this, "opcaoSelecionada"));
        add(opcaoSelecionadaLabel);

        // ============================================================
        // 7. DROPDOWNCHOICE - Lista suspensa
        // ============================================================
        List<String> opcoesDropdown = Arrays.asList("Selecione...", "Java", "Python", "JavaScript", "C++", "Go");
        DropDownChoice<String> dropdown = new DropDownChoice<>("dropdown",
                new PropertyModel<>(this, "itemSelecionado"),
                opcoesDropdown);
        dropdown.setNullValid(true);
        add(dropdown);

        Label itemSelecionadoLabel = new Label("itemSelecionadoLabel",
                new PropertyModel<>(this, "itemSelecionado"));
        add(itemSelecionadoLabel);

        // ============================================================
        // 8. LISTVIEW - Lista repetitiva
        // ============================================================
        ListView<String> listView = new ListView<String>("listaItens", itensLista) {
            @Override
            protected void populateItem(ListItem<String> item) {
                String valor = item.getModelObject();
                item.add(new Label("item", valor));
                item.add(new Label("indice", item.getIndex() + 1));
            }
        };
        add(listView);

        // ============================================================
        // 9. LINKS - Diferentes tipos de links
        // ============================================================

        // BookmarkablePageLink - Link para outra página (URL amigável)
        add(new BookmarkablePageLink<>("linkAmigavelHome", HomePage.class));

        // ExternalLink - Link para URL externa
        add(new ExternalLink("linkExterno", "https://wicket.apache.org", "Site do Wicket"));

        // Link com ação customizada
        Link<Void> linkAcao = new Link<Void>("linkAcao") {
            @Override
            public void onClick() {
                contador++;
                getSession().info("Contador incrementado! Valor: " + contador);
            }
        };
        add(linkAcao);

        // ============================================================
        // 10. FORM - Formulário com validação
        // ============================================================
        Form<Void> formDemo = new Form<Void>("formDemo") {
            @Override
            protected void onSubmit() {
                // Validação automática acontece antes deste método
                getSession().success("Formulário submetido com sucesso!");
                getSession().info("Texto: " + textoSimples);
                getSession().info("Email: " + email);
                getSession().info("Checkbox: " + (aceito ? "Marcado" : "Desmarcado"));
            }
        };
        add(formDemo);

        // Adicionar campos ao formulário
        TextField<String> formCampoTexto = new TextField<>("formCampoTexto",
                new PropertyModel<>(this, "textoSimples"));
        formCampoTexto.setRequired(true);
        formCampoTexto.add(StringValidator.minimumLength(5));
        formDemo.add(formCampoTexto);

        Button botaoSubmit = new Button("botaoSubmit");
        formDemo.add(botaoSubmit);

        // ============================================================
        // 11. WEBMARKUPCONTAINER - Container genérico
        // ============================================================
        WebMarkupContainer container = new WebMarkupContainer("container");
        container.setVisible(contador > 0); // Visível apenas se contador > 0
        container.add(new Label("mensagemContainer",
                "Este container só aparece quando o contador é maior que 0!"));
        add(container);

        // ============================================================
        // COMPONENTE CUSTOMIZADO - CertidaoCard (Demonstra Tríade do Wicket)
        // ============================================================
        // 1. COMPONENT: CertidaoCard.java (classe Java)
        // 2. MARKUP: CertidaoCard.html (arquivo HTML)
        // 3. MODEL: IModel<Certidao> (fornece os dados)

        // IMPORTANTE: Criar o container PRIMEIRO
        // O componente deve ser adicionado ao container, não diretamente à página
        WebMarkupContainer componenteContainer = new WebMarkupContainer("componenteContainer");
        componenteContainer.setVisible(true);

        // Buscar algumas certidões do repositório para demonstrar
        CertidaoRepository repo = new InMemoryCertidaoRepository();
        List<Certidao> certidoes = repo.findAll();

        // Criar o componente CertidaoCard
        CertidaoCard certidaoCard;

        // Se houver certidões, exibir o primeiro como exemplo
        if (!certidoes.isEmpty()) {
            Certidao exemploCertidao = certidoes.get(0);
            certidaoCard = new CertidaoCard("certidaoCardExemplo", Model.of(exemploCertidao));
        } else {
            // Se não houver certidões, criar uma de exemplo
            Certidao exemploCertidao = new Certidao();
            exemploCertidao.setId(999L);
            exemploCertidao.setNumero("12345/2024");
            exemploCertidao.setTipo(CertidaoTipo.POSITIVA);
            exemploCertidao.setInteressado("João da Silva");
            exemploCertidao.setDataEmissao("15/01/2024");
            exemploCertidao.setStatus(CertidaoStatus.EMITIDA);
            certidaoCard = new CertidaoCard("certidaoCardExemplo", Model.of(exemploCertidao));
        }

        // Adicionar o componente AO CONTAINER (não à página diretamente)
        componenteContainer.add(certidaoCard);

        // Agora adicionar o container à página
        add(componenteContainer);

        // ============================================================
        // 14. BEHAVIOR CUSTOMIZADO - ConfirmacaoBehavior
        // ============================================================
        // MÓDULO 2 - ITEM 2: Behavior Customizado
        //
        // Este exemplo demonstra como criar e usar um Behavior customizado:
        // - Behavior adiciona comportamento a componentes sem modificar sua classe
        // - Reutilizável: pode ser usado em qualquer componente (Link, Button, etc.)
        // - Encapsulado: lógica separada do componente

        // Link de exemplo com confirmação customizada
        Link<Void> linkComConfirmacao = new Link<Void>("linkComConfirmacao") {
            @Override
            public void onClick() {
                contador += 10;
                getSession().success("Ação confirmada! Contador incrementado em 10.");
            }
        };
        linkComConfirmacao.add(new com.hvivox.certidoes.behavior.ConfirmacaoBehavior(
                "Tem certeza que deseja incrementar o contador em 10?"));
        add(linkComConfirmacao);

        // Link com mensagem padrão
        Link<Void> linkComConfirmacaoPadrao = new Link<Void>("linkComConfirmacaoPadrao") {
            @Override
            public void onClick() {
                contador = 0;
                getSession().info("Contador resetado!");
            }
        };
        linkComConfirmacaoPadrao.add(new com.hvivox.certidoes.behavior.ConfirmacaoBehavior());
        add(linkComConfirmacaoPadrao);
    }

    // ============================================================
    // MÉTODOS PARA CRIAR DOCUMENTAÇÕES - MÓDULO 2 - ITEM 3
    // ============================================================

    // Getters e Setters para os modelos
    public String getTextoSimples() {
        return textoSimples;
    }

    public void setTextoSimples(String textoSimples) {
        this.textoSimples = textoSimples;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTextoLongo() {
        return textoLongo;
    }

    public void setTextoLongo(String textoLongo) {
        this.textoLongo = textoLongo;
    }

    public Boolean getAceito() {
        return aceito;
    }

    public void setAceito(Boolean aceito) {
        this.aceito = aceito;
    }

    public String getOpcaoSelecionada() {
        return opcaoSelecionada;
    }

    public void setOpcaoSelecionada(String opcaoSelecionada) {
        this.opcaoSelecionada = opcaoSelecionada;
    }

    public String getItemSelecionado() {
        return itemSelecionado;
    }

    public void setItemSelecionado(String itemSelecionado) {
        this.itemSelecionado = itemSelecionado;
    }

    public Integer getContador() {
        return contador;
    }

    public void setContador(Integer contador) {
        this.contador = contador;
    }
}
