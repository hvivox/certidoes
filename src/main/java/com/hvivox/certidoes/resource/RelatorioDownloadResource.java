package com.hvivox.certidoes.resource;

import com.hvivox.certidoes.domain.Certidao;
import com.hvivox.certidoes.service.RelatorioService;
import org.apache.wicket.request.resource.AbstractResource;
import org.apache.wicket.util.time.Duration;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * MÓDULO 7 - ITEM 59: RESOURCE PARA DOWNLOAD DE RELATÓRIOS
 * 
 * Resource que gera e faz download de relatórios de certidões.
 * Utiliza os serviços de geração de relatórios (Excel ou Word).
 */
public class RelatorioDownloadResource extends AbstractResource {
    
    private static final long serialVersionUID = 1L;
    
    private final RelatorioService relatorioService;
    private final List<Certidao> certidoes;
    private final String nomeArquivo;
    
    public RelatorioDownloadResource(RelatorioService relatorioService, List<Certidao> certidoes, String nomeArquivo) {
        this.relatorioService = relatorioService;
        this.certidoes = certidoes;
        this.nomeArquivo = nomeArquivo;
    }
    
    @Override
    protected ResourceResponse newResourceResponse(Attributes attributes) {
        ResourceResponse response = new ResourceResponse();
        
        response.setContentType(relatorioService.getContentType());
        response.setFileName(nomeArquivo + relatorioService.getFileExtension());
        response.setCacheDuration(Duration.NONE); // Não cachear
        response.disableCaching();
        
        response.setWriteCallback(new WriteCallback() {
            @Override
            public void writeData(Attributes attributes) {
                try {
                    ByteArrayOutputStream outputStream = relatorioService.gerarRelatorio(certidoes);
                    attributes.getResponse().write(outputStream.toByteArray());
                } catch (Exception e) {
                    throw new RuntimeException("Erro ao gerar relatório", e);
                }
            }
        });
        
        return response;
    }
}

