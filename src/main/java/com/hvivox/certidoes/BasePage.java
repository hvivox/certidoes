package com.hvivox.certidoes;

import com.hvivox.certidoes.component.FeedbackPanelCustomizado;
import com.hvivox.certidoes.page.AgrupamentoDemoPage;
import com.hvivox.certidoes.page.AjaxDemoPage;
import com.hvivox.certidoes.page.CertidaoFormPage;
import com.hvivox.certidoes.page.CertidaoListPage;
import com.hvivox.certidoes.page.ComponentesDemoPage;
import com.hvivox.certidoes.page.ConvertersDemoPage;
import com.hvivox.certidoes.page.HeaderContributionsDemoPage;
import com.hvivox.certidoes.page.ModoProducaoDemoPage;
import com.hvivox.certidoes.page.RecursosCustomizadosDemoPage;
import com.hvivox.certidoes.page.URLsDemoPage;
import com.hvivox.certidoes.page.TroubleshootingDemoPage;
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

        // wicket:id="linkAjax" no HTML - Link para página de demonstração de Ajax
        add(new BookmarkablePageLink<>("linkAjax", AjaxDemoPage.class));

        // wicket:id="linkHeaderContributions" no HTML - Link para página de
        // demonstração de
        // Header Contributions
        add(new BookmarkablePageLink<>("linkHeaderContributions", HeaderContributionsDemoPage.class));

        // wicket:id="linkRecursosCustomizados" no HTML - Link para página de
        // demonstração de
        // Customização de Recursos
        add(new BookmarkablePageLink<>("linkRecursosCustomizados", RecursosCustomizadosDemoPage.class));

        // wicket:id="linkConverters" no HTML - Link para página de demonstração de
        // Converters
        add(new BookmarkablePageLink<>("linkConverters", ConvertersDemoPage.class));

        // wicket:id="linkURLs" no HTML - Link para página de demonstração de URLs
        add(new BookmarkablePageLink<>("linkURLs", URLsDemoPage.class));

        // wicket:id="linkModoProducao" no HTML - Link para página de demonstração de
        // Modo de Produção
        add(new BookmarkablePageLink<>("linkModoProducao", ModoProducaoDemoPage.class));

        // wicket:id="linkTroubleshooting" no HTML - Link para página de Troubleshooting
        add(new BookmarkablePageLink<>("linkTroubleshooting", TroubleshootingDemoPage.class));
    }
}
