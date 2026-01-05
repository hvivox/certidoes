package com.hvivox.certidoes.service;

import com.hvivox.certidoes.domain.Certidao;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * MÓDULO 7 - ITEM 59: RELATÓRIOS COM APACHE POI
 * 
 * Interface para serviços de geração de relatórios.
 */
public interface RelatorioService {
    
    /**
     * Gera relatório contendo as certidões fornecidas
     * 
     * @param certidoes Lista de certidões para incluir no relatório
     * @return ByteArrayOutputStream contendo o relatório gerado
     * @throws Exception Se ocorrer erro na geração
     */
    ByteArrayOutputStream gerarRelatorio(List<Certidao> certidoes) throws Exception;
    
    /**
     * Retorna o tipo MIME do relatório gerado
     * 
     * @return String com o tipo MIME (ex: "application/vnd.ms-excel")
     */
    String getContentType();
    
    /**
     * Retorna a extensão do arquivo do relatório
     * 
     * @return String com a extensão (ex: ".xlsx")
     */
    String getFileExtension();
}

