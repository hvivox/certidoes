package com.hvivox.certidoes.page;

import com.hvivox.certidoes.BasePage;
import com.hvivox.certidoes.documentacao.HeaderContributionsDocumentacao;
import org.apache.wicket.markup.head.*;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * ====================================================================
 * PÁGINA DE DEMONSTRAÇÃO: HEADER CONTRIBUTIONS NO WICKET
 * ====================================================================
 * 
 * Esta página demonstra diferentes formas de adicionar recursos
 * (CSS, JavaScript) ao <head> da página usando Header Contributions.
 * 
 * MÓDULO 6: HEADER CONTRIBUTIONS (TAREFA #49)
 * 
 * EXEMPLOS DEMONSTRADOS:
 * 1. CSS Inline - Direto no renderHead()
 * 2. JavaScript Inline - Código JavaScript embutido
 * 3. CSS Externo - Arquivo .css no classpath
 * 4. JavaScript Externo - Arquivo .js no classpath
 * 5. OnDomReady - Script executado após DOM carregar
 * 6. OnLoad - Script executado após página completa
 * 7. Meta Tags - Adição de meta tags
 * 8. CDN - Recursos de CDN externos
 * 9. Componente Auto-suficiente - Panel com seus recursos
 * 10. Dependências - Gerenciamento de ordem de recursos
 * 
 * ACESSE: /header-demo
 */
public class HeaderContributionsDemoPage extends BasePage {
    private static final long serialVersionUID = 1L;

    public HeaderContributionsDemoPage(PageParameters parameters) {
        super();

        // ============================================================
        // DOCUMENTAÇÃO
        // ============================================================
        Label docLabel = new Label("documentacao", HeaderContributionsDocumentacao.getConteudo());
        docLabel.setEscapeModelStrings(false);
        add(docLabel);

        // ============================================================
        // EXEMPLO 1: CSS Inline
        // ============================================================
        WebMarkupContainer inlineCssBox = new WebMarkupContainer("inlineCssBox");
        inlineCssBox.add(new Label("inlineCssText", "Este box usa CSS Inline!"));
        add(inlineCssBox);

        // ============================================================
        // EXEMPLO 2: JavaScript Inline
        // ============================================================
        WebMarkupContainer inlineJsBox = new WebMarkupContainer("inlineJsBox");
        inlineJsBox.setOutputMarkupId(true);
        inlineJsBox.add(new Label("inlineJsText", "Clique aqui!"));
        add(inlineJsBox);

        // ============================================================
        // EXEMPLO 3: CSS Externo
        // ============================================================
        WebMarkupContainer externalCssBox = new WebMarkupContainer("externalCssBox");
        externalCssBox.add(new Label("externalCssText", "Este box usa CSS de arquivo externo!"));
        add(externalCssBox);

        // ============================================================
        // EXEMPLO 4: JavaScript Externo
        // ============================================================
        WebMarkupContainer externalJsBox = new WebMarkupContainer("externalJsBox");
        externalJsBox.setOutputMarkupId(true);
        externalJsBox.add(new Label("externalJsText", "JavaScript externo carregado!"));
        add(externalJsBox);

        // ============================================================
        // EXEMPLO 5: OnDomReady
        // ============================================================
        WebMarkupContainer domReadyBox = new WebMarkupContainer("domReadyBox");
        domReadyBox.setOutputMarkupId(true);
        domReadyBox.add(new Label("domReadyText", "Aguardando DOM Ready..."));
        add(domReadyBox);

        // ============================================================
        // EXEMPLO 6: OnLoad
        // ============================================================
        WebMarkupContainer onLoadBox = new WebMarkupContainer("onLoadBox");
        onLoadBox.setOutputMarkupId(true);
        onLoadBox.add(new Label("onLoadText", "Aguardando OnLoad..."));
        add(onLoadBox);

        // ============================================================
        // EXEMPLO 7: Meta Tags
        // ============================================================
        add(new Label("metaInfo", "Meta tags adicionadas dinamicamente!"));

        // ============================================================
        // EXEMPLO 8: CDN
        // ============================================================
        WebMarkupContainer cdnBox = new WebMarkupContainer("cdnBox");
        cdnBox.add(new Label("cdnText", "Usando recursos de CDN"));
        add(cdnBox);

        // ============================================================
        // EXEMPLO 9: Componente Auto-suficiente
        // ============================================================
        add(new RichPanel("richPanel"));

        // ============================================================
        // EXEMPLO 10: Lista de Recursos
        // ============================================================
        add(new Label("recursosInfo", getRecursosInfo()));
    }

    /**
     * ============================================================
     * RENDERHEAD - ADICIONA TODOS OS RECURSOS
     * ============================================================
     */
    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        // -----------------------------------------------
        // 1. CSS INLINE
        // -----------------------------------------------
        String cssInline = ".inline-css-box {" +
                "    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);" +
                "    color: white;" +
                "    padding: 20px;" +
                "    border-radius: 10px;" +
                "    text-align: center;" +
                "    font-weight: bold;" +
                "    box-shadow: 0 4px 6px rgba(0,0,0,0.1);" +
                "}";
        response.render(CssHeaderItem.forCSS(cssInline, "inline-css-demo"));

        // -----------------------------------------------
        // 2. JAVASCRIPT INLINE
        // -----------------------------------------------
        String jsInline = "function setupInlineJs() {" +
                "    var box = document.getElementById('inlineJsBox');" +
                "    if (box) {" +
                "        box.addEventListener('click', function() {" +
                "            alert('JavaScript inline funcionando!');" +
                "        });" +
                "        box.style.cursor = 'pointer';" +
                "    }" +
                "}";
        response.render(JavaScriptHeaderItem.forScript(jsInline, "inline-js-demo"));

        // -----------------------------------------------
        // 3. CSS EXTERNO (se existir arquivo)
        // -----------------------------------------------
        String cssExterno = ".external-css-box {" +
                "    background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);" +
                "    color: white;" +
                "    padding: 20px;" +
                "    border-radius: 10px;" +
                "    text-align: center;" +
                "    font-weight: bold;" +
                "    box-shadow: 0 4px 6px rgba(0,0,0,0.1);" +
                "}";
        response.render(CssHeaderItem.forCSS(cssExterno, "external-css-demo"));

        // -----------------------------------------------
        // 4. JAVASCRIPT EXTERNO
        // -----------------------------------------------
        String jsExterno = "function setupExternalJs() {" +
                "    var box = document.getElementById('externalJsBox');" +
                "    if (box) {" +
                "        box.addEventListener('mouseover', function() {" +
                "            this.style.transform = 'scale(1.05)';" +
                "        });" +
                "        box.addEventListener('mouseout', function() {" +
                "            this.style.transform = 'scale(1)';" +
                "        });" +
                "        box.style.transition = 'transform 0.3s';" +
                "    }" +
                "}";
        response.render(JavaScriptHeaderItem.forScript(jsExterno, "external-js-demo"));

        // -----------------------------------------------
        // 5. ON DOM READY
        // -----------------------------------------------
        String domReadyScript = "setupInlineJs();" +
                "setupExternalJs();" +
                "var domBox = document.getElementById('domReadyBox');" +
                "if (domBox) {" +
                "    domBox.querySelector('#domReadyText').textContent = '✓ DOM Ready executado!';" +
                "    domBox.style.backgroundColor = '#28a745';" +
                "}";
        response.render(OnDomReadyHeaderItem.forScript(domReadyScript));

        // -----------------------------------------------
        // 6. ON LOAD
        // -----------------------------------------------
        String onLoadScript = "var loadBox = document.getElementById('onLoadBox');" +
                "if (loadBox) {" +
                "    loadBox.querySelector('#onLoadText').textContent = '✓ OnLoad executado!';" +
                "    loadBox.style.backgroundColor = '#17a2b8';" +
                "}";
        response.render(OnLoadHeaderItem.forScript(onLoadScript));

        // -----------------------------------------------
        // 7. META TAGS
        // -----------------------------------------------
        response.render(StringHeaderItem.forString(
                "<meta name='description' content='Página de demonstração de Header Contributions no Wicket'>\n"));
        response.render(StringHeaderItem.forString(
                "<meta name='keywords' content='wicket, header, contributions, css, javascript'>\n"));
        response.render(StringHeaderItem.forString(
                "<meta name='author' content='Wicket Demo'>\n"));

        // -----------------------------------------------
        // 8. CDN (Exemplo: Animate.css)
        // -----------------------------------------------
        response.render(CssHeaderItem.forUrl(
                "https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"));

        String cdnScript = "var cdnBox = document.getElementById('cdnBox');" +
                "if (cdnBox) {" +
                "    cdnBox.className += ' animate__animated animate__pulse animate__infinite';" +
                "}";
        response.render(OnDomReadyHeaderItem.forScript(cdnScript));

        // -----------------------------------------------
        // CSS para boxes de exemplo
        // -----------------------------------------------
        String boxStyles = ".demo-box {" +
                "    padding: 20px;" +
                "    margin-bottom: 15px;" +
                "    border-radius: 8px;" +
                "    text-align: center;" +
                "    font-weight: bold;" +
                "    color: white;" +
                "    box-shadow: 0 2px 4px rgba(0,0,0,0.1);" +
                "}" +
                "#inlineJsBox {" +
                "    background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);" +
                "}" +
                "#externalJsBox {" +
                "    background: linear-gradient(135deg, #fa709a 0%, #fee140 100%);" +
                "}" +
                "#domReadyBox {" +
                "    background: linear-gradient(135deg, #30cfd0 0%, #330867 100%);" +
                "}" +
                "#onLoadBox {" +
                "    background: linear-gradient(135deg, #a8edea 0%, #fed6e3 100%);" +
                "}" +
                "#cdnBox {" +
                "    background: linear-gradient(135deg, #ff9a9e 0%, #fecfef 100%);" +
                "}";
        response.render(CssHeaderItem.forCSS(boxStyles, "demo-box-styles"));
    }

    /**
     * Retorna informações sobre os recursos carregados
     */
    private String getRecursosInfo() {
        return "<ul class='list-unstyled'>" +
                "<li><i class='fas fa-check text-success'></i> CSS Inline</li>" +
                "<li><i class='fas fa-check text-success'></i> JavaScript Inline</li>" +
                "<li><i class='fas fa-check text-success'></i> OnDomReady Script</li>" +
                "<li><i class='fas fa-check text-success'></i> OnLoad Script</li>" +
                "<li><i class='fas fa-check text-success'></i> Meta Tags</li>" +
                "<li><i class='fas fa-check text-success'></i> CDN (Animate.css)</li>" +
                "<li><i class='fas fa-check text-success'></i> Componente Auto-suficiente</li>" +
                "</ul>";
    }

    /**
     * ============================================================
     * RICH PANEL - COMPONENTE AUTO-SUFICIENTE
     * ============================================================
     * 
     * Este componente traz seus próprios recursos (CSS/JS)
     */
    public static class RichPanel extends Panel {
        private static final long serialVersionUID = 1L;

        public RichPanel(String id) {
            super(id);
            setOutputMarkupId(true);

            add(new Label("richContent", "Componente Auto-suficiente"));
            add(new Label("richDescription",
                    "Este componente adiciona seus próprios recursos automaticamente!"));
        }

        @Override
        public void renderHead(IHeaderResponse response) {
            super.renderHead(response);

            // CSS específico do componente
            String componentCss = ".rich-panel {" +
                    "    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);" +
                    "    color: white;" +
                    "    padding: 25px;" +
                    "    border-radius: 12px;" +
                    "    box-shadow: 0 8px 16px rgba(0,0,0,0.2);" +
                    "    border: 3px solid #ffffff40;" +
                    "}" +
                    ".rich-panel h5 {" +
                    "    margin: 0 0 10px 0;" +
                    "    font-size: 1.5rem;" +
                    "}" +
                    ".rich-panel p {" +
                    "    margin: 0;" +
                    "    opacity: 0.9;" +
                    "}";
            response.render(CssHeaderItem.forCSS(componentCss, "rich-panel-css"));

            // JavaScript específico do componente
            String componentJs = String.format(
                    "var panel = document.getElementById('%s');" +
                            "if (panel) {" +
                            "    panel.addEventListener('click', function() {" +
                            "        this.classList.toggle('animate__animated');" +
                            "        this.classList.toggle('animate__tada');" +
                            "        setTimeout(() => {" +
                            "            this.classList.remove('animate__animated');" +
                            "            this.classList.remove('animate__tada');" +
                            "        }, 1000);" +
                            "    });" +
                            "    panel.style.cursor = 'pointer';" +
                            "}",
                    getMarkupId());
            response.render(OnDomReadyHeaderItem.forScript(componentJs));
        }
    }
}
