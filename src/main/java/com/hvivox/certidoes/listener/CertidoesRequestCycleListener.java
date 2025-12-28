package com.hvivox.certidoes.listener;

import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ====================================================================
 * ITEM 2: REQUEST CYCLE LISTENER - MÓDULO 2: ARQUITETURA DO WICKET
 * ====================================================================
 * 
 * Este listener demonstra como interceptar o ciclo de requisição do Wicket.
 * 
 * CONCEITOS DEMONSTRADOS:
 * - RequestCycle: ciclo de vida de uma requisição HTTP
 * - IRequestCycleListener: interface para interceptar eventos do ciclo
 * - Etapas do processamento: onBeginRequest, onEndRequest, etc.
 * 
 * ETAPAS DO PROCESSAMENTO DE UMA REQUISIÇÃO:
 * 1. onBeginRequest() - Início da requisição
 * 2. onRequestHandlerResolved() - Handler identificado
 * 3. onRequestHandlerScheduled() - Handler agendado
 * 4. onRequestHandlerExecuted() - Handler executado
 * 5. onEndRequest() - Fim da requisição
 * 
 * CONTEXTO DO PROJETO (CERTIDÕES):
 * Este listener faz logging das requisições para monitoramento:
 * - Log de início e fim de requisições
 * - Log de handlers executados
 * - Log de erros (se houver)
 * 
 * COMO USAR:
 * O listener é registrado automaticamente no WicketApplication.init()
 * e é executado para TODAS as requisições da aplicação.
 * 
 * IMPORTANTE:
 * - O listener é executado para TODAS as requisições
 * - Deve ser thread-safe (pode ser chamado de múltiplas threads)
 * - Não deve fazer operações pesadas (pode impactar performance)
 */
public class CertidoesRequestCycleListener extends AbstractRequestCycleListener {

    private static final Logger logger = LoggerFactory.getLogger(CertidoesRequestCycleListener.class);

    /**
     * Flag para habilitar/desabilitar o monitoramento do Request Cycle.
     * 
     * COMO USAR:
     * - true: logs são exibidos (útil para debug e desenvolvimento)
     * - false: logs são desabilitados (útil para produção)
     * 
     * Para habilitar, altere para: private static final boolean
     * HABILITAR_MONITORAMENTO = true;
     * Para desabilitar, altere para: private static final boolean
     * HABILITAR_MONITORAMENTO = false;
     */
    private static final boolean HABILITAR_MONITORAMENTO = false;

    /**
     * Chamado no início de cada requisição.
     * 
     * EXEMPLO DE USO:
     * - Logging de requisições
     * - Inicialização de variáveis de contexto
     * - Medição de tempo de resposta
     * 
     * @param cycle RequestCycle atual
     */
    @Override
    public void onBeginRequest(RequestCycle cycle) {
        if (!HABILITAR_MONITORAMENTO) {
            return; // Monitoramento desabilitado
        }

        // Log da URL da requisição
        String url = cycle.getRequest().getUrl().toString();
        logger.info("▶ Início da requisição: {}", url);
        // System.out para garantir que apareça no console
        System.out.println("[RequestCycle] ▶ Início da requisição: " + url);
    }

    /**
     * Chamado quando o handler da requisição é identificado.
     * 
     * O handler é o componente responsável por processar a requisição.
     * Pode ser uma página, um recurso, um componente Ajax, etc.
     * 
     * @param cycle   RequestCycle atual
     * @param handler Handler identificado
     */
    @Override
    public void onRequestHandlerResolved(RequestCycle cycle, IRequestHandler handler) {
        if (!HABILITAR_MONITORAMENTO) {
            return; // Monitoramento desabilitado
        }

        // Log do tipo de handler identificado
        String handlerType = handler.getClass().getSimpleName();
        logger.info("  → Handler identificado: {}", handlerType);
        System.out.println("[RequestCycle]   → Handler identificado: " + handlerType);
    }

    /**
     * Chamado quando o handler é agendado para execução.
     * 
     * @param cycle   RequestCycle atual
     * @param handler Handler agendado
     */
    @Override
    public void onRequestHandlerScheduled(RequestCycle cycle, IRequestHandler handler) {
        if (!HABILITAR_MONITORAMENTO) {
            return; // Monitoramento desabilitado
        }

        // Log quando o handler é agendado
        String handlerType = handler.getClass().getSimpleName();
        logger.info("  → Handler agendado: {}", handlerType);
        System.out.println("[RequestCycle]   → Handler agendado: " + handlerType);
    }

    /**
     * Chamado após a execução do handler.
     * 
     * @param cycle   RequestCycle atual
     * @param handler Handler executado
     */
    @Override
    public void onRequestHandlerExecuted(RequestCycle cycle, IRequestHandler handler) {
        if (!HABILITAR_MONITORAMENTO) {
            return; // Monitoramento desabilitado
        }

        // Log quando o handler é executado com sucesso
        String handlerType = handler.getClass().getSimpleName();
        logger.info("  → Handler executado: {}", handlerType);
        System.out.println("[RequestCycle]   → Handler executado: " + handlerType);
    }

    /**
     * Chamado no fim de cada requisição.
     * 
     * EXEMPLO DE USO:
     * - Finalização de recursos
     * - Log de tempo total de processamento
     * - Limpeza de contexto
     * 
     * @param cycle RequestCycle atual
     */
    @Override
    public void onEndRequest(RequestCycle cycle) {
        if (!HABILITAR_MONITORAMENTO) {
            return; // Monitoramento desabilitado
        }

        // Log de fim da requisição
        logger.info("◀ Fim da requisição");
        System.out.println("[RequestCycle] ◀ Fim da requisição");
    }

    /**
     * Chamado quando ocorre uma exceção durante o processamento.
     * 
     * EXEMPLO DE USO:
     * - Log de erros
     * - Envio de notificações
     * - Tratamento de erros customizado
     * 
     * @param cycle RequestCycle atual
     * @param ex    Exceção ocorrida
     * @return IRequestHandler para tratar o erro, ou null para tratamento padrão
     */
    @Override
    public IRequestHandler onException(RequestCycle cycle, Exception ex) {
        // Erros sempre são logados, independente da flag (importante para debug)
        logger.error("❌ Erro durante processamento da requisição: {}", ex.getMessage(), ex);

        if (HABILITAR_MONITORAMENTO) {
            System.out.println("[RequestCycle] ❌ Erro: " + ex.getMessage());
        }

        // Retornar null para usar o tratamento padrão do Wicket
        // Ou retornar um handler customizado para página de erro
        return null;
    }
}
