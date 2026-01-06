package com.hvivox.certidoes.page;

import com.hvivox.certidoes.BasePage;
import com.hvivox.certidoes.domain.Certidao;
import com.hvivox.certidoes.domain.CertidaoStatus;
import com.hvivox.certidoes.domain.CertidaoTipo;
import com.hvivox.certidoes.infra.CertidaoRepository;
import com.hvivox.certidoes.infra.InMemoryCertidaoRepository;
import com.hvivox.certidoes.model.CertidaoFiltro;
import com.hvivox.certidoes.resource.RelatorioDownloadResource;
import com.hvivox.certidoes.service.RelatorioExcelService;
import com.hvivox.certidoes.service.RelatorioWordService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.IResource;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * MÓDULO 7 - ITEM 58: BUSCA E FILTROS NA LISTAGEM DE CERTIDÕES
 * 
 * Página de listagem de certidões com funcionalidade de busca e filtros.
 * 
 * FUNCIONALIDADES:
 * - Busca por texto (número e interessado)
 * - Filtro por tipo de certidão
 * - Filtro por status
 * - Filtro por período de data de emissão
 * - Atualização dinâmica via Ajax (sem recarregar a página)
 * - Limpar filtros
 */
public class CertidaoListPage extends BasePage {
    private static final long serialVersionUID = 1L;

    // Repositório (transient porque não é serializável - Wicket serializa páginas)
    private transient CertidaoRepository repository;

    // Modelo de filtro
    private CertidaoFiltro filtro = new CertidaoFiltro();

    // Container da tabela que será atualizado via Ajax
    private WebMarkupContainer tableContainer;
    private WebMarkupContainer emptyMessage;

