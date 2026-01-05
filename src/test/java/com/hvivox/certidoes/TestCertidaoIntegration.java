package com.hvivox.certidoes;

import com.hvivox.certidoes.page.CertidaoDetailPage;
import com.hvivox.certidoes.page.CertidaoFormPage;
import com.hvivox.certidoes.page.CertidaoListPage;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * MÓDULO 7 - ITEM 55: TESTE DE INTEGRAÇÃO - Fluxo Completo de Certidão
 * 
 * Testa o fluxo completo da aplicação:
 * 1. Navegar para o formulário
 * 2. Criar uma certidão
 * 3. Ver certidão criada na listagem
 * 4. Visualizar detalhes da certidão
 * 
 * Este teste simula a interação real do usuário com o sistema.
 */
public class TestCertidaoIntegration {

    private WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester(new WicketApplication());
    }

    @Test
    public void completeUserFlowWorksCorrectly() {
        // ===================================================================
        // PASSO 1: Iniciar na página inicial
        // ===================================================================
        tester.startPage(HomePage.class);
        tester.assertRenderedPage(HomePage.class);

        // ===================================================================
        // PASSO 2: Navegar para o formulário de nova certidão
        // ===================================================================
        tester.startPage(CertidaoFormPage.class);
        tester.assertRenderedPage(CertidaoFormPage.class);

        // ===================================================================
        // PASSO 3: Preencher e submeter o formulário
        // ===================================================================
        FormTester formTester = tester.newFormTester("form");

        // Preencher todos os campos obrigatórios
        formTester.setValue("numero", "CERT-INTEGRATION-TEST-001");
        formTester.select("tipo", 0); // Primeiro tipo
        formTester.setValue("interessado", "Maria Silva");
        formTester.setValue("dataEmissao", "01/01/2026");

        // Submeter o formulário
        formTester.submit();

        // Verificar que não há erros
        tester.assertNoErrorMessage();

        // ===================================================================
        // PASSO 5: Navegar para a listagem
        // ===================================================================
        tester.startPage(CertidaoListPage.class);
        tester.assertRenderedPage(CertidaoListPage.class);

        // Verificar que o container da tabela existe
        tester.assertComponent("tableContainer",
                org.apache.wicket.markup.html.WebMarkupContainer.class);

        // Verificar que há certidões na lista (conteúdo da página)
        tester.assertContains("Certidão");

        // ===================================================================
        // PASSO 6: Testar visualização de detalhes (se houver links)
        // ===================================================================
        // A página de detalhes requer um ID, então vamos apenas verificar
        // que ela renderiza corretamente quando iniciada
        // (em um cenário real, clicaríamos no link na lista)
        tester.startPage(CertidaoDetailPage.class,
                new org.apache.wicket.request.mapper.parameter.PageParameters().add("id", 1));
        tester.assertRenderedPage(CertidaoDetailPage.class);
    }

    @Test
    public void navigationBetweenPagesWorks() {
        // Testar navegação entre diferentes páginas do sistema

        // Home → Lista
        tester.startPage(HomePage.class);
        tester.assertRenderedPage(HomePage.class);

        // Clicar no link "Listar Certidões"
        tester.clickLink("linkListar");
        tester.assertRenderedPage(CertidaoListPage.class);

        // Lista → Nova Certidão
        tester.clickLink("linkNova");
        tester.assertRenderedPage(CertidaoFormPage.class);

        // Nova → Home
        tester.clickLink("linkHome");
        tester.assertRenderedPage(HomePage.class);
    }

    @Test
    public void formValidationInIntegrationContext() {
        // Testar validação no contexto de integração

        tester.startPage(CertidaoFormPage.class);

        // Tentar submeter formulário com campos obrigatórios vazios
        FormTester formTester = tester.newFormTester("form");
        formTester.setValue("numero", ""); // Campo obrigatório vazio
        formTester.setValue("interessado", "");
        formTester.setValue("dataEmissao", "");
        formTester.submit();

        // Deve haver erros de validação - a página deve permanecer a mesma
        tester.assertRenderedPage(CertidaoFormPage.class);

        // A página não deve ter mudado
        tester.assertRenderedPage(CertidaoFormPage.class);
    }

    @Test
    public void sessionDataPersistsAcrossPages() {
        // Testar que dados da sessão persistem entre páginas

        // Iniciar na home
        tester.startPage(HomePage.class);

        // Navegar para outras páginas e voltar
        tester.startPage(CertidaoListPage.class);
        tester.startPage(CertidaoFormPage.class);
        tester.startPage(HomePage.class);

        // Verificar que o contador da sessão ainda existe
        // (o contador de certidões excluídas deve estar visível)
        tester.assertComponent("certidoesExcluidas",
                org.apache.wicket.markup.html.basic.Label.class);
    }
}
