package com.hvivox.certidoes;

import com.hvivox.certidoes.page.*;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * MÓDULO 7 - ITEM 55: TESTE UNITÁRIO 4 - Páginas de Demonstração
 * 
 * Testa todas as páginas de demonstração do projeto.
 * 
 * TESTES:
 * - Todas as páginas demo renderizam corretamente
 * - Sem erros de componentes ausentes
 */
public class TestDemoPages {

    private WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester(new WicketApplication());
    }

    @Test
    public void homePageRendersSuccessfully() {
        tester.startPage(HomePage.class);
        tester.assertRenderedPage(HomePage.class);
        tester.assertNoErrorMessage();
    }

    @Test
    public void componentesDemoPageRendersSuccessfully() {
        tester.startPage(ComponentesDemoPage.class);
        tester.assertRenderedPage(ComponentesDemoPage.class);
        tester.assertNoErrorMessage();
    }

    @Test
    public void agrupamentoDemoPageRendersSuccessfully() {
        tester.startPage(AgrupamentoDemoPage.class);
        tester.assertRenderedPage(AgrupamentoDemoPage.class);
        tester.assertNoErrorMessage();
    }

    @Test
    public void ajaxDemoPageRendersSuccessfully() {
        tester.startPage(AjaxDemoPage.class);
        tester.assertRenderedPage(AjaxDemoPage.class);
        tester.assertNoErrorMessage();
    }

    @Test
    public void headerContributionsDemoPageRendersSuccessfully() {
        tester.startPage(HeaderContributionsDemoPage.class);
        tester.assertRenderedPage(HeaderContributionsDemoPage.class);
        tester.assertNoErrorMessage();
    }

    @Test
    public void recursosCustomizadosDemoPageRendersSuccessfully() {
        tester.startPage(RecursosCustomizadosDemoPage.class);
        tester.assertRenderedPage(RecursosCustomizadosDemoPage.class);
        tester.assertNoErrorMessage();
    }

    @Test
    public void convertersDemoPageRendersSuccessfully() {
        tester.startPage(ConvertersDemoPage.class);
        tester.assertRenderedPage(ConvertersDemoPage.class);
        tester.assertNoErrorMessage();
    }

    @Test
    public void modelosDemoPageRendersSuccessfully() {
        tester.startPage(ModelosDemoPage.class);
        tester.assertRenderedPage(ModelosDemoPage.class);
        tester.assertNoErrorMessage();
    }
}
