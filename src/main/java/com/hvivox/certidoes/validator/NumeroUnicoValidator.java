package com.hvivox.certidoes.validator;

import com.hvivox.certidoes.infra.CertidaoRepository;
import com.hvivox.certidoes.infra.InMemoryCertidaoRepository;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

/**
 * ====================================================================
 * VALIDADOR CUSTOMIZADO - NÚMERO ÚNICO
 * ====================================================================
 * 
 * MÓDULO 4 - ITEM 5: Processamento Avançado (Validação de Negócio)
 * 
 * Validador que verifica se o número da certidão já existe no repositório.
 * 
 * CARACTERÍSTICAS:
 * - Valida se o número já existe (modo novo)
 * - Valida se o número existe em outra certidão (modo edição)
 * - Reutilizável: pode ser usado em qualquer TextField de número
 * - Mensagens de erro personalizadas em português
 * 
 * COMO USAR:
 * TextField<String> numeroField = new TextField<>("numero");
 * numeroField.add(new NumeroUnicoValidator(false, null)); // Modo novo
 * 
 * OU para edição:
 * numeroField.add(new NumeroUnicoValidator(true, certidao.getId()));
 * 
 * VANTAGENS:
 * - Validação executada automaticamente pelo Wicket
 * - Integrado ao ciclo de validação
 * - Mensagens de erro exibidas automaticamente
 * - Funciona com FeedbackPanel
 */
public class NumeroUnicoValidator implements IValidator<String> {
    private static final long serialVersionUID = 1L;

    private boolean isEditMode;
    private Long idAtual;
    private transient CertidaoRepository repository;

    /**
     * Construtor para modo novo (criação).
     */
    public NumeroUnicoValidator() {
        this(false, null);
    }

    /**
     * Construtor com parâmetros.
     * 
     * @param isEditMode true se estiver editando uma certidão existente
     * @param idAtual    ID da certidão atual (null se modo novo)
     */
    public NumeroUnicoValidator(boolean isEditMode, Long idAtual) {
        this.isEditMode = isEditMode;
        this.idAtual = idAtual;
    }

    /**
     * Obtém a instância do repositório.
     */
    private CertidaoRepository getRepository() {
        if (repository == null) {
            repository = new InMemoryCertidaoRepository();
        }
        return repository;
    }

    /**
     * Valida se o número é único.
     * 
     * Este método é chamado automaticamente pelo Wicket durante o processo
     * de validação do formulário.
     * 
     * @param validatable Objeto que contém o valor a ser validado
     */
    @Override
    public void validate(IValidatable<String> validatable) {
        String numero = validatable.getValue();

        // Se o valor for nulo ou vazio, não valida aqui
        // (deixe o setRequired() tratar campos obrigatórios)
        if (numero == null || numero.trim().isEmpty()) {
            return;
        }

        String numeroNormalizado = numero.trim();
        CertidaoRepository repo = getRepository();

        // Verificar duplicação
        boolean numeroExiste;

        if (isEditMode && idAtual != null) {
            // Modo edição: verificar se existe em outra certidão
            numeroExiste = repo.findAll().stream()
                    .filter(c -> c.getNumero() != null && c.getId() != null)
                    .anyMatch(c -> c.getNumero().trim().equals(numeroNormalizado)
                            && !c.getId().equals(idAtual));

            if (numeroExiste) {
                ValidationError error = new ValidationError();
                error.setMessage("Já existe outra certidão com o número '" + numeroNormalizado
                        + "'. Por favor, use outro número.");
                validatable.error(error);
            }
        } else {
            // Modo novo: verificar se já existe
            numeroExiste = repo.findAll().stream()
                    .filter(c -> c.getNumero() != null)
                    .anyMatch(c -> c.getNumero().trim().equals(numeroNormalizado));

            if (numeroExiste) {
                ValidationError error = new ValidationError();
                error.setMessage("Já existe uma certidão com o número '" + numeroNormalizado
                        + "'. Por favor, use outro número.");
                validatable.error(error);
            }
        }
    }
}
