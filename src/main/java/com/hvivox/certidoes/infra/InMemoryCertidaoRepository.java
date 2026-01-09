package com.hvivox.certidoes.infra;

import com.hvivox.certidoes.domain.Certidao;
import com.hvivox.certidoes.domain.CertidaoStatus;
import com.hvivox.certidoes.domain.CertidaoTipo;
import com.hvivox.certidoes.model.CertidaoFiltro;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

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
    
    /**
     * MÓDULO 7 - ITEM 58: BUSCA E FILTROS
     * 
     * Implementa busca de certidões com múltiplos filtros.
     * Os filtros são aplicados cumulativamente (AND).
     */
    @Override
    public List<Certidao> findByFiltro(CertidaoFiltro filtro) {
        if (filtro == null || !filtro.temFiltroAplicado()) {
            return findAll();
        }
        
        return DB.values().stream()
            .filter(certidao -> filtrarPorTextoBusca(certidao, filtro.getTextoBusca()))
            .filter(certidao -> filtrarPorTipo(certidao, filtro.getTipo()))
            .filter(certidao -> filtrarPorStatus(certidao, filtro.getStatus()))
            .filter(certidao -> filtrarPorData(certidao, filtro.getDataInicio(), filtro.getDataFim()))
            .collect(Collectors.toList());
    }
    
    /**
     * Filtra por texto de busca (busca em número e interessado)
     */
    private boolean filtrarPorTextoBusca(Certidao certidao, String textoBusca) {
        if (textoBusca == null || textoBusca.trim().isEmpty()) {
            return true;
        }
        
        String busca = textoBusca.toLowerCase().trim();
        
        // Busca no número
        if (certidao.getNumero() != null && certidao.getNumero().toLowerCase().contains(busca)) {
            return true;
        }
        
        // Busca no interessado
        if (certidao.getInteressado() != null && certidao.getInteressado().toLowerCase().contains(busca)) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Filtra por tipo de certidão
     */
    private boolean filtrarPorTipo(Certidao certidao, CertidaoTipo tipo) {
        if (tipo == null) {
            return true;
        }
        return tipo.equals(certidao.getTipo());
    }
    
    /**
     * Filtra por status da certidão
     */
    private boolean filtrarPorStatus(Certidao certidao, CertidaoStatus status) {
        if (status == null) {
            return true;
        }
        return status.equals(certidao.getStatus());
    }
    
    /**
     * Filtra por data de emissão (entre dataInicio e dataFim)
     */
    private boolean filtrarPorData(Certidao certidao, Date dataInicio, Date dataFim) {
        if (dataInicio == null && dataFim == null) {
            return true;
        }
        
        // Parsear a data da certidão (formato dd/MM/yyyy)
        if (certidao.getDataEmissao() == null) {
            return false;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dataCertidao = sdf.parse(certidao.getDataEmissao());
            
            // Verificar se está dentro do período
            if (dataInicio != null && dataCertidao.before(dataInicio)) {
                return false;
            }
            
            if (dataFim != null && dataCertidao.after(dataFim)) {
                return false;
            }
            
            return true;
        } catch (ParseException e) {
            // Se não conseguir parsear, não incluir nos resultados
            return false;
        }
    }
}
