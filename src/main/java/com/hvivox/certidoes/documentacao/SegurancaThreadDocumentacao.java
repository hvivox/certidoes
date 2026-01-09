package com.hvivox.certidoes.documentacao;

/**
 * ====================================================================
 * DOCUMENTAÇÃO: SEGURANÇA DE THREAD - MÓDULO 2: ARQUITETURA DO WICKET
 * ====================================================================
 * 
 * Esta classe contém a documentação sobre Segurança de Thread no Wicket.
 * 
 * O Wicket garante thread-safety através de várias estratégias.
 */
public class SegurancaThreadDocumentacao {

    /**
     * Retorna o conteúdo HTML formatado da documentação sobre Segurança de Thread.
     * 
     * @return HTML formatado com a documentação
     */
    public static String getConteudo() {
        StringBuilder html = new StringBuilder();
        html.append("<h6>Segurança de Thread no Wicket</h6>");
        html.append("<p>O Wicket garante <strong>thread-safety</strong> através de várias estratégias para proteger o estado da aplicação.</p>");
        
        html.append("<h6>Como o Wicket garante Thread-Safety?</h6>");
        html.append("<ul>");
        html.append("<li><strong>Isolamento de Sessão</strong>: Cada sessão é isolada e não compartilha estado</li>");
        html.append("<li><strong>Serialização</strong>: Páginas e componentes são serializados entre requisições</li>");
        html.append("<li><strong>Request Cycle</strong>: Cada requisição é processada em uma thread separada</li>");
        html.append("<li><strong>Session Store</strong>: Estado armazenado de forma thread-safe</li>");
        html.append("</ul>");
        
        html.append("<h6>Boas Práticas:</h6>");
        html.append("<ul>");
        html.append("<li><strong>Não compartilhar estado</strong> entre requisições (use Session ou Application)</li>");
        html.append("<li><strong>Campos transient</strong>: Marque campos não-serializáveis como <code>transient</code></li>");
        html.append("<li><strong>Stateless pages</strong>: Use páginas stateless quando possível</li>");
        html.append("</ul>");
        
        html.append("<h6>Exemplo no Projeto:</h6>");
        html.append("<pre><code>");
        html.append("// Repositório marcado como transient\n");
        html.append("private transient CertidaoRepository repository;\n");
        html.append("// Isso evita problemas de serialização\n");
        html.append("</code></pre>");
        html.append("<p>Veja: <code>CertidaoListPage.java</code>, <code>CertidaoFormPage.java</code></p>");
        
        html.append("<h6>Links Relacionados:</h6>");
        html.append("<ul>");
        html.append("<li><a href=\"https://wicket.apache.org/learn/guide/\" target=\"_blank\">Documentação Oficial do Wicket</a></li>");
        html.append("<li><a href=\"https://cwiki.apache.org/confluence/display/WICKET/Thread+safety\" target=\"_blank\">Thread Safety (Wiki)</a></li>");
        html.append("</ul>");
        
        return html.toString();
    }
}

