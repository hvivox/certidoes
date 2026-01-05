package com.hvivox.certidoes;

import com.hvivox.certidoes.listener.CertidoesRequestCycleListener;
import com.hvivox.certidoes.page.*;
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
		
		// MÓDULO 7 - ITEM 56: Montar URLs bookmarkable
		montarURLs();
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
	 * MÓDULO 7 - ITEM 56: MOUNTED PAGES (URLs BOOKMARKABLE)
	 * 
	 * Monta páginas com URLs limpas e amigáveis.
	 * 
	 * BENEFÍCIOS:
	 * - URLs mais legíveis: /certidoes/lista em vez de /wicket/page?0
	 * - SEO-friendly
	 * - Fácil de compartilhar
	 * - Bookmarkable (pode ser marcada nos favoritos)
	 * 
	 * SINTAXE:
	 * - mountPage("/caminho", MinhaPage.class)
	 * - mountPage("/certidoes/${id}", CertidaoDetailPage.class) - com parâmetro
	 * 
	 * EXEMPLOS DE ACESSO:
	 * - /certidoes/lista
	 * - /certidoes/nova
	 * - /certidoes/detalhe?id=123
	 * - /demos/urls
	 */
	private void montarURLs() {
		// Páginas principais de certidões
		mountPage("/certidoes/lista", CertidaoListPage.class);
		mountPage("/certidoes/nova", CertidaoFormPage.class);
		mountPage("/certidoes/detalhe", CertidaoDetailPage.class);
		
		// Páginas de demonstração
		mountPage("/demos/componentes", ComponentesDemoPage.class);
		mountPage("/demos/modelos", ModelosDemoPage.class);
		mountPage("/demos/agrupamento", AgrupamentoDemoPage.class);
		mountPage("/demos/ajax", AjaxDemoPage.class);
		mountPage("/demos/header-contributions", HeaderContributionsDemoPage.class);
		mountPage("/demos/recursos-customizados", RecursosCustomizadosDemoPage.class);
		mountPage("/demos/converters", ConvertersDemoPage.class);
		mountPage("/demos/urls", URLsDemoPage.class);
	}
}
