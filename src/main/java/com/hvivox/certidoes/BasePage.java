package com.hvivox.certidoes;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public abstract class BasePage extends WebPage {

    public BasePage() {
        add(new Label("appName", "Certidões"));
        add(new Label("appTitle", "Certidões"));
    }
}
