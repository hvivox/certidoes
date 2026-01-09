package com.hvivox.certidoes.infra;

import com.hvivox.certidoes.domain.Certidao;
import com.hvivox.certidoes.model.CertidaoFiltro;

import java.util.List;
import java.util.Optional;

public interface CertidaoRepository {
    List<Certidao> findAll();
    Optional<Certidao> findById(Long id);
    Certidao save(Certidao certidao); // cria/atualiza
    void delete(Long id);
    
    /**
     * MÓDULO 7 - ITEM 58: Busca e filtros de certidões
     * 
     * Busca certidões aplicando os filtros especificados.
     * Se nenhum filtro for aplicado, retorna todas as certidões.
     * 
     * @param filtro Critérios de busca e filtro
     * @return Lista de certidões que atendem aos critérios
     */
    List<Certidao> findByFiltro(CertidaoFiltro filtro);
}
