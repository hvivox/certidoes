package com.hvivox.certidoes;

import com.hvivox.certidoes.page.CertidaoFormPage;
import com.hvivox.certidoes.page.CertidaoListPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public abstract class BasePage extends WebPage {

    public BasePage() {
        add(new Label("appName", "Certidões"));
        add(new Label("appTitle", "Certidões"));
        
        // FeedbackPanel para mensagens de sucesso/erro
        add(new FeedbackPanel("feedback"));
        
        // Links do menu
        add(new BookmarkablePageLink<>("linkHome", HomePage.class));
        add(new BookmarkablePageLink<>("linkListar", CertidaoListPage.class));
        add(new BookmarkablePageLink<>("linkNova", CertidaoFormPage.class));
    }
}
