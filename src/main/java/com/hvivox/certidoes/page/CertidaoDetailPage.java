package com.hvivox.certidoes.page;

import com.hvivox.certidoes.BasePage;
import com.hvivox.certidoes.infra.CertidaoRepository;
import com.hvivox.certidoes.infra.InMemoryCertidaoRepository;
import com.hvivox.certidoes.model.CertidaoLoadableDetachableModel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class CertidaoDetailPage extends BasePage {
    private static final long serialVersionUID = 1L;

    // Repositório (transient porque não é serializável - Wicket serializa páginas)
    private transient CertidaoRepository repository;

    public CertidaoDetailPage(final PageParameters parameters) {
        super();

        Long id = parameters.get("id").toOptionalLong();

        if (id == null) {
            showNotFound();
            return;
        }

        if (!getRepository().findById(id).isPresent()) {
            showNotFound();
            return;
        }

        CertidaoLoadableDetachableModel certidaoModel = new CertidaoLoadableDetachableModel(id);

        WebMarkupContainer headerContainer = new WebMarkupContainer("headerContainer");
        headerContainer.setVisible(true);
        add(headerContainer);

        PageParameters editParams = new PageParameters();
        editParams.add("id", id);
        headerContainer.add(new BookmarkablePageLink<>("linkEditar", CertidaoFormPage.class, editParams));
        headerContainer.add(new BookmarkablePageLink<>("linkVoltar", CertidaoListPage.class));

        WebMarkupContainer certidaoContainer = new WebMarkupContainer("certidaoContainer");
        certidaoContainer.setVisible(true);
        add(certidaoContainer);

        certidaoContainer.add(new Label("id", new PropertyModel<>(certidaoModel, "id")));
        certidaoContainer.add(new Label("numero", new PropertyModel<>(certidaoModel, "numero")));
        certidaoContainer.add(new Label("tipo", new PropertyModel<>(certidaoModel, "tipo.descricao")));
        certidaoContainer.add(new Label("interessado", new PropertyModel<>(certidaoModel, "interessado")));
        certidaoContainer.add(new Label("dataEmissao", new PropertyModel<>(certidaoModel, "dataEmissao")));
        certidaoContainer.add(new Label("status", new PropertyModel<>(certidaoModel, "status.descricao")));

        // Mensagem de não encontrado (escondida)
        WebMarkupContainer notFoundMessage = new WebMarkupContainer("notFoundMessage");
        notFoundMessage.setVisible(false);
        add(notFoundMessage);
    }

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

    private CertidaoRepository getRepository() {
        if (repository == null) {
            repository = new InMemoryCertidaoRepository();
        }
        return repository;
    }
}
