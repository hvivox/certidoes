package com.hvivox.certidoes;

import com.hvivox.certidoes.component.FeedbackPanelCustomizado;
import com.hvivox.certidoes.page.AgrupamentoDemoPage;
import com.hvivox.certidoes.page.CertidaoFormPage;
import com.hvivox.certidoes.page.CertidaoListPage;
import com.hvivox.certidoes.page.ComponentesDemoPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public abstract class BasePage extends WebPage {

    public BasePage() {
        // Label para o nome da aplicação no cabeçalho
        // wicket:id="appName" no HTML
        add(new Label("appName", "Certidões"));

        // Label para o título da página (tag <title>)
        // wicket:id="appTitle" no HTML
        add(new Label("appTitle", "Certidões"));

        // FeedbackPanel customizado para mensagens de sucesso/erro
        // MÓDULO 4 - ITEM 7: Feedback Avançado
        add(new FeedbackPanelCustomizado("feedback"));

        // Links do menu
        add(new BookmarkablePageLink<>("linkHome", HomePage.class));

        // wicket:id="linkListar" no HTML
        add(new BookmarkablePageLink<>("linkListar", CertidaoListPage.class));

        // wicket:id="linkNova" no HTML
        add(new BookmarkablePageLink<>("linkNova", CertidaoFormPage.class));

        // wicket:id="linkComponentes" no HTML - Link para página de demonstração de
        // componentes
        add(new BookmarkablePageLink<>("linkComponentes", ComponentesDemoPage.class));

        // wicket:id="linkAgrupamento" no HTML - Link para página de demonstração de
        // agrupamento
        add(new BookmarkablePageLink<>("linkAgrupamento", AgrupamentoDemoPage.class));
    }
}
