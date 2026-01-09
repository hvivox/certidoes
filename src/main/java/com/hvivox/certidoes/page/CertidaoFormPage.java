package com.hvivox.certidoes.page;

import com.hvivox.certidoes.BasePage;
import com.hvivox.certidoes.domain.Certidao;
import com.hvivox.certidoes.domain.CertidaoStatus;
import com.hvivox.certidoes.domain.CertidaoTipo;
import com.hvivox.certidoes.infra.CertidaoRepository;
import com.hvivox.certidoes.infra.InMemoryCertidaoRepository;
import com.hvivox.certidoes.validator.DataFormatadaValidator;
import com.hvivox.certidoes.validator.NumeroUnicoValidator;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
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
        // MÓDULO 4 - ITEM 5: Processamento Avançado (onError, onValidate)
        Form<Certidao> form = new Form<Certidao>("form", new CompoundPropertyModel<>(certidao)) {
            @Override
            protected void onSubmit() {
                // Validação automática já foi feita pelos validadores antes deste método
                // onValidate() também foi executado antes deste método
                // Se chegou aqui, todos os campos estão válidos

                // Salvar
                getRepository().save(certidao);

                if (isEditMode) {
                    getSession().success("Certidão atualizada com sucesso!");
                } else {
                    getSession().success("Certidão cadastrada com sucesso!");
                }

                setResponsePage(CertidaoListPage.class);
            }

            /**
             * 
             * Este método é chamado quando há erros de validação no formulário.
             * É executado ANTES do onSubmit() se houver erros.
             * 
             * USO:
             * - Tratar erros de validação de forma customizada
             * - Adicionar mensagens de erro específicas
             * - Registrar erros para análise
             * - Personalizar feedback ao usuário
             * - Adicionar classes CSS para destacar campos com erro
             */
            @Override
            protected void onError() {
                super.onError(); // Chama o método da classe pai para manter comportamento padrão

                // MÓDULO 4 - ITEM 7: Adicionar classe CSS aos campos com erro
                // Isso destaca visualmente os campos inválidos usando Bootstrap
                visitChildren(FormComponent.class, (component, visit) -> {
                    FormComponent<?> fc = (FormComponent<?>) component;
                    if (!fc.isValid()) {
                        // Adicionar classe Bootstrap para destacar campo com erro
                        fc.add(new AttributeModifier("class", Model.of("form-control is-invalid")));
                    } else {
                        // Remover classe de erro se o campo estiver válido
                        fc.add(new AttributeModifier("class", Model.of("form-control")));
                    }
                });
            }

            /**
             * MÓDULO 4 - ITEM 5: Método onValidate()
             * 
             * Este método é chamado ANTES do onSubmit() e DEPOIS das validações
             * automáticas dos componentes (setRequired, validators, etc.).
             * 
             * USO:
             * - Validações de negócio que dependem de múltiplos campos
             * - Validações que não podem ser feitas em validadores individuais
             * - Validações condicionais baseadas no estado do objeto
             * - Validações que precisam acessar o repositório ou serviços
             * 
             * IMPORTANTE:
             * - Se encontrar erros, use error() para adicionar mensagens
             * - Se houver erros, onSubmit() NÃO será chamado
             * - onError() será chamado se houver erros após onValidate()
             */
            /**
             * MÓDULO 4 - ITEM 5: Método onValidate()
             * 
             * Este método é chamado ANTES do onSubmit() e DEPOIS das validações
             * automáticas dos componentes (setRequired, validators, etc.).
             * 
             * IMPORTANTE: onValidate() é chamado SEMPRE, mesmo se houver erros de campo.
             * Se adicionar erros aqui usando error(), o onSubmit() NÃO será chamado
             * e onError() será chamado automaticamente.
             * 
             * NOTA: A validação de número único foi movida para NumeroUnicoValidator
             * para garantir que seja executada no ciclo de validação do Wicket.
             */
            @Override
            protected void onValidate() {
                super.onValidate(); // Chama o método da classe pai

                // Validações de negócio customizadas adicionais
                // (validações que não podem ser feitas em validadores individuais)

                // Validação: Verificar se interessado não está vazio (validação adicional)
                if (certidao.getInteressado() != null && certidao.getInteressado().trim().isEmpty()) {
                    error("O campo Interessado não pode conter apenas espaços em branco.");
                }
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
        // Adicionar validador de número único APENAS em modo novo (criação)
        // Em modo edição, não validamos duplicação para permitir manter o mesmo número
        if (!isEditMode) {
            numeroField.add(new NumeroUnicoValidator(false, null));
        }
        form.add(numeroField);

        // FeedbackPanel específico para o campo número (mostra apenas erros deste
        // campo)
        FeedbackPanel numeroFeedback = new FeedbackPanel("numeroFeedback",
                new ComponentFeedbackMessageFilter(numeroField));
        numeroFeedback.setOutputMarkupId(true);
        form.add(numeroFeedback);

        // Campo Tipo (obrigatório) - Dropdown
        DropDownChoice<CertidaoTipo> tipoField = new DropDownChoice<>("tipo",
                Arrays.asList(CertidaoTipo.values()));
        tipoField.setRequired(true);
        tipoField.setNullValid(false);
        form.add(tipoField);

        // FeedbackPanel específico para o campo tipo (mostra apenas erros deste campo)
        FeedbackPanel tipoFeedback = new FeedbackPanel("tipoFeedback",
                new ComponentFeedbackMessageFilter(tipoField));
        tipoFeedback.setOutputMarkupId(true);
        form.add(tipoFeedback);

        // Campo Interessado (obrigatório)
        TextField<String> interessadoField = new TextField<>("interessado");
        interessadoField.setRequired(true);
        interessadoField.add(StringValidator.minimumLength(1));
        form.add(interessadoField);

        // FeedbackPanel específico para o campo interessado (mostra apenas erros deste
        // campo)
        FeedbackPanel interessadoFeedback = new FeedbackPanel("interessadoFeedback",
                new ComponentFeedbackMessageFilter(interessadoField));
        interessadoFeedback.setOutputMarkupId(true);
        form.add(interessadoFeedback);

        // Campo Data Emissão (obrigatório)
        // Usando DataFormatadaValidator para validar formato dd/MM/yyyy
        // MÓDULO 4 - ITEM 7: Feedback inline por componente
        TextField<String> dataEmissaoField = new TextField<>("dataEmissao");
        dataEmissaoField.setRequired(true);
        dataEmissaoField.add(new DataFormatadaValidator());
        form.add(dataEmissaoField);
        // MÓDULO 4 - ITEM 7: Feedback inline por componente
        // FeedbackPanel específico para o campo dataEmissao (mostra apenas erros deste
        // campo)
        FeedbackPanel dataEmissaoFeedback = new FeedbackPanel("dataEmissaoFeedback",
                new ComponentFeedbackMessageFilter(dataEmissaoField));
        dataEmissaoFeedback.setOutputMarkupId(true);
        form.add(dataEmissaoFeedback);

        // Campo Status (opcional) - Dropdown
        // MÓDULO 4 - ITEM 7: Feedback inline por componente
        DropDownChoice<CertidaoStatus> statusField = new DropDownChoice<>("status",
                Arrays.asList(CertidaoStatus.values()));
        statusField.setNullValid(true);
        form.add(statusField);
        // MÓDULO 4 - ITEM 7: Feedback inline por componente
        // FeedbackPanel específico para o campo status (mostra apenas erros deste
        // campo)
        FeedbackPanel statusFeedback = new FeedbackPanel("statusFeedback",
                new ComponentFeedbackMessageFilter(statusField));
        statusFeedback.setOutputMarkupId(true);
        form.add(statusFeedback);

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
