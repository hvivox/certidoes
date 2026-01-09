package com.hvivox.certidoes.documentacao;

/**
 * ====================================================================
 * DOCUMENTAÇÃO: ARQUITETURA DO WICKET - MÓDULO 2: ARQUITETURA DO WICKET
 * ====================================================================
 * 
 * Esta classe contém a documentação sobre a Arquitetura do Wicket.
 * 
 * O Wicket possui uma arquitetura robusta baseada em componentes, sessões e ciclo de requisições.
 */
public class ArquiteturaDocumentacao {

    /**
     * Retorna o conteúdo HTML formatado da documentação sobre Arquitetura do Wicket.
     * 
     * @return HTML formatado com a documentação
     */
    public static String getConteudo() {
        StringBuilder html = new StringBuilder();
        html.append("<h6>Arquitetura do Wicket</h6>");
        html.append("<p>O Wicket possui uma arquitetura robusta baseada em componentes, sessões e ciclo de requisições.</p>");
        
        html.append("<h6>Componentes Principais da Arquitetura:</h6>");
        html.append("<ul>");
        html.append("<li><strong>Application</strong>: Classe principal da aplicação (<code>WicketApplication</code>)</li>");
        html.append("<li><strong>Session</strong>: Armazena dados da sessão do usuário (<code>CertidoesSession</code>)</li>");
        html.append("<li><strong>Page</strong>: Representa uma página da aplicação</li>");
        html.append("<li><strong>Component</strong>: Elementos reutilizáveis da interface</li>");
        html.append("<li><strong>Model</strong>: Fornece dados aos componentes</li>");
        html.append("<li><strong>Behavior</strong>: Adiciona comportamento aos componentes</li>");
        html.append("</ul>");
        
        html.append("<h6>Fluxo de uma Requisição:</h6>");
        html.append("<ol>");
        html.append("<li><strong>Cliente faz requisição HTTP</strong></li>");
        html.append("<li><strong>WicketFilter intercepta</strong> a requisição</li>");
        html.append("<li><strong>RequestCycle</strong> é criado e processado</li>");
        html.append("<li><strong>Wicket identifica</strong> a página/componente alvo</li>");
        html.append("<li><strong>Wicket restaura</strong> o estado da página (se necessário)</li>");
        html.append("<li><strong>Processa eventos</strong> (cliques, submissões, etc.)</li>");
        html.append("<li><strong>Renderiza</strong> a resposta HTML</li>");
        html.append("<li><strong>Envia HTML</strong> ao cliente</li>");
        html.append("</ol>");
        
        html.append("<h6>RequestCycleListener:</h6>");
        html.append("<p>Permite interceptar eventos do ciclo de requisição:</p>");
        html.append("<ul>");
        html.append("<li><code>onBeginRequest()</code> - Início da requisição</li>");
        html.append("<li><code>onRequestHandlerResolved()</code> - Handler identificado</li>");
        html.append("<li><code>onRequestHandlerExecuted()</code> - Handler executado</li>");
        html.append("<li><code>onEndRequest()</code> - Fim da requisição</li>");
        html.append("<li><code>onException()</code> - Tratamento de erros</li>");
        html.append("</ul>");
        
        html.append("<h6>Exemplo no Projeto:</h6>");
        html.append("<p>Veja <code>CertidoesRequestCycleListener.java</code> que demonstra como interceptar o ciclo de requisição.</p>");
        html.append("<p>Configurado em: <code>WicketApplication.init()</code></p>");
        
        html.append("<h6>Aninhamento de Componentes:</h6>");
        html.append("<p>Componentes podem conter outros componentes, formando uma árvore hierárquica:</p>");
        html.append("<pre><code>");
        html.append("Page\n");
        html.append("  └─ Form\n");
        html.append("      ├─ TextField\n");
        html.append("      ├─ Button\n");
        html.append("      └─ Panel\n");
        html.append("          └─ Label\n");
        html.append("</code></pre>");
        
        html.append("<h6>Links Relacionados:</h6>");
        html.append("<ul>");
        html.append("<li><a href=\"https://wicket.apache.org/learn/guide/\" target=\"_blank\">Documentação Oficial do Wicket</a></li>");
        html.append("<li><a href=\"https://cwiki.apache.org/confluence/display/WICKET/Request+processing\" target=\"_blank\">Request Processing (Wiki)</a></li>");
        html.append("</ul>");
        
        return html.toString();
    }
}

