package com.hvivox.certidoes.documentacao;

/**
 * ====================================================================
 * DOCUMENTAÇÃO: AJAX NO WICKET - MÓDULO 6
 * ====================================================================
 * 
 * Esta classe contém a documentação sobre Ajax e Componentes Ricos no Wicket.
 * 
 * Ajax (Asynchronous JavaScript and XML) permite atualizar partes da página
 * sem recarregar a página inteira, proporcionando uma experiência mais fluida.
 */
public class AjaxDocumentacao {

    /**
     * Retorna o conteúdo HTML formatado da documentação sobre Ajax.
     * 
     * @return HTML formatado com a documentação
     */
    public static String getConteudo() {
        StringBuilder html = new StringBuilder();

        html.append("<h6>Ajax no Apache Wicket</h6>");
        html.append("<p>O Wicket fornece suporte nativo e poderoso para Ajax, abstraindo toda a complexidade ");
        html.append("do JavaScript e XMLHttpRequest, permitindo que você escreva código Ajax puramente em Java.</p>");

        html.append("<h6>O que é Ajax?</h6>");
        html.append("<p><strong>Ajax (Asynchronous JavaScript and XML)</strong> é uma técnica que permite:");
        html.append("<ul>");
        html.append("<li>Atualizar partes da página sem recarregar tudo</li>");
        html.append("<li>Comunicação assíncrona com o servidor</li>");
        html.append("<li>Interface mais responsiva e fluida</li>");
        html.append("<li>Melhor experiência do usuário</li>");
        html.append("<li>Economia de banda e recursos</li>");
        html.append("</ul>");

        html.append("<h6>Vantagens do Ajax no Wicket:</h6>");
        html.append("<ul>");
        html.append("<li><strong>Abstração Total:</strong> Você escreve Java, o Wicket gera o JavaScript</li>");
        html.append("<li><strong>Type-Safe:</strong> Sem erros de digitação em JavaScript</li>");
        html.append("<li><strong>Fácil de Usar:</strong> Componentes Ajax prontos para usar</li>");
        html.append("<li><strong>Fallback Automático:</strong> Funciona mesmo sem JavaScript habilitado</li>");
        html.append("<li><strong>Integrado:</strong> Ajax é parte natural do framework</li>");
        html.append("</ul>");

        html.append("<h6>Componentes Ajax Principais:</h6>");
        html.append("<div class='alert alert-primary'>");
        html.append("<h6>1. AjaxLink</h6>");
        html.append("<p>Link que executa ação Ajax sem recarregar a página.</p>");
        html.append("<pre><code>");
        html.append("AjaxLink&lt;Void&gt; link = new AjaxLink&lt;Void&gt;(\"ajaxLink\") {\n");
        html.append("    @Override\n");
        html.append("    public void onClick(AjaxRequestTarget target) {\n");
        html.append("        // Ação a executar\n");
        html.append("        contador++;\n");
        html.append("        // Atualizar componentes na página\n");
        html.append("        target.add(labelContador);\n");
        html.append("    }\n");
        html.append("};\n");
        html.append("</code></pre>");
        html.append("</div>");

        html.append("<div class='alert alert-success'>");
        html.append("<h6>2. AjaxButton</h6>");
        html.append("<p>Botão que submete formulário com Ajax.</p>");
        html.append("<pre><code>");
        html.append("AjaxButton button = new AjaxButton(\"save\") {\n");
        html.append("    @Override\n");
        html.append("    protected void onSubmit(AjaxRequestTarget target, Form&lt;?&gt; form) {\n");
        html.append("        // Salvar dados\n");
        html.append("        success(\"Dados salvos com sucesso!\");\n");
        html.append("        target.add(feedbackPanel);\n");
        html.append("    }\n");
        html.append("    \n");
        html.append("    @Override\n");
        html.append("    protected void onError(AjaxRequestTarget target, Form&lt;?&gt; form) {\n");
        html.append("        target.add(feedbackPanel);\n");
        html.append("    }\n");
        html.append("};\n");
        html.append("</code></pre>");
        html.append("</div>");

        html.append("<div class='alert alert-warning'>");
        html.append("<h6>3. IndicatingAjaxButton</h6>");
        html.append("<p>Botão Ajax com indicador de loading automático.</p>");
        html.append("<pre><code>");
        html.append("IndicatingAjaxButton button = new IndicatingAjaxButton(\"process\") {\n");
        html.append("    @Override\n");
        html.append("    protected void onSubmit(AjaxRequestTarget target, Form&lt;?&gt; form) {\n");
        html.append("        // Processa operação demorada\n");
        html.append("        processarDados();\n");
        html.append("        target.add(resultado);\n");
        html.append("    }\n");
        html.append("};\n");
        html.append("</code></pre>");
        html.append("</div>");

        html.append("<div class='alert alert-info'>");
        html.append("<h6>4. AjaxFormComponentUpdatingBehavior</h6>");
        html.append("<p>Atualiza componente do formulário em eventos específicos.</p>");
        html.append("<pre><code>");
        html.append("TextField&lt;String&gt; campo = new TextField&lt;&gt;(\"campo\", model);\n");
        html.append("campo.add(new AjaxFormComponentUpdatingBehavior(\"keyup\") {\n");
        html.append("    @Override\n");
        html.append("    protected void onUpdate(AjaxRequestTarget target) {\n");
        html.append("        // Atualizar outros componentes\n");
        html.append("        target.add(preview);\n");
        html.append("    }\n");
        html.append("});\n");
        html.append("</code></pre>");
        html.append("</div>");

        html.append("<div class='alert alert-secondary'>");
        html.append("<h6>5. AbstractAjaxTimerBehavior</h6>");
        html.append("<p>Atualiza componente automaticamente em intervalos.</p>");
        html.append("<pre><code>");
        html.append("label.add(new AbstractAjaxTimerBehavior(Duration.seconds(5)) {\n");
        html.append("    @Override\n");
        html.append("    protected void onTimer(AjaxRequestTarget target) {\n");
        html.append("        // Atualizar dados\n");
        html.append("        contador++;\n");
        html.append("        target.add(label);\n");
        html.append("    }\n");
        html.append("});\n");
        html.append("</code></pre>");
        html.append("</div>");

        html.append("<h6>AjaxRequestTarget - O Coração do Ajax no Wicket:</h6>");
        html.append("<p>O <code>AjaxRequestTarget</code> é o objeto que você usa para especificar ");
        html.append("quais componentes devem ser atualizados na resposta Ajax.</p>");
        html.append("<pre><code>");
        html.append("// Adicionar componentes para atualização\n");
        html.append("target.add(component1);\n");
        html.append("target.add(component2, component3);\n\n");
        html.append("// Executar JavaScript\n");
        html.append("target.appendJavaScript(\"alert('Atualizado!');\");\n");
        html.append("target.prependJavaScript(\"console.log('Antes');\");\n\n");
        html.append("// Focar em componente\n");
        html.append("target.focusComponent(textField);\n\n");
        html.append("// Adicionar listener\n");
        html.append("target.addListener(new AjaxRequestTarget.IListener() {\n");
        html.append(
                "    public void onAfterRespond(Map&lt;String, Component&gt; map, IJavaScriptResponse response) {\n");
        html.append("        // Executar após resposta\n");
        html.append("    }\n");
        html.append("});\n");
        html.append("</code></pre>");

        html.append("<h6>Requisitos para Componentes Ajax:</h6>");
        html.append("<div class='alert alert-danger'>");
        html.append("<h6><i class='fas fa-exclamation-triangle'></i> Importante:</h6>");
        html.append("<p>Para que um componente possa ser atualizado via Ajax, ele DEVE ter:</p>");
        html.append("<pre><code>");
        html.append("component.setOutputMarkupId(true);\n");
        html.append("// Ou, se o componente pode estar invisível:\n");
        html.append("component.setOutputMarkupPlaceholderTag(true);\n");
        html.append("</code></pre>");
        html.append("<p>Sem isso, o Wicket não consegue localizar o componente no DOM para atualizá-lo!</p>");
        html.append("</div>");

        html.append("<h6>Boas Práticas com Ajax:</h6>");
        html.append("<ul>");
        html.append("<li><strong>setOutputMarkupId(true):</strong> Sempre para componentes atualizáveis</li>");
        html.append("<li><strong>Atualizar o mínimo:</strong> Apenas componentes que mudaram</li>");
        html.append("<li><strong>Feedback visual:</strong> Use IndicatingAjax* para operações demoradas</li>");
        html.append("<li><strong>Tratamento de erros:</strong> Sempre implemente onError()</li>");
        html.append("<li><strong>Fallback:</strong> Use AjaxFallback* quando apropriado</li>");
        html.append("<li><strong>Throttle/Debounce:</strong> Use AjaxFormComponentUpdatingBehavior com cuidado</li>");
        html.append("</ul>");

        html.append("<h6>Exemplos no Projeto:</h6>");
        html.append("<ul>");
        html.append("<li><code>AgrupamentoDemoPage</code> - AjaxLink para alternar visibilidade</li>");
        html.append("<li><code>ComponentesDemoPage</code> - AjaxFormComponentUpdatingBehavior em TextField</li>");
        html.append("<li><code>AjaxDemoPage</code> - Demonstração completa de todos os componentes</li>");
        html.append("</ul>");

        html.append("<h6>Links Relacionados:</h6>");
        html.append("<ul>");
        html.append(
                "<li><a href='https://wicket.apache.org/learn/guide/ajax.html' target='_blank'>Wicket Ajax Guide</a></li>");
        html.append(
                "<li><a href='https://cwiki.apache.org/confluence/display/WICKET/Ajax' target='_blank'>Ajax Wiki</a></li>");
        html.append(
                "<li><a href='https://github.com/apache/wicket/tree/master/wicket-examples/src/main/java/org/apache/wicket/examples/ajax' target='_blank'>Ajax Examples</a></li>");
        html.append("</ul>");

        return html.toString();
    }
}