    public CertidaoListPage(final PageParameters parameters) {
        super();

        // ==================== FORMULÁRIO DE BUSCA/FILTROS ====================

        Form<CertidaoFiltro> formBusca = new Form<>("formBusca", new CompoundPropertyModel<>(filtro));
        add(formBusca);

        // Campo de busca por texto
        TextField<String> textoBuscaField = new TextField<>("textoBusca");
        textoBuscaField.setLabel(new org.apache.wicket.model.Model<>("Busca"));
        formBusca.add(textoBuscaField);

        // Dropdown de tipo
        DropDownChoice<CertidaoTipo> tipoChoice = new DropDownChoice<CertidaoTipo>(
                "tipo",
                Arrays.asList(CertidaoTipo.values())) {
            private static final long serialVersionUID = 1L;

            @Override
            protected String getNullValidDisplayValue() {
                return "-- Todos os tipos --";
            }

            @Override
            protected boolean localizeDisplayValues() {
                return false;
            }
        };
        tipoChoice.setNullValid(true);
        formBusca.add(tipoChoice);

        // Dropdown de status
        DropDownChoice<CertidaoStatus> statusChoice = new DropDownChoice<CertidaoStatus>(
                "status",
                Arrays.asList(CertidaoStatus.values())) {
            private static final long serialVersionUID = 1L;

            @Override
            protected String getNullValidDisplayValue() {
                return "-- Todos os status --";
            }

            @Override
            protected boolean localizeDisplayValues() {
                return false;
            }
        };
        statusChoice.setNullValid(true);
        formBusca.add(statusChoice);

        // Campos de data
        DateTextField dataInicioField = new DateTextField("dataInicio", "dd/MM/yyyy");
        formBusca.add(dataInicioField);

        DateTextField dataFimField = new DateTextField("dataFim", "dd/MM/yyyy");
        formBusca.add(dataFimField);

        // Botão Buscar (Ajax)
        AjaxButton btnBuscar = new AjaxButton("btnBuscar") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                // Atualizar a lista com os filtros aplicados
                atualizarListaCertidoes();

                // Adicionar feedback
                if (filtro.temFiltroAplicado()) {
                    getSession().info("Filtros aplicados com sucesso!");
                } else {
                    getSession().info("Exibindo todas as certidões.");
                }

                // Atualizar componentes via Ajax
                target.add(tableContainer);
                target.add(emptyMessage);
                target.add(getFeedbackPanel());
            }
        };
        formBusca.add(btnBuscar);

        // Botão Limpar (Ajax)
        AjaxButton btnLimpar = new AjaxButton("btnLimpar") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                // Limpar todos os filtros
                filtro.limpar();

                // Atualizar a lista
                atualizarListaCertidoes();

                getSession().info("Filtros limpos!");

                // Atualizar componentes via Ajax
                target.add(formBusca);
                target.add(tableContainer);
                target.add(emptyMessage);
                target.add(getFeedbackPanel());
            }
        };
        btnLimpar.setDefaultFormProcessing(false); // Não validar o formulário
        formBusca.add(btnLimpar);

        // ==================== TABELA DE RESULTADOS ====================

        // Container para a tabela (para poder atualizar via Ajax)
        tableContainer = new WebMarkupContainer("tableContainer");
        tableContainer.setOutputMarkupId(true);
        add(tableContainer);

        // Mensagem quando não houver dados (deve ser criada ANTES de
        // atualizarListaCertidoes)
        emptyMessage = new WebMarkupContainer("emptyMessage");
        emptyMessage.setOutputMarkupId(true);
        add(emptyMessage);

        // Inicializar a lista (após criar tableContainer e emptyMessage)
        atualizarListaCertidoes();

        // Link "Nova Certidão" no topo
        add(new BookmarkablePageLink<>("linkNovaCertidao", CertidaoFormPage.class));

        // ==================== MÓDULO 7 - ITEM 59: BOTÕES DE EXPORTAÇÃO
        // ====================

        // Botão Exportar para Excel
        add(new ResourceLink<Void>("btnExportarExcel", new IResource() {
            private static final long serialVersionUID = 1L;

            @Override
            public void respond(Attributes attributes) {
                // Buscar certidões com os filtros atuais
                List<Certidao> certidoesParaExportar = getRepository().findByFiltro(filtro);

                // Gerar nome do arquivo com data/hora
                String nomeArquivo = "certidoes_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

                // Criar resource de download
                RelatorioDownloadResource resource = new RelatorioDownloadResource(
                        new RelatorioExcelService(),
                        certidoesParaExportar,
                        nomeArquivo);

                resource.respond(attributes);
            }
        }));

        // Botão Exportar para Word
        add(new ResourceLink<Void>("btnExportarWord", new IResource() {
            private static final long serialVersionUID = 1L;

            @Override
            public void respond(Attributes attributes) {
                // Buscar certidões com os filtros atuais
                List<Certidao> certidoesParaExportar = getRepository().findByFiltro(filtro);

                // Gerar nome do arquivo com data/hora
                String nomeArquivo = "certidoes_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

                // Criar resource de download
                RelatorioDownloadResource resource = new RelatorioDownloadResource(
                        new RelatorioWordService(),
                        certidoesParaExportar,
                        nomeArquivo);

                resource.respond(attributes);
            }
        }));
    }

    /**
     * Atualiza a lista de certidões aplicando os filtros atuais
     */
    private void atualizarListaCertidoes() {
        // Buscar certidões com filtros
        List<Certidao> certidoes = getRepository().findByFiltro(filtro);

        // Atualizar visibilidade dos containers
        tableContainer.setVisible(!certidoes.isEmpty());
        emptyMessage.setVisible(certidoes.isEmpty());

        // Remover ListView anterior (se existir)
        if (tableContainer.get("certidoes") != null) {
            tableContainer.remove("certidoes");
        }

        // ListView para repetir as linhas da tabela
        ListView<Certidao> certidoesList = new ListView<Certidao>("certidoes", certidoes) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<Certidao> item) {
                Certidao certidao = item.getModelObject();

                // Colunas simples
                item.add(new Label("id", certidao.getId()));
                item.add(new Label("numero", certidao.getNumero()));
                // Tipo usando método do enum (refatorado)
                item.add(new Label("tipo",
                        certidao.getTipo() != null ? certidao.getTipo().getDescricao() : ""));
                item.add(new Label("interessado", certidao.getInteressado()));
                item.add(new Label("dataEmissao", certidao.getDataEmissao()));
                // Status usando método do enum (refatorado)
                item.add(new Label("status",
                        certidao.getStatus() != null ? certidao.getStatus().getDescricao() : ""));

                // Links de ação
                PageParameters params = new PageParameters();
                params.add("id", certidao.getId());

                // Link Ver (vamos criar a página no Passo 4)
                BookmarkablePageLink<Void> linkVer = new BookmarkablePageLink<>("linkVer", CertidaoDetailPage.class,
                        params);
                item.add(linkVer);

                // Link Editar (vamos usar a mesma página do formulário)
                BookmarkablePageLink<Void> linkEditar = new BookmarkablePageLink<>("linkEditar", CertidaoFormPage.class,
                        params);
                item.add(linkEditar);

                // Link Excluir com confirmação usando Behavior customizado (MÓDULO 2 - ITEM 2)
                Link<Void> linkExcluir = new Link<Void>("linkExcluir") {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClick() {
                        Long certidaoId = certidao.getId();
                        getRepository().delete(certidaoId);

                        // MÓDULO 2 - ITEM 1: Incrementar contador na Session customizada
                        com.hvivox.certidoes.session.CertidoesSession session = com.hvivox.certidoes.session.CertidoesSession
                                .get();
                        session.incrementarCertidoesExcluidas();

                        getSession().info("Certidão excluída com sucesso!");
                        setResponsePage(CertidaoListPage.class);
                    }
                };
                // Adicionar o Behavior de confirmação
                linkExcluir.add(new com.hvivox.certidoes.behavior.ConfirmacaoBehavior(
                        "Tem certeza que deseja excluir esta certidão?"));
                item.add(linkExcluir);
            }
        };
        tableContainer.add(certidoesList);
    }

    /**
     * Obtém o painel de feedback para atualização via Ajax
     */
    private WebMarkupContainer getFeedbackPanel() {
        return (WebMarkupContainer) get("feedback");
    }

    /**
     * Obtém a instância do repositório (lazy initialization)
     * Como o repositório usa dados estáticos, podemos criar uma nova instância
     * quando necessário
     */
    private CertidaoRepository getRepository() {
        if (repository == null) {
            repository = new InMemoryCertidaoRepository();
        }
        return repository;
    }
}
