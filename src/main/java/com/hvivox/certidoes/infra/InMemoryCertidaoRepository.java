package com.hvivox.certidoes.infra;

import com.hvivox.certidoes.domain.Certidao;
import com.hvivox.certidoes.domain.CertidaoStatus;
import com.hvivox.certidoes.domain.CertidaoTipo;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryCertidaoRepository implements CertidaoRepository {

    private static final AtomicLong SEQ = new AtomicLong(0);
    private static final Map<Long, Certidao> DB = new LinkedHashMap<>();

    static {
        // dados iniciais (pra você já ver tabela)
        seed("0001", CertidaoTipo.NEGATIVA, "Empresa ABC", "24/12/2025", CertidaoStatus.EMITIDA);
        seed("0002", CertidaoTipo.POSITIVA, "Empresa XYZ", "23/12/2025", CertidaoStatus.RASCUNHO);
    }

    private static void seed(String numero, CertidaoTipo tipo, String interessado, String data, CertidaoStatus status) {
        Certidao c = new Certidao();
        c.setId(SEQ.incrementAndGet());
        c.setNumero(numero);
        c.setTipo(tipo);
        c.setInteressado(interessado);
        c.setDataEmissao(data);
        c.setStatus(status);
        DB.put(c.getId(), c);
    }

    @Override
    public List<Certidao> findAll() {
        return new ArrayList<>(DB.values());
    }

    @Override
    public Optional<Certidao> findById(Long id) {
        return Optional.ofNullable(DB.get(id));
    }

    @Override
    public Certidao save(Certidao certidao) {
        if (certidao.getId() == null) {
            certidao.setId(SEQ.incrementAndGet());
        }
        DB.put(certidao.getId(), certidao);
        return certidao;
    }

    @Override
    public void delete(Long id) {
        DB.remove(id);
    }
}
