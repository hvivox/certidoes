package com.hvivox.certidoes.documentacao;

/**
 * ====================================================================
 * DOCUMENTAÇÃO: MVC DO JEITO WICKET - MÓDULO 2: ARQUITETURA DO WICKET
 * ====================================================================
 * 
 * Esta classe contém a documentação sobre MVC (Model-View-Controller) no Wicket.
 * 
 * O Wicket implementa o padrão MVC de forma única e poderosa.
 */
public class MVCDocumentacao {

    /**
     * Retorna o conteúdo HTML formatado da documentação sobre MVC do jeito Wicket.
     * 
     * @return HTML formatado com a documentação
     */
    public static String getConteudo() {
        StringBuilder html = new StringBuilder();
        html.append("<h6>MVC do jeito Wicket</h6>");
        html.append("<p>O Wicket implementa o padrão <strong>MVC (Model-View-Controller)</strong> de forma única e poderosa.</p>");
        
        html.append("<h6>Como o Wicket implementa MVC?</h6>");
        html.append("<ul>");
        html.append("<li><strong>Model (Modelo)</strong>: <code>IModel&lt;T&gt;</code> - fornece os dados</li>");
        html.append("<li><strong>View (Visão)</strong>: Arquivo HTML (<code>.html</code>) - define a apresentação</li>");
        html.append("<li><strong>Controller (Controlador)</strong>: Classe Java (ex: <code>Page.java</code>) - define a lógica</li>");
        html.append("</ul>");
        
        html.append("<h6>A Tríade do Wicket:</h6>");
        html.append("<p>Cada componente no Wicket é composto por três partes:</p>");
        html.append("<ol>");
        html.append("<li><strong>Component</strong>: Classe Java que define o comportamento</li>");
        html.append("<li><strong>Markup</strong>: Arquivo HTML que define a estrutura visual</li>");
        html.append("<li><strong>Model</strong>: <code>IModel</code> que fornece os dados</li>");
        html.append("</ol>");
        
        html.append("<h6>Exemplo Prático:</h6>");
        html.append("<pre><code>");
        html.append("// Java (Controller)\n");
        html.append("add(new Label(\"nome\", new PropertyModel<>(pessoa, \"nome\")));\n\n");
        html.append("// HTML (View)\n");
        html.append("&lt;span wicket:id=\"nome\"&gt;Nome&lt;/span&gt;\n\n");
        html.append("// Model (dados)\n");
        html.append("PropertyModel fornece o valor de pessoa.getNome()\n");
        html.append("</code></pre>");
        
        html.append("<h6>Vantagens do MVC no Wicket:</h6>");
        html.append("<ul>");
        html.append("<li><strong>Separação de responsabilidades</strong>: Lógica separada da apresentação</li>");
        html.append("<li><strong>Reutilização</strong>: Componentes podem ser reutilizados</li>");
        html.append("<li><strong>Testabilidade</strong>: Fácil de testar cada parte separadamente</li>");
        html.append("<li><strong>Manutenibilidade</strong>: Mudanças em uma parte não afetam outras</li>");
        html.append("</ul>");
        
        html.append("<h6>Exemplo no Projeto:</h6>");
        html.append("<p>Veja o componente <code>CertidaoCard</code> que demonstra a tríade:</p>");
        html.append("<ul>");
        html.append("<li><strong>Component</strong>: <code>CertidaoCard.java</code></li>");
        html.append("<li><strong>Markup</strong>: <code>CertidaoCard.html</code></li>");
        html.append("<li><strong>Model</strong>: <code>IModel&lt;Certidao&gt;</code></li>");
        html.append("</ul>");
        
        html.append("<h6>Links Relacionados:</h6>");
        html.append("<ul>");
        html.append("<li><a href=\"https://wicket.apache.org/learn/guide/\" target=\"_blank\">Documentação Oficial do Wicket</a></li>");
        html.append("<li><a href=\"https://cwiki.apache.org/confluence/display/WICKET/Wicket+and+MVC\" target=\"_blank\">Wicket e MVC (Wiki)</a></li>");
        html.append("</ul>");
        
        return html.toString();
    }
}

