package com.hvivox.certidoes.session;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

/**
 * ====================================================================
 * ITEM 1: SESSION CUSTOMIZADA - MÓDULO 2: ARQUITETURA DO WICKET
 * ====================================================================
 * 
 * Session customizada para armazenar estatísticas da sessão do usuário.
 * 
 * CONCEITOS DEMONSTRADOS:
 * - Session: armazena dados específicos do usuário durante a sessão
 * - WebSession: classe base para sessões web no Wicket
 * - Persistência: dados na Session persistem entre requisições
 * 
 * CONTEXTO DO PROJETO (CERTIDÕES):
 * Esta Session armazena o contador de certidões excluídas nesta sessão.
 * 
 * COMO USAR:
 * // Obter a Session atual
 * CertidoesSession session = CertidoesSession.get();
 * 
 * // Incrementar contador
 * session.incrementarCertidoesExcluidas();
 * 
 * // Ler contador
 * int total = session.getCertidoesExcluidasNestaSessao();
 */
public class CertidoesSession extends WebSession {
    private static final long serialVersionUID = 1L;

    /**
     * Contador de certidões excluídas nesta sessão.
     */
    private int certidoesExcluidasNestaSessao = 0;

    /**
     * Construtor da Session.
     * 
     * @param request Requisição HTTP atual
     */
    public CertidoesSession(Request request) {
        super(request);
    }

    /**
     * Obtém a instância da Session atual.
     * 
     * EXEMPLO DE USO:
     * CertidoesSession session = CertidoesSession.get();
     * session.incrementarCertidoesExcluidas();
     * 
     * @return Instância da Session atual
     */
    public static CertidoesSession get() {
        return (CertidoesSession) WebSession.get();
    }

    /**
     * Incrementa o contador de certidões excluídas.
     */
    public void incrementarCertidoesExcluidas() {
        this.certidoesExcluidasNestaSessao++;
    }

    /**
     * Obtém o número de certidões excluídas nesta sessão.
     * 
     * @return Número de certidões excluídas
     */
    public int getCertidoesExcluidasNestaSessao() {
        return certidoesExcluidasNestaSessao;
    }
}
