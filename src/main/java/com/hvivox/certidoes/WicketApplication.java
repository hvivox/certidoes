package com.hvivox.certidoes;

import com.hvivox.certidoes.listener.CertidoesRequestCycleListener;
import com.hvivox.certidoes.session.CertidoesSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.resource.caching.FilenameWithVersionResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.NoOpResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.version.MessageDigestResourceVersion;
import org.apache.wicket.util.time.Duration;

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

		// MÓDULO 7 - ITEM 53: Configurar carregamento de recursos
		configurarRecursos();

		// MÓDULO 7 - ITEM 57: Configurar modo de produção
		configurarModoProducao();
	}

	/**
	 * MÓDULO 7 - ITEM 53: CUSTOMIZAÇÃO DO CARREGAMENTO DE RECURSOS
	 * 
	 * Configura como recursos (CSS, JS, imagens) são carregados, cacheados e
	 * otimizados.
	 * 
	 * CONFIGURAÇÕES:
	 * - Cache: estratégia diferente para desenvolvimento vs produção
	 * - Versionamento: hash MD5 do conteúdo em produção
	 * - Minificação: automática em produção (.min.js/.min.css)
	 * - Poll frequency: recarregamento rápido em desenvolvimento
	 */
	private void configurarRecursos() {
		if (usesDeploymentConfig()) {
			// ==== PRODUÇÃO ====
			// Cache agressivo com versionamento por MD5
			// Exemplo: style.css vira style-abc123def456.css
			// Permite cache infinito no browser, pois nome muda quando conteúdo muda
			getResourceSettings().setCachingStrategy(
					new FilenameWithVersionResourceCachingStrategy(
							new MessageDigestResourceVersion() // MD5 do conteúdo
					));

			// Habilitar minificação automática
			// Wicket procurará por arquivos .min.js e .min.css automaticamente
			getResourceSettings().setUseMinifiedResources(true);
		} else {
			// ==== DESENVOLVIMENTO ====
			// Sem cache - recursos sempre recarregados
			getResourceSettings().setCachingStrategy(NoOpResourceCachingStrategy.INSTANCE);

			// Recarregar recursos modificados a cada 1 segundo
			// Útil para ver mudanças em CSS/JS sem reiniciar servidor
			getResourceSettings().setResourcePollFrequency(Duration.seconds(1));

			// Minificação desabilitada para facilitar debug
			getResourceSettings().setUseMinifiedResources(false);
		}
	}

	/**
	 * MÓDULO 7 - ITEM 57: CONFIGURAÇÃO DE MODO DE PRODUÇÃO
	 * 
	 * Aplica otimizações e configurações de segurança específicas para produção.
	 * 
	 * OTIMIZAÇÕES APLICADAS:
	 * 1. Compressão de markup HTML (remove espaços, quebras de linha
	 * desnecessárias)
	 * 2. Stripping de comentários Wicket do HTML final
	 * 3. Desabilitação de informações de debug detalhadas
	 * 4. Configurações de segurança aprimoradas
	 * 5. Cache de páginas e serialização otimizada
	 * 
	 * COMO FUNCIONA:
	 * - Este método verifica o modo atual usando usesDeploymentConfig()
	 * - Se true = PRODUÇÃO (DEPLOYMENT)
	 * - Se false = DESENVOLVIMENTO (DEVELOPMENT)
	 * 
	 * COMO ALTERAR O MODO:
	 * - Por padrão, Wicket roda em DEVELOPMENT quando executado via Start.java
	 * - Para produção, defina a propriedade do sistema:
	 * -Dwicket.configuration=deployment
	 * 
	 * - Ou configure no web.xml:
	 * <context-param>
	 * <param-name>configuration</param-name>
	 * <param-value>deployment</param-value>
	 * </context-param>
	 */
	private void configurarModoProducao() {
		if (usesDeploymentConfig()) {
			// ==================== MODO PRODUÇÃO ====================

			// 1. OTIMIZAÇÃO DE MARKUP HTML
			// Comprime o HTML removendo espaços, tabs e quebras de linha desnecessárias
			// Reduz o tamanho da resposta em até 20-30%
			getMarkupSettings().setCompressWhitespace(true);

			// Remove comentários Wicket do HTML final (<!-- wicket:id="..." -->)
			// Esses comentários são úteis para debug, mas desnecessários em produção
			getMarkupSettings().setStripWicketTags(true);

			// 2. CONFIGURAÇÕES DE DEBUG
			// Desabilita informações detalhadas de debug em páginas de erro
			// Em produção, erros devem mostrar página customizada, não stack traces
			getDebugSettings().setAjaxDebugModeEnabled(false);
			getDebugSettings().setDevelopmentUtilitiesEnabled(false);

			// 3. SERIALIZAÇÃO E PERFORMANCE
			// Em produção, páginas são serializadas para o disco para economizar memória
			// Configura checagem de serialização para garantir que tudo seja serializável
			// NOTA: Para customizar a página de erro "Page Expired", crie uma página e
			// configure:
			// getApplicationSettings().setPageExpiredErrorPage(MinhaPageExpiredPage.class);

			// 4. CONFIGURAÇÕES DE SEGURANÇA
			// Throw exception se houver tentativa de acesso a componente não autorizado
			// (em dev, apenas loga warning)
			getSecuritySettings().setEnforceMounts(true);

			// 5. CACHE DE PÁGINAS
			// Em produção, mantém mais páginas em cache para melhor performance
			// O padrão é suficiente, mas pode ser ajustado conforme necessidade
			// getPageSettings().setVersionPagesByDefault(true); // Já é true por padrão

		} else {
			// ==================== MODO DESENVOLVIMENTO ====================

			// 1. MARKUP LEGÍVEL
			// HTML não é comprimido para facilitar debug no browser
			getMarkupSettings().setCompressWhitespace(false);

			// Mantém comentários Wicket no HTML para debug
			getMarkupSettings().setStripWicketTags(false);

			// 2. DEBUG HABILITADO
			// Mostra informações detalhadas de debug em páginas de erro
			getDebugSettings().setAjaxDebugModeEnabled(true);
			getDebugSettings().setDevelopmentUtilitiesEnabled(true);

			// 3. HOT RELOAD
			// Recarrega automaticamente arquivos HTML modificados sem reiniciar servidor
			getResourceSettings().setResourcePollFrequency(Duration.seconds(1));

			// 4. MENSAGENS DETALHADAS
			// Em desenvolvimento, erros mostram stack traces completos
			// Facilita debug e identificação de problemas
		}
	}
}
