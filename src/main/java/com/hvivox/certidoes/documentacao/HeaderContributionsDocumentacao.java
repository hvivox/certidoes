package com.hvivox.certidoes.documentacao;

/**
 * ====================================================================
 * DOCUMENTAÇÃO: HEADER CONTRIBUTIONS NO WICKET - MÓDULO 6
 * ====================================================================
 * 
 * Esta classe contém a documentação sobre contribuições de cabeçalho
 * (Header Contributions) no Wicket.
 * 
 * Header Contributions permitem que componentes adicionem recursos
 * (CSS, JavaScript) dinamicamente ao <head> da página.
 */
public class HeaderContributionsDocumentacao {

    /**
     * Retorna o conteúdo HTML formatado da documentação sobre Header Contributions.
     * 
     * @return HTML formatado com a documentação
     */
    public static String getConteudo() {
        StringBuilder html = new StringBuilder();

        html.append("<h6>Header Contributions no Apache Wicket</h6>");
        html.append("<p>Header Contributions é o mecanismo do Wicket que permite aos componentes ");
        html.append("adicionar recursos (CSS, JavaScript, meta tags) ao <code>&lt;head&gt;</code> ");
        html.append("da página de forma dinâmica e desacoplada.</p>");

        html.append("<h6>Por que usar Header Contributions?</h6>");
        html.append("<ul>");
        html.append("<li><strong>Encapsulamento:</strong> Componente traz seus próprios recursos</li>");
        html.append("<li><strong>Reutilização:</strong> Não precisa lembrar de incluir CSS/JS manualmente</li>");
        html.append("<li><strong>Deduplicação:</strong> Wicket evita recursos duplicados automaticamente</li>");
        html.append("<li><strong>Ordem correta:</strong> Gerencia ordem de carregamento</li>");
        html.append("<li><strong>Modularidade:</strong> Cada componente é auto-suficiente</li>");
        html.append("</ul>");

        html.append("<h6>Formas de Adicionar Recursos:</h6>");

        html.append("<div class='alert alert-primary'>");
        html.append("<h6>1. renderHead() - Método Principal</h6>");
        html.append("<p>Sobrescreva o método <code>renderHead()</code> em qualquer componente:</p>");
        html.append("<pre><code>");
        html.append("@Override\n");
        html.append("public void renderHead(IHeaderResponse response) {\n");
        html.append("    super.renderHead(response);\n");
        html.append("    \n");
        html.append("    // Adicionar CSS\n");
        html.append("    response.render(CssHeaderItem.forReference(\n");
        html.append("        new CssResourceReference(MyComponent.class, \"style.css\")\n");
        html.append("    ));\n");
        html.append("    \n");
        html.append("    // Adicionar JavaScript\n");
        html.append("    response.render(JavaScriptHeaderItem.forReference(\n");
        html.append("        new JavaScriptResourceReference(MyComponent.class, \"script.js\")\n");
        html.append("    ));\n");
        html.append("    \n");
        html.append("    // Adicionar CSS inline\n");
        html.append("    response.render(CssHeaderItem.forCSS(\".custom { color: red; }\", \"my-css\"));\n");
        html.append("    \n");
        html.append("    // Adicionar JavaScript inline\n");
        html.append("    response.render(OnDomReadyHeaderItem.forScript(\n");
        html.append("        \"console.log('Componente carregado!');\"\n");
        html.append("    ));\n");
        html.append("}\n");
        html.append("</code></pre>");
        html.append("</div>");

        html.append("<div class='alert alert-success'>");
        html.append("<h6>2. CSS Externo (Arquivo .css)</h6>");
        html.append("<pre><code>");
        html.append("// Arquivo: MyComponent.css (mesmo pacote que MyComponent.java)\n");
        html.append(".my-component {\n");
        html.append("    background-color: #f0f0f0;\n");
        html.append("    padding: 10px;\n");
        html.append("}\n\n");
        html.append("// No componente:\n");
        html.append("@Override\n");
        html.append("public void renderHead(IHeaderResponse response) {\n");
        html.append("    super.renderHead(response);\n");
        html.append("    response.render(CssHeaderItem.forReference(\n");
        html.append("        new CssResourceReference(MyComponent.class, \"MyComponent.css\")\n");
        html.append("    ));\n");
        html.append("}\n");
        html.append("</code></pre>");
        html.append("</div>");

        html.append("<div class='alert alert-warning'>");
        html.append("<h6>3. JavaScript Externo (Arquivo .js)</h6>");
        html.append("<pre><code>");
        html.append("// Arquivo: MyComponent.js (mesmo pacote que MyComponent.java)\n");
        html.append("function initMyComponent() {\n");
        html.append("    console.log('MyComponent initialized!');\n");
        html.append("}\n\n");
        html.append("// No componente:\n");
        html.append("@Override\n");
        html.append("public void renderHead(IHeaderResponse response) {\n");
        html.append("    super.renderHead(response);\n");
        html.append("    response.render(JavaScriptHeaderItem.forReference(\n");
        html.append("        new JavaScriptResourceReference(MyComponent.class, \"MyComponent.js\")\n");
        html.append("    ));\n");
        html.append("    // Executar após DOM carregar\n");
        html.append("    response.render(OnDomReadyHeaderItem.forScript(\"initMyComponent();\"));\n");
        html.append("}\n");
        html.append("</code></pre>");
        html.append("</div>");

        html.append("<div class='alert alert-info'>");
        html.append("<h6>4. Biblioteca Externa (CDN)</h6>");
        html.append("<pre><code>");
        html.append("@Override\n");
        html.append("public void renderHead(IHeaderResponse response) {\n");
        html.append("    super.renderHead(response);\n");
        html.append("    \n");
        html.append("    // jQuery via CDN\n");
        html.append("    response.render(JavaScriptHeaderItem.forUrl(\n");
        html.append("        \"https://code.jquery.com/jquery-3.6.0.min.js\"\n");
        html.append("    ));\n");
        html.append("    \n");
        html.append("    // Font Awesome via CDN\n");
        html.append("    response.render(CssHeaderItem.forUrl(\n");
        html.append("        \"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css\"\n");
        html.append("    ));\n");
        html.append("}\n");
        html.append("</code></pre>");
        html.append("</div>");

        html.append("<div class='alert alert-danger'>");
        html.append("<h6>5. Dependências entre Recursos</h6>");
        html.append("<p>Você pode especificar dependências para garantir ordem de carregamento:</p>");
        html.append("<pre><code>");
        html.append("public class MyJavaScriptReference extends JavaScriptResourceReference {\n");
        html.append("    public MyJavaScriptReference() {\n");
        html.append("        super(MyComponent.class, \"my-script.js\");\n");
        html.append("    }\n");
        html.append("    \n");
        html.append("    @Override\n");
        html.append("    public List&lt;HeaderItem&gt; getDependencies() {\n");
        html.append("        List&lt;HeaderItem&gt; dependencies = new ArrayList&lt;&gt;();\n");
        html.append("        // Garante que jQuery carrega antes\n");
        html.append("        dependencies.add(JavaScriptHeaderItem.forReference(\n");
        html.append("            Application.get().getJavaScriptLibrarySettings()\n");
        html.append("                .getJQueryReference()\n");
        html.append("        ));\n");
        html.append("        return dependencies;\n");
        html.append("    }\n");
        html.append("}\n");
        html.append("</code></pre>");
        html.append("</div>");

        html.append("<h6>Tipos de HeaderItem:</h6>");
        html.append("<table class='table table-striped'>");
        html.append("<thead><tr><th>Tipo</th><th>Descrição</th><th>Quando Usar</th></tr></thead>");
        html.append("<tbody>");
        html.append("<tr><td><code>CssHeaderItem</code></td><td>CSS externo ou inline</td><td>Estilos</td></tr>");
        html.append(
                "<tr><td><code>JavaScriptHeaderItem</code></td><td>JavaScript externo ou inline</td><td>Scripts</td></tr>");
        html.append(
                "<tr><td><code>OnDomReadyHeaderItem</code></td><td>Executa quando DOM está pronto</td><td>Inicialização</td></tr>");
        html.append(
                "<tr><td><code>OnLoadHeaderItem</code></td><td>Executa no onload da janela</td><td>Após tudo carregar</td></tr>");
        html.append(
                "<tr><td><code>OnEventHeaderItem</code></td><td>Executa em evento específico</td><td>Eventos customizados</td></tr>");
        html.append(
                "<tr><td><code>StringHeaderItem</code></td><td>String literal no head</td><td>Meta tags, etc</td></tr>");
        html.append(
                "<tr><td><code>MetaDataHeaderItem</code></td><td>Meta tags específicas</td><td>SEO, viewport</td></tr>");
        html.append("</tbody>");
        html.append("</table>");

        html.append("<h6>Exemplo Prático: Componente Auto-suficiente</h6>");
        html.append("<pre><code>");
        html.append("public class RichComponent extends Panel {\n");
        html.append("    public RichComponent(String id) {\n");
        html.append("        super(id);\n");
        html.append("        setOutputMarkupId(true);\n");
        html.append("    }\n");
        html.append("    \n");
        html.append("    @Override\n");
        html.append("    public void renderHead(IHeaderResponse response) {\n");
        html.append("        super.renderHead(response);\n");
        html.append("        \n");
        html.append("        // CSS do componente\n");
        html.append("        response.render(CssHeaderItem.forCSS(\n");
        html.append("            \".rich-component { border: 2px solid blue; padding: 10px; }\",\n");
        html.append("            \"rich-component-css\"\n");
        html.append("        ));\n");
        html.append("        \n");
        html.append("        // JavaScript de inicialização\n");
        html.append("        String script = String.format(\n");
        html.append("            \"$('#%s').fadeIn('slow');\",\n");
        html.append("            getMarkupId()\n");
        html.append("        );\n");
        html.append("        response.render(OnDomReadyHeaderItem.forScript(script));\n");
        html.append("    }\n");
        html.append("}\n");
        html.append("</code></pre>");

        html.append("<h6>Boas Práticas:</h6>");
        html.append("<ul>");
        html.append("<li><strong>Sempre chame super.renderHead():</strong> Para não quebrar a cadeia</li>");
        html.append("<li><strong>Use IDs únicos:</strong> Para CSS/JS inline (evita duplicação)</li>");
        html.append("<li><strong>Prefira ResourceReference:</strong> Para recursos externos</li>");
        html.append("<li><strong>Minimize inline:</strong> Use arquivos .css e .js quando possível</li>");
        html.append("<li><strong>Declare dependências:</strong> Para garantir ordem correta</li>");
        html.append("<li><strong>Use OnDomReady:</strong> Para scripts que manipulam DOM</li>");
        html.append("<li><strong>Considere cache:</strong> ResourceReference usa cache do browser</li>");
        html.append("</ul>");

        html.append("<h6>Problemas Comuns:</h6>");
        html.append("<div class='alert alert-warning'>");
        html.append("<ul>");
        html.append("<li><strong>Recurso não carrega:</strong> Verifique se arquivo está no classpath</li>");
        html.append("<li><strong>Erro 404:</strong> Nome do arquivo ou pacote incorreto</li>");
        html.append("<li><strong>JavaScript não executa:</strong> Use OnDomReady se manipula DOM</li>");
        html.append("<li><strong>CSS não aplica:</strong> Verifique seletores e especificidade</li>");
        html.append("<li><strong>Recursos duplicados:</strong> Use ID único para inline</li>");
        html.append("</ul>");
        html.append("</div>");

        html.append("<h6>Exemplos no Projeto:</h6>");
        html.append("<ul>");
        html.append("<li><code>HeaderContributionsDemoPage</code> - Demonstração completa</li>");
        html.append("<li><code>BasePage</code> - Bootstrap e Font Awesome (se aplicável)</li>");
        html.append("<li>Qualquer componente customizado pode adicionar recursos!</li>");
        html.append("</ul>");

        html.append("<h6>Links Relacionados:</h6>");
        html.append("<ul>");
        html.append(
                "<li><a href='https://wicket.apache.org/guide/header.html' target='_blank'>Wicket Header Contributions Guide</a></li>");
        html.append(
                "<li><a href='https://cwiki.apache.org/confluence/display/WICKET/Wicket+Request+Processing' target='_blank'>Request Processing</a></li>");
        html.append(
                "<li><a href='https://github.com/apache/wicket/tree/master/wicket-examples' target='_blank'>Wicket Examples</a></li>");
        html.append("</ul>");

        return html.toString();
    }
}
