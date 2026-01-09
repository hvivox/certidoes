package com.hvivox.certidoes.validator;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ====================================================================
 * VALIDADOR CUSTOMIZADO - DATA FORMATADA (dd/MM/yyyy)
 * ====================================================================
 * 
 * Este validador implementa IValidator<String> para validar datas no formato
 * brasileiro (dd/MM/yyyy).
 * 
 * CARACTERÍSTICAS:
 * - Valida formato exato: dd/MM/yyyy (10 caracteres)
 * - Valida se a data é válida (não aceita 32/13/2025, por exemplo)
 * - Valida se a data parseada corresponde exatamente ao input
 * - Reutilizável: pode ser usado em qualquer TextField de data
 * - Mensagens de erro personalizadas em português
 * 
 * COMO USAR:
 * TextField<String> campoData = new TextField<>("dataEmissao");
 * campoData.add(new DataFormatadaValidator());
 * 
 * OU com mensagem customizada:
 * campoData.add(new DataFormatadaValidator("Data inválida!"));
 * 
 * VANTAGENS:
 * - Código reutilizável: não precisa repetir lógica de validação
 * - Testável: pode ser testado isoladamente
 * - Manutenível: mudanças na validação em um único lugar
 * - Integrado ao Wicket: funciona com FeedbackPanel automaticamente
 * 
 * CONTEXTO DO PROJETO (CERTIDÕES):
 * Usado para validar o campo "dataEmissao" no formulário de certidões,
 * garantindo que todas as datas sejam inseridas no formato correto.
 */
public class DataFormatadaValidator implements IValidator<String> {
    private static final long serialVersionUID = 1L;

    private static final String FORMATO_DATA = "dd/MM/yyyy";
    private static final int TAMANHO_ESPERADO = 10;

    private String mensagemErro;

    /**
     * Construtor padrão com mensagem de erro genérica.
     */
    public DataFormatadaValidator() {
        this("Data inválida! Use o formato dd/MM/yyyy (ex: 25/12/2025)");
    }

    /**
     * Construtor com mensagem de erro customizada.
     * 
     * @param mensagemErro Mensagem de erro personalizada
     */
    public DataFormatadaValidator(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    /**
     * Valida o valor da data.
     * 
     * Este método é chamado automaticamente pelo Wicket durante o processo
     * de validação do formulário.
     * 
     * @param validatable Objeto que contém o valor a ser validado
     */
    @Override
    public void validate(IValidatable<String> validatable) {
        String dataStr = validatable.getValue();

        // Se o valor for nulo ou vazio, não valida aqui
        // (deixe o setRequired() tratar campos obrigatórios)
        if (dataStr == null || dataStr.trim().isEmpty()) {
            return;
        }

        // Validar tamanho
        if (dataStr.length() != TAMANHO_ESPERADO) {
            ValidationError error = new ValidationError();
            error.setMessage(mensagemErro);
            validatable.error(error);
            return;
        }

        // Validar formato e se a data é válida
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA);
            sdf.setLenient(false); // Não aceitar datas inválidas como 32/13/2025

            Date date = sdf.parse(dataStr);

            // Verificar se a data parseada corresponde exatamente ao input
            // Isso garante que "01/01/2025" não seja aceito como "1/1/2025"
            String dataFormatada = sdf.format(date);
            if (!dataFormatada.equals(dataStr)) {
                ValidationError error = new ValidationError();
                error.setMessage(mensagemErro);
                validatable.error(error);
                return;
            }

            // Se chegou aqui, a data é válida!

        } catch (ParseException e) {
            // Data inválida (formato incorreto ou data inexistente)
            ValidationError error = new ValidationError();
            error.setMessage(mensagemErro);
            validatable.error(error);
        }
    }
}
