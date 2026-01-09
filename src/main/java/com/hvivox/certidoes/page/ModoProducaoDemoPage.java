package com.hvivox.certidoes.page;

import com.hvivox.certidoes.BasePage;
import org.apache.wicket.Application;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

/**
 * MÓDULO 7 - ITEM 57: DEMONSTRAÇÃO DE MODO DE PRODUÇÃO
 * 
 * Esta página demonstra as diferenças entre modo DEVELOPMENT e DEPLOYMENT,
 * mostrando qual modo está ativo e quais configurações estão aplicadas.
 */
public class ModoProducaoDemoPage extends BasePage {
    
    private static final long serialVersionUID = 1L;
    
    public ModoProducaoDemoPage() {
        super();
        
        // Obter a aplicação Wicket
        Application app = Application.get();
        
        // Verificar qual modo está ativo
        boolean isProducao = app.usesDeploymentConfig();
        
        // ==================== INFORMAÇÕES DO MODO ATUAL ====================
        
        // Label mostrando o modo atual
        Label labelModo = new Label("modoAtual", 
                Model.of(isProducao ? "DEPLOYMENT (Produção)" : "DEVELOPMENT (Desenvolvimento)"));
        labelModo.add(new org.apache.wicket.AttributeModifier("class", 
                isProducao ? "badge badge-success" : "badge badge-warning"));
        add(labelModo);
        
        // Descrição do modo
        String descricaoModo = isProducao 
                ? "Otimizado para produção com cache agressivo, minificação e segurança aprimorada"
                : "Otimizado para desenvolvimento com hot reload, debug habilitado e mensagens detalhadas";
        add(new Label("descricaoModo", descricaoModo));
        
        // ==================== CONFIGURAÇÕES APLICADAS ====================
        
        // Markup - Compressão de whitespace
        boolean comprimirWhitespace = app.getMarkupSettings().getCompressWhitespace();
        add(new Label("comprimirWhitespace", 
                comprimirWhitespace ? "✓ Habilitado" : "✗ Desabilitado"));
        add(new Label("comprimirWhitespaceDescricao", 
                comprimirWhitespace 
                    ? "HTML é comprimido removendo espaços desnecessários"
                    : "HTML mantém formatação original para debug"));
        
        // Markup - Strip Wicket tags
        boolean stripWicketTags = app.getMarkupSettings().getStripWicketTags();
        add(new Label("stripWicketTags", 
                stripWicketTags ? "✓ Habilitado" : "✗ Desabilitado"));
        add(new Label("stripWicketTagsDescricao", 
                stripWicketTags 
                    ? "Comentários Wicket são removidos do HTML final"
                    : "Comentários Wicket são mantidos para debug"));
        
        // Debug - Ajax Debug Mode
        boolean ajaxDebug = app.getDebugSettings().isAjaxDebugModeEnabled();
        add(new Label("ajaxDebug", 
                ajaxDebug ? "✓ Habilitado" : "✗ Desabilitado"));
        add(new Label("ajaxDebugDescricao", 
                ajaxDebug 
                    ? "Ajax Debug Window disponível para inspeção"
                    : "Ajax Debug Window desabilitada"));
        
        // Debug - Development Utilities
        boolean devUtilities = app.getDebugSettings().isDevelopmentUtilitiesEnabled();
        add(new Label("devUtilities", 
                devUtilities ? "✓ Habilitado" : "✗ Desabilitado"));
        add(new Label("devUtilitiesDescricao", 
                devUtilities 
                    ? "Utilitários de desenvolvimento disponíveis"
                    : "Utilitários de desenvolvimento desabilitados"));
        
        // Resources - Minificação
        boolean useMinified = app.getResourceSettings().getUseMinifiedResources();
        add(new Label("useMinified", 
                useMinified ? "✓ Habilitado" : "✗ Desabilitado"));
        add(new Label("useMinifiedDescricao", 
                useMinified 
                    ? "Wicket procura automaticamente por .min.css e .min.js"
                    : "Versões normais de CSS/JS são usadas"));
        
        // Resources - Caching Strategy
        String cachingStrategy = app.getResourceSettings().getCachingStrategy().getClass().getSimpleName();
        add(new Label("cachingStrategy", cachingStrategy));
        add(new Label("cachingStrategyDescricao", 
                isProducao 
                    ? "Cache agressivo com versionamento MD5 no nome do arquivo"
                    : "Sem cache - recursos sempre recarregados"));
        
        // ==================== SEÇÕES DE MODO (Visibilidade condicional) ====================
        
        // Seção visível apenas em DESENVOLVIMENTO
        WebMarkupContainer secaoDesenvolvimento = new WebMarkupContainer("secaoDesenvolvimento");
        secaoDesenvolvimento.setVisible(!isProducao);
        add(secaoDesenvolvimento);
        
        // Seção visível apenas em PRODUÇÃO
        WebMarkupContainer secaoProducao = new WebMarkupContainer("secaoProducao");
        secaoProducao.setVisible(isProducao);
        add(secaoProducao);
        
        // ==================== INSTRUÇÕES PARA ALTERNAR MODO ====================
        
        add(new Label("instrucoesModo", getInstrucoesModo()));
    }
    
    /**
     * Retorna instruções de como alternar entre modos
     */
    private String getInstrucoesModo() {
        return "Para alternar o modo de execução:\n\n" +
               "1. Via Maven (Jetty):\n" +
               "   mvn jetty:run -Dwicket.configuration=deployment\n\n" +
               "2. Via IDE (VM Arguments):\n" +
               "   -Dwicket.configuration=deployment\n\n" +
               "3. Via web.xml:\n" +
               "   <context-param>\n" +
               "       <param-name>configuration</param-name>\n" +
               "       <param-value>deployment</param-value>\n" +
               "   </context-param>";
    }
    
    /**
     * Retorna informações do modo atual para exibição
     */
    public String getDocumentacaoInfo() {
        return "Esta página demonstra as diferenças entre os modos DEVELOPMENT e DEPLOYMENT do Wicket.\n\n" +
               "As configurações mostradas acima são aplicadas automaticamente no método " +
               "WicketApplication.configurarModoProducao().\n\n" +
               "Para mais detalhes, consulte:\n" +
               "• ModoProducaoDocumentacao.java (documentação completa)\n" +
               "• WicketApplication.java (implementação)";
    }
}

