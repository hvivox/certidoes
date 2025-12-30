package com.hvivox.certidoes.model;

import com.hvivox.certidoes.domain.Certidao;
import com.hvivox.certidoes.infra.CertidaoRepository;
import com.hvivox.certidoes.infra.InMemoryCertidaoRepository;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * ====================================================================
 * LOADABLEDETACHABLEMODEL PARA CERTIDÃO - MÓDULO 3: MODELOS
 * ====================================================================
 * 
 * Esta classe demonstra o uso de LoadableDetachableModel no Wicket.
 * 
 * VANTAGENS:
 * - Evita problemas de serialização: apenas o ID é serializado, não o objeto completo
 * - Lazy Loading: o objeto só é carregado quando necessário
 * - Performance: libera memória após o uso (detach)
 * - Segurança: evita manter objetos grandes em memória entre requisições
 * 
 * CICLO DE VIDA:
 * 1. load() - Carrega o objeto do repositório/banco de dados
 * 2. attach() - Objeto fica disponível (chamado automaticamente pelo Wicket)
 * 3. detach() - Libera o objeto da memória (chamado automaticamente pelo Wicket)
 * 
 * QUANDO USAR:
 * - Objetos pesados (entidades JPA, objetos grandes)
 * - Objetos que vêm de banco de dados
 * - Quando você quer evitar problemas de serialização
 * - Quando o objeto pode mudar entre requisições
 * 
 * EXEMPLO DE USO:
 * CertidaoLoadableDetachableModel model = new CertidaoLoadableDetachableModel(1L);
 * add(new Label("numero", new PropertyModel<>(model, "numero")));
 * 
 * @see org.apache.wicket.model.LoadableDetachableModel
 */
public class CertidaoLoadableDetachableModel extends LoadableDetachableModel<Certidao> {
    private static final long serialVersionUID = 1L;

    // Apenas o ID é serializado, não o objeto Certidao completo
    private final Long certidaoId;

    // Repositório (transient porque não é serializável)
    private transient CertidaoRepository repository;

    /**
     * Construtor do Model.
     * 
     * @param certidaoId ID da certidão a ser carregada
     */
    public CertidaoLoadableDetachableModel(Long certidaoId) {
        this.certidaoId = certidaoId;
    }

    /**
     * Construtor alternativo que aceita uma Certidao existente.
     * Útil quando você já tem o objeto mas quer usar LoadableDetachableModel.
     * 
     * @param certidao Certidão existente (o ID será extraído)
     */
    public CertidaoLoadableDetachableModel(Certidao certidao) {
        if (certidao == null || certidao.getId() == null) {
            throw new IllegalArgumentException("Certidão deve ter um ID válido");
        }
        this.certidaoId = certidao.getId();
    }

    /**
     * Carrega o objeto Certidao do repositório.
     * 
     * Este método é chamado automaticamente pelo Wicket quando o objeto é necessário.
     * O objeto carregado é mantido em memória até que detach() seja chamado.
     * 
     * @return Certidao carregada do repositório
     */
    @Override
    protected Certidao load() {
        // Obter repositório (lazy initialization)
        CertidaoRepository repo = getRepository();
        
        // Carregar certidão do repositório
        return repo.findById(certidaoId)
                .orElseThrow(() -> new RuntimeException("Certidão não encontrada com ID: " + certidaoId));
    }

    /**
     * Obtém a instância do repositório (lazy initialization).
     * 
     * Como o repositório não é serializável, ele é marcado como transient
     * e recriado quando necessário.
     * 
     * @return Instância do repositório
     */
    private CertidaoRepository getRepository() {
        if (repository == null) {
            repository = new InMemoryCertidaoRepository();
        }
        return repository;
    }

    /**
     * Retorna o ID da certidão.
     * Útil para debug ou quando você precisa do ID sem carregar o objeto.
     * 
     * @return ID da certidão
     */
    public Long getCertidaoId() {
        return certidaoId;
    }
}

