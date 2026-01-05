package com.hvivox.certidoes.page;

import com.hvivox.certidoes.BasePage;
import org.apache.wicket.markup.head.*;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.*;

/**
 * MÓDULO 7 - ITEM 53: CUSTOMIZAÇÃO DO CARREGAMENTO DE RECURSOS
 * 
 * Esta página demonstra de forma SIMPLES E PRÁTICA como customizar o
 * carregamento
 * de recursos (CSS, JS, imagens) no Apache Wicket.
 * 
 * EXEMPLOS INCLUÍDOS:
 * 1. CSS via ResourceReference (arquivo externo)
 * 2. JavaScript via ResourceReference (arquivo externo)
 * 3. CSS Inline (direto no código)
 * 4. JavaScript Inline (direto no código)
 * 5. Recursos de CDN
 * 6. Context Relative Resources
 */
public class RecursosCustomizadosDemoPage extends BasePage {

    private static final long serialVersionUID = 1L;

    // BOA PRÁTICA: Declare ResourceReferences como static final para reutilização
    private static final CssResourceReference CUSTOM_CSS = new CssResourceReference(RecursosCustomizadosDemoPage.class,
            "custom-resource.css");

    private static final JavaScriptResourceReference CUSTOM_JS = new JavaScriptResourceReference(
            RecursosCustomizadosDemoPage.class, "custom-resource.js");

    public RecursosCustomizadosDemoPage(final PageParameters parameters) {
        super();

        // Labels informativos
        add(new Label("pageTitle", "Customização de Recursos"));
        add(new Label("pageSubtitle", "Aprenda a carregar CSS, JS e outros recursos de forma customizada"));

        // Exemplo 1: ResourceReference
        add(new Label("exemplo1Info",
                "CSS e JavaScript carregados via ResourceReference (arquivos custom-resource.css e custom-resource.js)"));

        // Exemplo 2: CSS Inline
        add(new Label("exemplo2Info",
                "CSS adicionado diretamente via CssHeaderItem.forCSS() no método renderHead()"));

        // Exemplo 3: JS Inline
        add(new Label("exemplo3Info",
                "JavaScript adicionado via JavaScriptHeaderItem.forScript()"));

        // Exemplo 4: CDN
        add(new Label("exemplo4Info",
                "Recurso carregado de CDN externo via CssHeaderItem.forUrl()"));

        // Exemplo 5: Context Relative
        add(new Label("exemplo5Info",
                "Recursos da pasta webapp/ via ContextRelativeResourceReference"));

        // Informações sobre configurações
        add(new Label("configInfo", getConfigInfo()).setEscapeModelStrings(false));

        // Documentação inline
        add(new Label("docInfo", getDocumentacaoInfo()).setEscapeModelStrings(false));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        // ═══════════════════════════════════════════════════════════════════
        // EXEMPLO 1: CSS e JS via ResourceReference
        // ═══════════════════════════════════════════════════════════════════
        // Arquivos custom-resource.css e custom-resource.js no mesmo pacote
        response.render(CssHeaderItem.forReference(CUSTOM_CSS));
        response.render(JavaScriptHeaderItem.forReference(CUSTOM_JS));

        // ═══════════════════════════════════════════════════════════════════
        // EXEMPLO 2: CSS Inline (direto no código)
        // ═══════════════════════════════════════════════════════════════════
        String inlineCss = ".inline-example { " +
                "  background: linear-gradient(135deg, #43e97b 0%, #38f9d7 100%); " +
                "  padding: 20px; " +
                "  border-radius: 10px; " +
                "  color: white; " +
                "  text-align: center; " +
                "  font-weight: bold; " +
                "}";
        response.render(CssHeaderItem.forCSS(inlineCss, "inline-css-example"));

        // ═══════════════════════════════════════════════════════════════════
        // EXEMPLO 3: JavaScript Inline
        // ═══════════════════════════════════════════════════════════════════
        String inlineJs = "console.log('📦 JavaScript Inline carregado!'); " +
                "window.exemploInline = function() { " +
                "  alert('Função JavaScript carregada inline!'); " +
                "};";
        response.render(JavaScriptHeaderItem.forScript(inlineJs, "inline-js-example"));

        // ═══════════════════════════════════════════════════════════════════
        // EXEMPLO 4: Recurso de CDN (Animate.css)
        // ═══════════════════════════════════════════════════════════════════
        response.render(CssHeaderItem.forUrl(
                "https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css",
                "animate-css"));

        // ═══════════════════════════════════════════════════════════════════
        // EXEMPLO 5: OnDomReady - Executar quando DOM estiver pronto
        // ═══════════════════════════════════════════════════════════════════
        String onDomReadyScript = "console.log('🎯 DOM está pronto!'); " +
                "document.querySelectorAll('.exemplo-box').forEach(function(box) { " +
                "  box.style.borderLeft = '4px solid #007bff'; " +
                "});";
        response.render(OnDomReadyHeaderItem.forScript(onDomReadyScript));
    }

    private String getConfigInfo() {
        StringBuilder info = new StringBuilder();
        info.append("<div class='alert alert-info'>");
        info.append("<h5><i class='fas fa-cog'></i> Configurações Atuais</h5>");
        info.append("<ul class='mb-0'>");

        // Estratégia de cache
        String strategy = getApplication().getResourceSettings()
                .getCachingStrategy().getClass().getSimpleName();
        info.append("<li><strong>Estratégia de Cache:</strong> ").append(strategy).append("</li>");

        // Minificação
        boolean useMinified = getApplication().getResourceSettings().getUseMinifiedResources();
        info.append("<li><strong>Usa Recursos Minificados:</strong> ")
                .append(useMinified ? "Sim" : "Não").append("</li>");

        // Modo de configuração
        String configMode = getApplication().usesDeploymentConfig() ? "Produção" : "Desenvolvimento";
        info.append("<li><strong>Modo:</strong> ").append(configMode).append("</li>");

        info.append("</ul>");
        info.append("<small class='text-muted mt-2 d-block'>");
        info.append("Configure em <code>WicketApplication.init()</code>");
        info.append("</small>");
        info.append("</div>");

        return info.toString();
    }

    private String getDocumentacaoInfo() {
        return "<div class='card'>" +
                "<div class='card-header bg-primary text-white'>" +
                "<h5 class='mb-0'><i class='fas fa-book'></i> Resumo dos Conceitos</h5>" +
                "</div>" +
                "<div class='card-body'>" +
                "<p>Esta página demonstra os principais conceitos de customização de recursos no Apache Wicket.</p>" +
                "<h6>Tópicos Cobertos:</h6>" +
                "<div class='row'>" +
                "<div class='col-md-6'>" +
                "<ul>" +
                "<li>Formas de carregar recursos</li>" +
                "<li>ResourceReference (CSS, JS, imagens)</li>" +
                "<li>Cache e versionamento</li>" +
                "<li>Recursos dinâmicos</li>" +
                "</ul>" +
                "</div>" +
                "<div class='col-md-6'>" +
                "<ul>" +
                "<li>Resource Bundles</li>" +
                "<li>Minificação automática</li>" +
                "<li>URLs customizadas</li>" +
                "<li>Boas práticas</li>" +
                "</ul>" +
                "</div>" +
                "</div>" +
                "<p class='mt-3 mb-0'><strong>Configurações:</strong> Veja <code>WicketApplication.configurarRecursos()</code> "
                +
                "para ver como configurar recursos no Wicket.</p>" +
                "</div>" +
                "</div>";
    }
}
