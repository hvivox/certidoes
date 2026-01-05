package com.hvivox.certidoes.page;

import com.hvivox.certidoes.BasePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * MÓDULO 7 - ITEM 56: URLs E BOOKMARKABLE PAGES
 * 
 * Esta página demonstra conceitos de URLs no Apache Wicket:
 * - Bookmarkable Pages (páginas que podem ser marcadas/compartilhadas)
 * - PageParameters (parâmetros na URL)
 * - URL encoding
 * - Diferentes tipos de links
 * 
 * CONCEITOS:
 * 
 * 1. BOOKMARKABLE PAGE:
 * - Página que pode ser acessada diretamente via URL
 * - Pode ser marcada nos favoritos do navegador
 * - Pode ser compartilhada
 * - URL limpa e previsível
 * 
 * 2. PAGE PARAMETERS:
 * - Parâmetros passados na URL
 * - Exemplo: /page?id=123&tipo=CPF
 * - Wicket converte automaticamente
 * 
 * 3. MOUNTED PAGES:
 * - URLs customizadas configuradas na Application
 * - Exemplo: /certidoes/detalhe/123 (mais limpo que ?id=123)
 * 
 * DIFERENÇA:
 * - Página Normal: Não pode ser acessada diretamente, apenas por navegação
 * - Bookmarkable: Pode ser acessada diretamente pela URL
 */
public class URLsDemoPage extends BasePage {

    private static final long serialVersionUID = 1L;

    public URLsDemoPage(final PageParameters parameters) {
        super();

        // Processar parâmetros recebidos da URL
        String nome = parameters.get("nome").toString("Visitante");
        String idStr = parameters.get("id").toString("0");
        Integer id = 0;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            id = 0;
        }
        String tipo = parameters.get("tipo").toString("N/A");

        // Exibir parâmetros recebidos
        add(new Label("pageTitle", "URLs e Bookmarkable Pages"));
        add(new Label("nomeParam", nome));
        add(new Label("idParam", id.toString()));
        add(new Label("tipoParam", tipo));

        // Exemplo 1: Link bookmarkable simples (sem parâmetros)
        add(new BookmarkablePageLink<>("linkSimples", URLsDemoPage.class));

        // Exemplo 2: Link bookmarkable com parâmetros
        PageParameters params2 = new PageParameters();
        params2.add("nome", "João Silva");
        params2.add("id", 123);
        params2.add("tipo", "CPF");
        add(new BookmarkablePageLink<>("linkComParametros", URLsDemoPage.class, params2));

        // Exemplo 3: Link para outra página bookmarkable
        add(new BookmarkablePageLink<>("linkOutraPagina", CertidaoListPage.class));

        // Exemplo 4: Link com múltiplos parâmetros
        PageParameters params4 = new PageParameters();
        params4.add("nome", "Maria Oliveira");
        params4.add("id", 456);
        params4.add("tipo", "CNPJ");
        params4.add("status", "ATIVO");
        add(new BookmarkablePageLink<>("linkMultiplosParams", URLsDemoPage.class, params4));

        // URLs de exemplo
        add(new Label("urlAtual", getUrlAtual()));
        add(new Label("urlExemplos", getUrlExemplos()).setEscapeModelStrings(false));

        // Documentação
        add(new Label("docInfo", getDocumentacaoInfo()).setEscapeModelStrings(false));
    }

    private String getUrlAtual() {
        return getRequest().getUrl().toString();
    }

    private String getUrlExemplos() {
        return "<div class='alert alert-info'>" +
                "<h6><i class='fas fa-link'></i> Exemplos de URLs Bookmarkable:</h6>" +
                "<ul class='mb-0'>" +
                "<li><code>/wicket/page?0</code> - URL padrão do Wicket</li>" +
                "<li><code>/wicket/page?0&nome=João&id=123</code> - Com parâmetros</li>" +
                "<li><code>/certidoes/detalhe/123</code> - URL mounted (mais limpa)</li>" +
                "<li><code>/certidoes/lista</code> - URL mounted simples</li>" +
                "</ul>" +
                "</div>";
    }

    private String getDocumentacaoInfo() {
        return "<div class='card'>" +
                "<div class='card-header bg-primary text-white'>" +
                "<h5 class='mb-0'><i class='fas fa-book'></i> Conceitos de URLs no Wicket</h5>" +
                "</div>" +
                "<div class='card-body'>" +

                "<h6>1. Bookmarkable Page</h6>" +
                "<p>Página que pode ser acessada diretamente via URL. Para criar:</p>" +
                "<pre class='bg-light p-2 rounded'><code>public class MinhaPage extends WebPage {" +
                "\n    public MinhaPage(PageParameters params) {" +
                "\n        super(params);" +
                "\n        // Processar parâmetros" +
                "\n    }" +
                "\n}</code></pre>" +

                "<h6 class='mt-3'>2. PageParameters</h6>" +
                "<p>Parâmetros passados na URL:</p>" +
                "<pre class='bg-light p-2 rounded'><code>// Ler parâmetros" +
                "\nString nome = parameters.get(\"nome\").toString(\"padrão\");" +
                "\nInteger id = parameters.get(\"id\").toInteger(0);" +
                "\n\n// Criar link com parâmetros" +
                "\nPageParameters params = new PageParameters();" +
                "\nparams.add(\"nome\", \"João\");" +
                "\nparams.add(\"id\", 123);" +
                "\nnew BookmarkablePageLink(\"link\", MinhaPage.class, params);</code></pre>" +

                "<h6 class='mt-3'>3. Mounted Pages (URLs Limpas)</h6>" +
                "<p>Configure URLs customizadas em <code>WicketApplication.init()</code>:</p>" +
                "<pre class='bg-light p-2 rounded'><code>// Montar página com URL customizada" +
                "\nmountPage(\"/certidoes/lista\", CertidaoListPage.class);" +
                "\nmountPage(\"/certidoes/detalhe\", CertidaoDetailPage.class);" +
                "\n\n// URL com parâmetros" +
                "\nmountPage(\"/certidoes/${id}\", CertidaoDetailPage.class);" +
                "\n// Acesso: /certidoes/123</code></pre>" +

                "<h6 class='mt-3'>4. Vantagens de Bookmarkable Pages</h6>" +
                "<ul>" +
                "<li>✅ URLs podem ser marcadas nos favoritos</li>" +
                "<li>✅ URLs podem ser compartilhadas</li>" +
                "<li>✅ Melhor para SEO</li>" +
                "<li>✅ Deep linking</li>" +
                "<li>✅ Histórico do navegador funciona melhor</li>" +
                "</ul>" +

                "<h6 class='mt-3'>5. Quando Usar</h6>" +
                "<div class='row'>" +
                "<div class='col-md-6'>" +
                "<strong class='text-success'>Use Bookmarkable:</strong>" +
                "<ul>" +
                "<li>Páginas de listagem</li>" +
                "<li>Páginas de detalhes</li>" +
                "<li>Resultados de busca</li>" +
                "<li>Páginas públicas</li>" +
                "</ul>" +
                "</div>" +
                "<div class='col-md-6'>" +
                "<strong class='text-warning'>Use Normal:</strong>" +
                "<ul>" +
                "<li>Páginas intermediárias</li>" +
                "<li>Wizards/formulários multi-step</li>" +
                "<li>Páginas com estado complexo</li>" +
                "<li>Modais/popups</li>" +
                "</ul>" +
                "</div>" +
                "</div>" +

                "</div>" +
                "</div>";
    }
}
