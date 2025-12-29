package com.hvivox.certidoes.documentacao;

/**
 * ====================================================================
 * DOCUMENTAÇÃO: SESSION STORE - MÓDULO 2: ARQUITETURA DO WICKET
 * ====================================================================
 * 
 * Esta classe contém a documentação sobre Session Store no Wicket.
 * 
 * Session Store é responsável por persistir e recuperar o estado das sessões.
 */
public class SessionStoreDocumentacao {

    /**
     * Retorna o conteúdo HTML formatado da documentação sobre Session Store.
     * 
     * @return HTML formatado com a documentação
     */
    public static String getConteudo() {
        StringBuilder html = new StringBuilder();
        html.append("<h6>Session Store</h6>");
        html.append("<p>O <strong>Session Store</strong> é responsável por persistir e recuperar o estado das sessões do Wicket.</p>");
        
        html.append("<h6>O que é Session Store?</h6>");
        html.append("<p>O Session Store determina <strong>onde</strong> e <strong>como</strong> o estado das páginas e componentes é armazenado entre requisições.</p>");
        
        html.append("<h6>Tipos de Session Store:</h6>");
        html.append("<ul>");
        html.append("<li><strong>HttpSessionStore</strong>: Armazena na sessão HTTP do servidor (padrão)</li>");
        html.append("<li><strong>SecondLevelCacheSessionStore</strong>: Usa cache de segundo nível (Hazelcast, etc.)</li>");
        html.append("<li><strong>DiskPageStore</strong>: Armazena em disco</li>");
        html.append("</ul>");
        
        html.append("<h6>Configuração:</h6>");
        html.append("<pre><code>");
        html.append("// Em WicketApplication.init():\n");
        html.append("getStoreSettings().setInmemoryCacheSize(10);\n");
        html.append("// Configura tamanho do cache em memória\n");
        html.append("</code></pre>");
        
        html.append("<h6>Exemplo no Projeto:</h6>");
        html.append("<p>O projeto usa o <strong>HttpSessionStore</strong> padrão, que armazena o estado na sessão HTTP do servidor.</p>");
        html.append("<p>Veja: <code>WicketApplication.java</code> - configurações de sessão</p>");
        
        html.append("<h6>Links Relacionados:</h6>");
        html.append("<ul>");
        html.append("<li><a href=\"https://wicket.apache.org/learn/guide/\" target=\"_blank\">Documentação Oficial do Wicket</a></li>");
        html.append("<li><a href=\"https://cwiki.apache.org/confluence/display/WICKET/Session+Management\" target=\"_blank\">Session Management (Wiki)</a></li>");
        html.append("</ul>");
        
        return html.toString();
    }
}

