package com.hvivox.certidoes.page;

import com.hvivox.certidoes.BasePage;
import com.hvivox.certidoes.domain.Certidao;
import com.hvivox.certidoes.domain.CertidaoStatus;
import com.hvivox.certidoes.domain.CertidaoTipo;
import com.hvivox.certidoes.infra.CertidaoRepository;
import com.hvivox.certidoes.infra.InMemoryCertidaoRepository;
import com.hvivox.certidoes.validator.DataFormatadaValidator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.StringValidator;

import java.util.Arrays;

public class CertidaoFormPage extends BasePage {
    private static final long serialVersionUID = 1L;

    // Repositório (transient porque não é serializável - Wicket serializa páginas)
    private transient CertidaoRepository repository;
    private Certidao certidao;
    private boolean isEditMode;

    public CertidaoFormPage(final PageParameters parameters) {
        super();

        // Verificar se é edição (tem parâmetro id)
        Long id = parameters.get("id").toOptionalLong();
        isEditMode = (id != null);

        if (isEditMode) {
            // Modo edição: buscar certidão existente
            certidao = getRepository().findById(id)
                    .orElseThrow(() -> new RuntimeException("Certidão não encontrada"));
        } else {
            // Modo novo: criar certidão vazia
            certidao = new Certidao();
        }

        // Criar formulário com modelo composto (bind automático)
        Form<Certidao> form = new Form<Certidao>("form", new CompoundPropertyModel<>(certidao)) {
            @Override
            protected void onSubmit() {
                // Validação automática já foi feita pelos validadores antes deste método
                // Se chegou aqui, todos os campos estão válidos

                // Salvar
                getRepository().save(certidao);

                if (isEditMode) {
                    getSession().info("Certidão atualizada com sucesso!");
                } else {
                    getSession().info("Certidão cadastrada com sucesso!");
                }

                setResponsePage(CertidaoListPage.class);
            }
        };
        add(form);

        // Título da página (fora do form, dentro do wicket:extend)
        Label titulo = new Label("titulo", isEditMode ? "Editar Certidão" : "Nova Certidão");
        add(titulo);

        // Campo Número (obrigatório)
        TextField<String> numeroField = new TextField<>("numero");
        numeroField.setRequired(true);
        numeroField.add(StringValidator.minimumLength(1));
        form.add(numeroField);
        form.add(new WebMarkupContainer("numeroFeedback"));

        // Campo Tipo (obrigatório) - Dropdown
        DropDownChoice<CertidaoTipo> tipoField = new DropDownChoice<>("tipo",
                Arrays.asList(CertidaoTipo.values()));
        tipoField.setRequired(true);
        tipoField.setNullValid(false);
        form.add(tipoField);
        form.add(new WebMarkupContainer("tipoFeedback"));

        // Campo Interessado (obrigatório)
        TextField<String> interessadoField = new TextField<>("interessado");
        interessadoField.setRequired(true);
        interessadoField.add(StringValidator.minimumLength(1));
        form.add(interessadoField);
        form.add(new WebMarkupContainer("interessadoFeedback"));

        // Campo Data Emissão (obrigatório)
        // Usando DataFormatadaValidator para validar formato dd/MM/yyyy
        TextField<String> dataEmissaoField = new TextField<>("dataEmissao");
        dataEmissaoField.setRequired(true);
        dataEmissaoField.add(new DataFormatadaValidator());
        form.add(dataEmissaoField);
        form.add(new WebMarkupContainer("dataEmissaoFeedback"));

        // Campo Status (opcional) - Dropdown
        DropDownChoice<CertidaoStatus> statusField = new DropDownChoice<>("status",
                Arrays.asList(CertidaoStatus.values()));
        statusField.setNullValid(true);
        form.add(statusField);
        form.add(new WebMarkupContainer("statusFeedback"));

        // Botões
        form.add(new Button("salvar"));
        form.add(new BookmarkablePageLink<>("cancelar", CertidaoListPage.class));
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
