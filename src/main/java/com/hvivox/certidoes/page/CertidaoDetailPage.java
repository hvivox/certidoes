package com.hvivox.certidoes.page;

import com.hvivox.certidoes.BasePage;
import com.hvivox.certidoes.domain.Certidao;
import com.hvivox.certidoes.infra.CertidaoRepository;
import com.hvivox.certidoes.infra.InMemoryCertidaoRepository;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.Optional;

public class CertidaoDetailPage extends BasePage {
    private static final long serialVersionUID = 1L;

    // Repositório (transient porque não é serializável - Wicket serializa páginas)
    private transient CertidaoRepository repository;

    public CertidaoDetailPage(final PageParameters parameters) {
        super();

        // Buscar ID da certidão nos parâmetros
        Long id = parameters.get("id").toOptionalLong();

        if (id == null) {
            // Se não tem ID, mostrar mensagem de erro
            showNotFound();
            return;
        }

        // Buscar certidão no repositório
        Optional<Certidao> certidaoOpt = getRepository().findById(id);

        if (!certidaoOpt.isPresent()) {
            // Certidão não encontrada
            showNotFound();
            return;
        }

        Certidao certidao = certidaoOpt.get();

        // Container do cabeçalho com botões (só aparece se encontrou)
        WebMarkupContainer headerContainer = new WebMarkupContainer("headerContainer");
        headerContainer.setVisible(true);
        add(headerContainer);

        // Links de ação no cabeçalho
        PageParameters editParams = new PageParameters();
        editParams.add("id", certidao.getId());
        headerContainer.add(new BookmarkablePageLink<>("linkEditar", CertidaoFormPage.class, editParams));
        headerContainer.add(new BookmarkablePageLink<>("linkVoltar", CertidaoListPage.class));

        // Container para os detalhes
        WebMarkupContainer certidaoContainer = new WebMarkupContainer("certidaoContainer");
        certidaoContainer.setVisible(true);
        add(certidaoContainer);

        // Campos somente leitura
        certidaoContainer.add(new Label("id", certidao.getId()));
        certidaoContainer.add(new Label("numero", certidao.getNumero()));
        certidaoContainer.add(new Label("tipo", formatTipo(certidao.getTipo())));
        certidaoContainer.add(new Label("interessado", certidao.getInteressado()));
        certidaoContainer.add(new Label("dataEmissao", certidao.getDataEmissao()));
        certidaoContainer.add(new Label("status", formatStatus(certidao.getStatus())));

        // Mensagem de não encontrado (escondida)
        WebMarkupContainer notFoundMessage = new WebMarkupContainer("notFoundMessage");
        notFoundMessage.setVisible(false);
        add(notFoundMessage);
    }

    /**
     * Mostra mensagem de certidão não encontrada
     */
    private void showNotFound() {
        // Esconder cabeçalho com botões
        WebMarkupContainer headerContainer = new WebMarkupContainer("headerContainer");
        headerContainer.setVisible(false);
        add(headerContainer);
        headerContainer.add(new WebMarkupContainer("linkEditar").setVisible(false));
        headerContainer.add(new WebMarkupContainer("linkVoltar").setVisible(false));

        // Esconder container de detalhes
        WebMarkupContainer certidaoContainer = new WebMarkupContainer("certidaoContainer");
        certidaoContainer.setVisible(false);
        add(certidaoContainer);

        // Mostrar mensagem de erro
        WebMarkupContainer notFoundMessage = new WebMarkupContainer("notFoundMessage");
        notFoundMessage.setVisible(true);
        add(notFoundMessage);

        // Adicionar link para voltar quando não encontrou
        notFoundMessage.add(new BookmarkablePageLink<>("linkVoltarNotFound", CertidaoListPage.class));
    }

    /**
     * Obtém a instância do repositório (lazy initialization)
     * Como o repositório usa dados estáticos, podemos criar uma nova instância quando necessário
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
