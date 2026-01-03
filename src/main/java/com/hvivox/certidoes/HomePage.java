package com.hvivox.certidoes;

import com.hvivox.certidoes.session.CertidoesSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Página inicial do sistema de certidões.
 * 
 * MÓDULO 2 - ITEM 1: Demonstra o uso da Session customizada
 * 
 * Esta página exibe um dashboard simples mostrando as estatísticas
 * da sessão atual (contadores de operações realizadas).
 */
public class HomePage extends BasePage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super();

		add(new Label("titulo", "Home - Certidões ✅"));

		// MÓDULO 2 - ITEM 1: Obter dados da Session customizada
		CertidoesSession session = CertidoesSession.get();

		// Dashboard: Exibir contador de certidões excluídas
		add(new Label("certidoesExcluidas",
				String.valueOf(session.getCertidoesExcluidasNestaSessao())));
	}
}
