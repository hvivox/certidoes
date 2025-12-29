package com.hvivox.certidoes;

import com.hvivox.certidoes.listener.CertidoesRequestCycleListener;
import com.hvivox.certidoes.session.CertidoesSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see com.hvivox.certidoes.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
	/**
	 * Define qual página será exibida quando o usuário acessar a raiz da aplicação.
	 * Esta é a página inicial (home page) da aplicação.
	 * 
	 * @return A classe da página inicial (HomePage)
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	/**
	 * Cria uma nova instância da Session customizada.
	 * 
	 * MÓDULO 2 - ITEM 1: SESSION CUSTOMIZADA
	 * 
	 * Este método é chamado automaticamente pelo Wicket quando uma nova sessão
	 * precisa ser criada (primeira requisição do usuário).
	 * 
	 * @param request  Requisição HTTP atual
	 * @param response Resposta HTTP atual
	 * @return Nova instância da Session customizada (CertidoesSession)
	 */
	@Override
	public WebSession newSession(Request request, Response response) {
		return new CertidoesSession(request);
	}

	/**
	 * Método de inicialização da aplicação Wicket.
	 * Aqui são feitas todas as configurações globais da aplicação.
	 * 
	 * CONFIGURAÇÕES REALIZADAS:
	 * - Encoding UTF-8: garante suporte a caracteres especiais (acentos, etc.)
	 * - Markup encoding: configuração de encoding para arquivos HTML
	 * 
	 * OUTRAS CONFIGURAÇÕES POSSÍVEIS:
	 * - getDebugSettings(): configurações de debug
	 * - getResourceSettings(): configurações de recursos (CSS, JS, imagens)
	 * - getSecuritySettings(): configurações de segurança
	 * - getApplicationSettings(): configurações gerais
	 * - getPageSettings(): configurações de páginas
	 * - getRequestCycleSettings(): configurações do ciclo de requisição
	 * 
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();

		// Configurar encoding UTF-8 para todas as requisições
		// Isso garante que caracteres especiais (acentos, ç, etc.) sejam tratados
		// corretamente
		getRequestCycleSettings().setResponseRequestEncoding("UTF-8");

		// Garantir que o Content-Type seja configurado corretamente
		// Define o encoding padrão para arquivos de markup (HTML)
		getMarkupSettings().setDefaultMarkupEncoding("UTF-8");

		// MÓDULO 2 - ITEM 2: Adicionar RequestCycleListener
		// Intercepta o ciclo de requisição para logging e monitoramento
		getRequestCycleListeners().add(new CertidoesRequestCycleListener());

		// EXEMPLO: Configurar modo de desenvolvimento (útil para debug)
		// getDebugSettings().setDevelopmentUtilitiesEnabled(true);

		// EXEMPLO: Configurar recursos estáticos
		// getResourceSettings().setResourcePollFrequency(Duration.seconds(1));
	}
}
