package com.hvivox.certidoes.page;

import com.hvivox.certidoes.BasePage;
import com.hvivox.certidoes.converter.*;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.convert.IConverter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * MÓDULO 7 - ITEM 54: CONVERTERS (CONVERSORES CUSTOMIZADOS)
 * 
 * Esta página demonstra o uso de conversores customizados no Wicket.
 * 
 * CONVERTERS:
 * - CPFConverter: Formata CPF (###.###.###-##)
 * - CNPJConverter: Formata CNPJ (##.###.###/####-##)
 * - CEPConverter: Formata CEP (#####-###)
 * - MoedaBrasileiraConverter: Formata valores monetários (R$ 1.234,56)
 * 
 * COMO FUNCIONAM:
 * - convertToObject(): String → Objeto (remove formatação para processar)
 * - convertToString(): Objeto → String (aplica formatação para exibir)
 * 
 * USO:
 * 1. Criar o conversor (implements IConverter<T>)
 * 2. Associar ao FormComponent via @Override getConverter()
 * 3. Wicket aplica automaticamente na renderização e submissão
 */
public class ConvertersDemoPage extends BasePage {

    private static final long serialVersionUID = 1L;

    // Modelo para armazenar os dados do formulário
    private FormData formData = new FormData();

    public ConvertersDemoPage(final PageParameters parameters) {
        super();

        add(new Label("pageTitle", "Converters Customizados"));

        // Formulário com conversores
        Form<Void> form = new Form<Void>("form") {
            @Override
            protected void onSubmit() {
                info("✅ Formulário enviado com sucesso!");
                info("📋 Valores armazenados (sem formatação):");
                info("• CPF: " + formData.getCpf());
                info("• CNPJ: " + formData.getCnpj());
                info("• CEP: " + formData.getCep());
                info("• Valor: " + formData.getValor());
            }
        };
        add(form);

        // Campo CPF com conversor customizado
        TextField<String> cpfField = new TextField<String>("cpf",
                new PropertyModel<String>(formData, "cpf")) {
            @SuppressWarnings("unchecked")
            @Override
            public <C> IConverter<C> getConverter(Class<C> type) {
                if (type == String.class) {
                    return (IConverter<C>) new CPFConverter();
                }
                return super.getConverter(type);
            }
        };
        cpfField.setRequired(false);
        form.add(cpfField);

        // Campo CNPJ com conversor customizado
        TextField<String> cnpjField = new TextField<String>("cnpj",
                new PropertyModel<String>(formData, "cnpj")) {
            @SuppressWarnings("unchecked")
            @Override
            public <C> IConverter<C> getConverter(Class<C> type) {
                if (type == String.class) {
                    return (IConverter<C>) new CNPJConverter();
                }
                return super.getConverter(type);
            }
        };
        cnpjField.setRequired(false);
        form.add(cnpjField);

        // Campo CEP com conversor customizado
        TextField<String> cepField = new TextField<String>("cep",
                new PropertyModel<String>(formData, "cep")) {
            @SuppressWarnings("unchecked")
            @Override
            public <C> IConverter<C> getConverter(Class<C> type) {
                if (type == String.class) {
                    return (IConverter<C>) new CEPConverter();
                }
                return super.getConverter(type);
            }
        };
        cepField.setRequired(false);
        form.add(cepField);

        // Campo Valor com conversor de moeda
        TextField<BigDecimal> valorField = new TextField<BigDecimal>("valor",
                new PropertyModel<BigDecimal>(formData, "valor"), BigDecimal.class) {
            @SuppressWarnings("unchecked")
            @Override
            public <C> IConverter<C> getConverter(Class<C> type) {
                if (type == BigDecimal.class) {
                    return (IConverter<C>) new MoedaBrasileiraConverter();
                }
                return super.getConverter(type);
            }
        };
        valorField.setRequired(false);
        form.add(valorField);

        // Labels para exibir valores atuais
        form.add(new Label("cpfAtual", new PropertyModel<String>(formData, "cpf")));
        form.add(new Label("cnpjAtual", new PropertyModel<String>(formData, "cnpj")));
        form.add(new Label("cepAtual", new PropertyModel<String>(formData, "cep")));
        form.add(new Label("valorAtual", new PropertyModel<BigDecimal>(formData, "valor")));

        // Documentação
        add(new Label("docInfo", getDocumentacaoInfo()).setEscapeModelStrings(false));
    }

    private String getDocumentacaoInfo() {
        return "<div class='card'>" +
                "<div class='card-header bg-info text-white'>" +
                "<h5 class='mb-0'><i class='fas fa-info-circle'></i> Como Funcionam os Converters</h5>" +
                "</div>" +
                "<div class='card-body'>" +
                "<h6>O que são Converters?</h6>" +
                "<p>Converters no Wicket são responsáveis por converter valores entre String (exibição) " +
                "e objetos tipados (processamento/armazenamento).</p>" +
                "<h6>Métodos Principais:</h6>" +
                "<ul>" +
                "<li><code>convertToObject(String, Locale)</code> - Converte String → Objeto (remove formatação)</li>" +
                "<li><code>convertToString(Object, Locale)</code> - Converte Objeto → String (aplica formatação)</li>" +
                "</ul>" +
                "<h6>Quando Usar?</h6>" +
                "<ul>" +
                "<li>Formatação de documentos (CPF, CNPJ, CEP)</li>" +
                "<li>Formatação de moeda e números</li>" +
                "<li>Datas em formatos específicos</li>" +
                "<li>Qualquer tipo customizado que precise de formatação</li>" +
                "</ul>" +
                "<h6>Vantagens:</h6>" +
                "<ul>" +
                "<li>✅ Separação entre exibição e lógica</li>" +
                "<li>✅ Validação automática de formato</li>" +
                "<li>✅ Reutilizável em todo o projeto</li>" +
                "<li>✅ Código mais limpo e mantível</li>" +
                "</ul>" +
                "</div>" +
                "</div>";
    }

    /**
     * Classe interna para armazenar dados do formulário
     */
    public static class FormData implements Serializable {
        private static final long serialVersionUID = 1L;

        private String cpf = "12345678901"; // Valor inicial para demonstração
        private String cnpj = "12345678000195";
        private String cep = "12345678";
        private BigDecimal valor = new BigDecimal("1234.56");

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public String getCnpj() {
            return cnpj;
        }

        public void setCnpj(String cnpj) {
            this.cnpj = cnpj;
        }

        public String getCep() {
            return cep;
        }

        public void setCep(String cep) {
            this.cep = cep;
        }

        public BigDecimal getValor() {
            return valor;
        }

        public void setValor(BigDecimal valor) {
            this.valor = valor;
        }
    }
}
