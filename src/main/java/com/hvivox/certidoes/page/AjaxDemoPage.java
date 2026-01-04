package com.hvivox.certidoes.page;

import com.hvivox.certidoes.BasePage;
import com.hvivox.certidoes.documentacao.AjaxDocumentacao;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ====================================================================
 * PÁGINA DE DEMONSTRAÇÃO: AJAX NO WICKET
 * ====================================================================
 * 
 * Esta página demonstra componentes e behaviors Ajax do Wicket.
 * 
 * MÓDULO 6: AJAX E COMPONENTES RICOS (TAREFA #48)
 * 
 * EXEMPLOS DEMONSTRADOS:
 * 1. AjaxLink - Link que executa ação sem reload
 * 2. AjaxButton - Botão que submete com Ajax
 * 3. IndicatingAjaxButton - Botão com loading spinner
 * 4. AjaxFallbackLink - Funciona com/sem JavaScript
 * 5. AjaxCheckBox - Checkbox com atualização instantânea
 * 6. AjaxFormComponentUpdatingBehavior - Atualização em tempo real
 * 7. AbstractAjaxTimerBehavior - Auto-atualização com timer
 * 8. AjaxSubmitLink - Link que submete formulário
 * 
 * ACESSE: /ajax-demo
 */
public class AjaxDemoPage extends BasePage {
    private static final long serialVersionUID = 1L;

    // Campos para exemplos
    private int contador = 0;
    private String textoDigitado = "";
    private boolean checkboxAtivo = false;
    private String nome = "";
    private String email = "";
    private String mensagemTempoReal = "Digite algo...";
    private int contadorAutomatico = 0;

    public AjaxDemoPage(PageParameters parameters) {
        super();

        // ============================================================
        // DOCUMENTAÇÃO
        // ============================================================
        Label docLabel = new Label("documentacao", AjaxDocumentacao.getConteudo());
        docLabel.setEscapeModelStrings(false);
        add(docLabel);

        // ============================================================
        // EXEMPLO 1: AjaxLink - Link Simples
        // ============================================================

        Label labelContador = new Label("labelContador", new PropertyModel<>(this, "contador"));
        labelContador.setOutputMarkupId(true);
        add(labelContador);

        add(new AjaxLink<Void>("ajaxLink") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                contador++;
                target.add(labelContador);
                info("Contador incrementado via Ajax!");
                target.add(getPage().get("feedback"));
            }
        });

        add(new AjaxLink<Void>("resetLink") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                contador = 0;
                target.add(labelContador);
                success("Contador resetado!");
                target.add(getPage().get("feedback"));
            }
        });

        // ============================================================
        // EXEMPLO 2: Container com Visibilidade Toggle
        // ============================================================

        final WebMarkupContainer containerToggle = new WebMarkupContainer("containerToggle");
        containerToggle.setOutputMarkupPlaceholderTag(true);
        containerToggle.setVisible(true);
        add(containerToggle);

        containerToggle.add(new Label("conteudoToggle", "Este conteúdo pode ser mostrado/ocultado com Ajax!"));

        add(new AjaxLink<Void>("toggleLink") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                containerToggle.setVisible(!containerToggle.isVisible());
                target.add(containerToggle);
            }
        });

        // ============================================================
        // EXEMPLO 3: Formulário com AjaxButton
        // ============================================================

        Form<Void> ajaxForm = new Form<>("ajaxForm");
        add(ajaxForm);

        ajaxForm.add(new TextField<>("nome", new PropertyModel<>(this, "nome")));
        ajaxForm.add(new TextField<>("email", new PropertyModel<>(this, "email")));

        Label resultadoForm = new Label("resultadoForm", new PropertyModel<>(this, "mensagemTempoReal"));
        resultadoForm.setOutputMarkupId(true);
        ajaxForm.add(resultadoForm);

        ajaxForm.add(new AjaxButton("ajaxSaveButton") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                mensagemTempoReal = String.format("✓ Dados salvos: %s (%s)", nome, email);
                target.add(resultadoForm);
                success("Formulário salvo com Ajax!");
                target.add(getPage().get("feedback"));
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                mensagemTempoReal = "✗ Erro ao salvar!";
                target.add(resultadoForm);
                error("Erro na validação!");
                target.add(getPage().get("feedback"));
            }
        });

        // ============================================================
        // EXEMPLO 4: IndicatingAjaxButton - Com Loading Spinner
        // ============================================================

        Form<Void> indicatingForm = new Form<>("indicatingForm");
        add(indicatingForm);

        Label resultadoIndicating = new Label("resultadoIndicating", Model.of("Aguardando..."));
        resultadoIndicating.setOutputMarkupId(true);
        indicatingForm.add(resultadoIndicating);

        indicatingForm.add(new IndicatingAjaxButton("indicatingButton") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                // Simular processamento demorado
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                resultadoIndicating.setDefaultModelObject("✓ Processamento concluído!");
                target.add(resultadoIndicating);
                success("Processamento finalizado!");
                target.add(getPage().get("feedback"));
            }
        });

        // ============================================================
        // EXEMPLO 5: AjaxCheckBox - Atualização Instantânea
        // ============================================================

        Label statusCheckbox = new Label("statusCheckbox", new PropertyModel<>(this, "checkboxAtivo"));
        statusCheckbox.setOutputMarkupId(true);
        add(statusCheckbox);

        add(new AjaxCheckBox("ajaxCheckbox", new PropertyModel<>(this, "checkboxAtivo")) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.add(statusCheckbox);
                info("Checkbox alterado para: " + checkboxAtivo);
                target.add(getPage().get("feedback"));
            }
        });

        // ============================================================
        // EXEMPLO 6: AjaxFormComponentUpdatingBehavior - Tempo Real
        // ============================================================

        TextField<String> campoTempoReal = new TextField<>("campoTempoReal",
                new PropertyModel<>(this, "textoDigitado"));
        campoTempoReal.setOutputMarkupId(true);
        add(campoTempoReal);

        Label previewTexto = new Label("previewTexto", new PropertyModel<>(this, "textoDigitado"));
        previewTexto.setOutputMarkupId(true);
        add(previewTexto);

        Label contadorCaracteres = new Label("contadorCaracteres", new PropertyModel<String>(this, "textoDigitado") {
            private static final long serialVersionUID = 1L;

            @Override
            public String getObject() {
                return textoDigitado != null ? String.valueOf(textoDigitado.length()) : "0";
            }
        });
        contadorCaracteres.setOutputMarkupId(true);
        add(contadorCaracteres);

        campoTempoReal.add(new AjaxFormComponentUpdatingBehavior("keyup") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.add(previewTexto);
                target.add(contadorCaracteres);
            }
        });

        // ============================================================
        // EXEMPLO 7: AbstractAjaxTimerBehavior - Auto-atualização
        // ============================================================

        Label labelTimer = new Label("labelTimer", new PropertyModel<>(this, "contadorAutomatico"));
        labelTimer.setOutputMarkupId(true);
        add(labelTimer);

        Label horaAtual = new Label("horaAtual", Model.of(getCurrentTime()));
        horaAtual.setOutputMarkupId(true);
        add(horaAtual);

        // Timer que atualiza a cada 2 segundos
        labelTimer.add(new AbstractAjaxTimerBehavior(Duration.seconds(2)) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onTimer(AjaxRequestTarget target) {
                contadorAutomatico++;
                horaAtual.setDefaultModelObject(getCurrentTime());
                target.add(labelTimer);
                target.add(horaAtual);
            }
        });

        // ============================================================
        // EXEMPLO 8: AjaxFallbackLink - Funciona com/sem JavaScript
        // ============================================================

        Label fallbackLabel = new Label("fallbackLabel", Model.of("0"));
        fallbackLabel.setOutputMarkupId(true);
        add(fallbackLabel);

        add(new AjaxFallbackLink<Void>("fallbackLink") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                int valor = Integer.parseInt(fallbackLabel.getDefaultModelObjectAsString());
                valor++;
                fallbackLabel.setDefaultModelObject(String.valueOf(valor));

                if (target != null) {
                    // Ajax habilitado
                    target.add(fallbackLabel);
                    info("Atualizado com Ajax!");
                } else {
                    // Ajax desabilitado - página será recarregada
                    info("Atualizado sem Ajax (reload)");
                }
                target.add(getPage().get("feedback"));
            }
        });

        // ============================================================
        // EXEMPLO 9: IndicatingAjaxLink
        // ============================================================

        Label indicatingLinkResult = new Label("indicatingLinkResult", Model.of("Clique no link..."));
        indicatingLinkResult.setOutputMarkupId(true);
        add(indicatingLinkResult);

        add(new IndicatingAjaxLink<Void>("indicatingLink") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                indicatingLinkResult.setDefaultModelObject("✓ Link processado!");
                target.add(indicatingLinkResult);
            }
        });

        // ============================================================
        // EXEMPLO 10: AjaxSubmitLink
        // ============================================================

        Form<Void> submitLinkForm = new Form<>("submitLinkForm");
        add(submitLinkForm);

        TextField<String> campoSubmitLink = new TextField<>("campoSubmitLink", Model.of(""));
        submitLinkForm.add(campoSubmitLink);

        Label resultSubmitLink = new Label("resultSubmitLink", Model.of(""));
        resultSubmitLink.setOutputMarkupId(true);
        submitLinkForm.add(resultSubmitLink);

        submitLinkForm.add(new AjaxSubmitLink("ajaxSubmitLink") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                String valor = campoSubmitLink.getModel().getObject();
                resultSubmitLink.setDefaultModelObject("Submetido: " + valor);
                target.add(resultSubmitLink);
                success("Form submetido via AjaxSubmitLink!");
                target.add(getPage().get("feedback"));
            }
        });
    }

    /**
     * Obtém hora atual formatada
     */
    private String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
