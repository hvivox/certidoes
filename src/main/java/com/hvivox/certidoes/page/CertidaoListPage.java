package com.hvivox.certidoes.page;

import com.hvivox.certidoes.BasePage;
import com.hvivox.certidoes.domain.Certidao;
import com.hvivox.certidoes.infra.CertidaoRepository;
import com.hvivox.certidoes.infra.InMemoryCertidaoRepository;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.List;

public class CertidaoListPage extends BasePage {
    private static final long serialVersionUID = 1L;

    // Repositório (transient porque não é serializável - Wicket serializa páginas)
    private transient CertidaoRepository repository;

    public CertidaoListPage(final PageParameters parameters) {
        super();

        // Buscar todas as certidões
        List<Certidao> certidoes = getRepository().findAll();

        // Container para a tabela (para poder esconder se não houver dados)
        WebMarkupContainer tableContainer = new WebMarkupContainer("tableContainer");
        tableContainer.setVisible(!certidoes.isEmpty());
        add(tableContainer);

        // ListView para repetir as linhas da tabela
        ListView<Certidao> certidoesList = new ListView<Certidao>("certidoes", certidoes) {
            @Override
            protected void populateItem(ListItem<Certidao> item) {
                Certidao certidao = item.getModelObject();

                // Colunas simples
                item.add(new Label("id", certidao.getId()));
                item.add(new Label("numero", certidao.getNumero()));
                item.add(new Label("tipo", formatTipo(certidao.getTipo())));
                item.add(new Label("interessado", certidao.getInteressado()));
                item.add(new Label("dataEmissao", certidao.getDataEmissao()));
                item.add(new Label("status", formatStatus(certidao.getStatus())));

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

                // Link Excluir com confirmação (onclick está no HTML)
                Link<Void> linkExcluir = new Link<Void>("linkExcluir") {
                    @Override
                    public void onClick() {
                        Long certidaoId = certidao.getId();
                        getRepository().delete(certidaoId);
                        getSession().info("Certidão excluída com sucesso!");
                        setResponsePage(CertidaoListPage.class);
                    }
                };
                item.add(linkExcluir);
            }
        };
        tableContainer.add(certidoesList);

        // Mensagem quando não houver dados
        WebMarkupContainer emptyMessage = new WebMarkupContainer("emptyMessage");
        emptyMessage.setVisible(certidoes.isEmpty());
        add(emptyMessage);

        // Link "Nova Certidão" no topo
        add(new BookmarkablePageLink<>("linkNovaCertidao", CertidaoFormPage.class));
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

    /**
     * Formata o tipo da certidão para exibição
     */
    private String formatTipo(com.hvivox.certidoes.domain.CertidaoTipo tipo) {
        if (tipo == null)
            return "";
        switch (tipo) {
            case NEGATIVA:
                return "Negativa";
            case POSITIVA:
                return "Positiva";
            case POSITIVA_COM_EFEITO_DE_NEGATIVA:
                return "Positiva com Efeito de Negativa";
            default:
                return tipo.toString();
        }
    }

    /**
     * Formata o status da certidão para exibição
     */
    private String formatStatus(com.hvivox.certidoes.domain.CertidaoStatus status) {
        if (status == null)
            return "";
        switch (status) {
            case RASCUNHO:
                return "Rascunho";
            case EMITIDA:
                return "Emitida";
            case CANCELADA:
                return "Cancelada";
            default:
                return status.toString();
        }
    }
}