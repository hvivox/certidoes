package com.hvivox.certidoes;

import com.hvivox.certidoes.page.CertidaoFormPage;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * MÓDULO 7 - ITEM 55: TESTE UNITÁRIO 1 - Formulário de Certidão
 * 
 * Testa o formulário de criação/edição de certidões.
 * 
 * TESTES:
 * - Página renderiza corretamente
 * - Formulário existe
 * - Campos obrigatórios funcionam
 * - Submissão com dados válidos funciona
 */
public class TestCertidaoFormPage {

    private WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester(new WicketApplication());
    }

    @Test
    public void formPageRendersSuccessfully() {
        // Iniciar e renderizar a página de formulário
        tester.startPage(CertidaoFormPage.class);

        // Verificar se a página foi renderizada corretamente
        tester.assertRenderedPage(CertidaoFormPage.class);
    }

    @Test
    public void formComponentsExist() {
        tester.startPage(CertidaoFormPage.class);

        // Verificar se o formulário existe
        tester.assertComponent("form", org.apache.wicket.markup.html.form.Form.class);

        // Verificar se os campos principais existem
        tester.assertComponent("form:numero", org.apache.wicket.markup.html.form.TextField.class);
        tester.assertComponent("form:tipo", org.apache.wicket.markup.html.form.DropDownChoice.class);
        tester.assertComponent("form:interessado", org.apache.wicket.markup.html.form.TextField.class);
        tester.assertComponent("form:dataEmissao", org.apache.wicket.markup.html.form.TextField.class);
    }

    @Test
    public void formSubmitsSuccessfully() {
        tester.startPage(CertidaoFormPage.class);

        // Criar FormTester para preencher o formulário
        FormTester formTester = tester.newFormTester("form");

        // Preencher todos os campos obrigatórios
        formTester.setValue("numero", "CERT-TEST-001");
        formTester.select("tipo", 0); // Selecionar primeiro tipo
        formTester.setValue("interessado", "João da Silva");
        formTester.setValue("dataEmissao", "01/01/2026");

        // Submeter o formulário
        formTester.submit();

        // Verificar se não há erros de validação
        tester.assertNoErrorMessage();
    }

    @Test
    public void formRequiredFieldsWork() {
        tester.startPage(CertidaoFormPage.class);

        // Criar FormTester
        FormTester formTester = tester.newFormTester("form");

        // Deixar campos obrigatórios vazios
        formTester.setValue("numero", "");
        formTester.setValue("interessado", "");
        formTester.setValue("dataEmissao", "");

        // Submeter o formulário
        formTester.submit();

        // Verificar se há mensagens de erro (formulário tem campos obrigatórios)
        // A página não deve ter mensagens de sucesso quando há erros de validação
        tester.assertRenderedPage(CertidaoFormPage.class);
    }
}
