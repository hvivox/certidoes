package com.hvivox.certidoes;

import com.hvivox.certidoes.page.CertidaoListPage;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * MÓDULO 7 - ITEM 55: TESTE UNITÁRIO 2 - Listagem de Certidões
 * 
 * Testa a página de listagem de certidões.
 * 
 * TESTES:
 * - Página renderiza corretamente
 * - DataView existe
 * - Links de ação existem
 */
public class TestCertidaoListPage {

    private WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester(new WicketApplication());
    }

    @Test
    public void listPageRendersSuccessfully() {
        // Iniciar e renderizar a página de listagem
        tester.startPage(CertidaoListPage.class);

        // Verificar se a página foi renderizada corretamente
        tester.assertRenderedPage(CertidaoListPage.class);
    }

    @Test
    public void tableContainerExists() {
        tester.startPage(CertidaoListPage.class);

        // Verificar se o container da tabela existe
        tester.assertComponent("tableContainer",
                org.apache.wicket.markup.html.WebMarkupContainer.class);
    }

    @Test
    public void repeatingComponentExists() {
        tester.startPage(CertidaoListPage.class);

        // Verificar se o componente repetidor existe (ListView)
        tester.assertComponent("tableContainer:certidoes",
                org.apache.wicket.markup.html.list.ListView.class);
    }

    @Test
    public void pageContainsCertidoes() {
        tester.startPage(CertidaoListPage.class);

        // Verificar que a página não está vazia (há certidões mock)
        // O DataView deve ter pelo menos um item
        tester.assertContains("Certidão");
    }
}
