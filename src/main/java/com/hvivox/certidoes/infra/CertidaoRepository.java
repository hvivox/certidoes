package com.hvivox.certidoes.infra;

import com.hvivox.certidoes.domain.Certidao;

import java.util.List;
import java.util.Optional;

public interface CertidaoRepository {
    List<Certidao> findAll();
    Optional<Certidao> findById(Long id);
    Certidao save(Certidao certidao); // cria/atualiza
    void delete(Long id);
}
