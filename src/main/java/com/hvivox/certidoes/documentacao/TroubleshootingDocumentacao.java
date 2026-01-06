package com.hvivox.certidoes.documentacao;

/**
 * MÓDULO 7 - ITEM 60: TROUBLESHOOTING
 * 
 * ==============================================================================
 * GUIA COMPLETO DE TROUBLESHOOTING - APACHE WICKET
 * ==============================================================================
 * 
 * Este documento contém problemas comuns encontrados durante o desenvolvimento
 * com Apache Wicket e suas respectivas soluções práticas.
 * 
 * Os problemas estão organizados por categoria para facilitar a busca.
 * 
 * ==============================================================================
 * ÍNDICE
 * ==============================================================================
 * 
 * 1. ERROS DE INICIALIZAÇÃO
 * 2. ERROS DE COMPONENTES
 * 3. ERROS DE SERIALIZAÇÃO
 * 4. ERROS DE RECURSOS
 * 5. ERROS DE AJAX
 * 6. ERROS DE DEPENDÊNCIAS
 * 7. ERROS DE CONFIGURAÇÃO
 * 8. ERROS DE DESENVOLVIMENTO
 * 9. PROBLEMAS DE PERFORMANCE
 * 10. DICAS GERAIS
 * 
 * ==============================================================================
 * 1. ERROS DE INICIALIZAÇÃO
 * ==============================================================================
 * 
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │ PROBLEMA: HTTP ERROR 503 Service Unavailable                            │
 * ├─────────────────────────────────────────────────────────────────────────┤
 * │ CAUSA:    Servidor não está rodando ou falhou ao iniciar                │
 * │                                                                          │
 * │ SOLUÇÃO:                                                                 │
 * │ 1. Verifique se o servidor está rodando: mvn jetty:run                  │
 * │ 2. Verifique erros no console do Maven                                  │
 * │ 3. Verifique se a porta 8080 está disponível                            │
 * └─────────────────────────────────────────────────────────────────────────┘
 * 
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │ PROBLEMA: Address already in use: bind (porta 8080)                     │
 * ├─────────────────────────────────────────────────────────────────────────┤
 * │ CAUSA:    Outro processo já está usando a porta 8080                    │
 * │                                                                          │
 * │ SOLUÇÃO (Windows):                                                       │
 * │ 1. Encerrar processo Java:                                              │
 * │    Get-Process -Name java | Stop-Process -Force                         │
 * │                                                                          │
 * │ 2. Ou encontrar e matar processo específico:                            │
 * │    netstat -ano | findstr :8080                                         │
 * │    taskkill /PID <número_do_PID> /F                                     │
 * │                                                                          │
 * │ SOLUÇÃO (Linux/Mac):                                                     │
 * │    lsof -ti:8080 | xargs kill -9                                        │
 * └─────────────────────────────────────────────────────────────────────────┘
 * 
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │ PROBLEMA: IllegalArgumentException: Argument 'X' may not be null        │
 * ├─────────────────────────────────────────────────────────────────────────┤
 * │ CAUSA:    Tentativa de passar null para método que não aceita null      │
 * │                                                                          │
 * │ EXEMPLO:  getApplicationSettings().setPageExpiredErrorPage(null);       │
 * │                                                                          │
 * │ SOLUÇÃO:                                                                 │
 * │ - Remova a linha que passa null                                         │
 * │ - Ou forneça um valor válido                                            │
 * │ - Consulte a documentação do método para ver valores aceitos            │
 * └─────────────────────────────────────────────────────────────────────────┘
 * 
 * ==============================================================================
 * 2. ERROS DE COMPONENTES
 * ==============================================================================
 * 
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │ PROBLEMA: NullPointerException ao acessar componente                    │
 * ├─────────────────────────────────────────────────────────────────────────┤
 * │ CAUSA:    Componente sendo usado antes de ser criado/adicionado         │
 * │                                                                          │
 * │ EXEMPLO DO ERRO:                                                         │
 * │   tableContainer = new WebMarkupContainer("table");                     │
 * │   add(tableContainer);                                                   │
 * │   atualizarLista(); // ← Usa emptyMessage aqui                         │
 * │   emptyMessage = new WebMarkupContainer("empty"); // ← Criado depois!   │
 * │   add(emptyMessage);                                                     │
 * │                                                                          │
 * │ SOLUÇÃO:                                                                 │
 * │ Sempre crie e adicione componentes ANTES de usá-los:                    │
 * │   tableContainer = new WebMarkupContainer("table");                     │
 * │   add(tableContainer);                                                   │
 * │   emptyMessage = new WebMarkupContainer("empty");                       │
 * │   add(emptyMessage);                                                     │
 * │   atualizarLista(); // Agora pode usar ambos!                           │
 * └─────────────────────────────────────────────────────────────────────────┘
 * 
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │ PROBLEMA: Unable to find component with id 'X' in page                  │
 * ├─────────────────────────────────────────────────────────────────────────┤
 * │ CAUSA:    wicket:id no HTML não corresponde ao ID no Java               │
 * │                                                                          │
 * │ EXEMPLO:                                                                 │
 * │   HTML:  <div wicket:id="meuComponente"></div>                          │
 * │   Java:  add(new Label("meucomponente", "texto")); // ID diferente!     │
 * │                                                                          │
 * │ SOLUÇÃO:                                                                 │
 * │ Garanta que os IDs sejam EXATAMENTE iguais (case-sensitive):            │
 * │   HTML:  <div wicket:id="meuComponente"></div>                          │
 * │   Java:  add(new Label("meuComponente", "texto")); // ID igual!         │
 * └─────────────────────────────────────────────────────────────────────────┘
 * 
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │ PROBLEMA: Component already added to parent                             │
 * ├─────────────────────────────────────────────────────────────────────────┤
 * │ CAUSA:    Tentativa de adicionar mesmo componente duas vezes            │
 * │                                                                          │
 * │ SOLUÇÃO:                                                                 │
 * │ - Remova o componente antes de adicionar novamente: remove("id")        │
 * │ - Ou crie uma nova instância do componente                              │
 * │ - Para atualizar via Ajax, use replace() ao invés de add()              │
 * └─────────────────────────────────────────────────────────────────────────┘
 * 
 * ==============================================================================
 * 3. ERROS DE SERIALIZAÇÃO
 * ==============================================================================
 * 
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │ PROBLEMA: NotSerializableException                                      │
 * ├─────────────────────────────────────────────────────────────────────────┤
 * │ CAUSA:    Objeto não serializável em página/componente                  │
 * │                                                                          │
 * │ EXPLICAÇÃO:                                                              │
 * │ Wicket serializa páginas para permitir navegação "voltar" no browser.   │
 * │ Todos os campos de páginas/componentes devem ser serializáveis.         │
 * │                                                                          │
 * │ SOLUÇÃO 1 - Marcar como transient:                                      │
 * │   private transient CertidaoRepository repository;                      │
 * │   // Recriar quando necessário com lazy initialization                  │
 * │                                                                          │
 * │ SOLUÇÃO 2 - Usar LoadableDetachableModel:                               │
 * │   new LoadableDetachableModel<Certidao>() {                             │
 * │       @Override                                                          │
 * │       protected Certidao load() {                                       │
 * │           return repository.findById(id);                               │
 * │       }                                                                  │
 * │   }                                                                      │
 * │                                                                          │
 * │ SOLUÇÃO 3 - Tornar a classe Serializable:                               │
 * │   public class MinhaClasse implements Serializable {                    │
 * │       private static final long serialVersionUID = 1L;                  │
 * │   }                                                                      │
 * └─────────────────────────────────────────────────────────────────────────┘
 * 
 * ==============================================================================
 * 4. ERROS DE RECURSOS
 * ==============================================================================
 * 
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │ PROBLEMA: CSS/JS não carregam ou aparecem 404                           │
 * ├─────────────────────────────────────────────────────────────────────────┤
 * │ CAUSA:    Arquivos em local incorreto                                   │
 * │                                                                          │
 * │ CONVENÇÃO WICKET:                                                        │
 * │ - Arquivos .java vão em: src/main/java/                                │
 * │ - Arquivos .html vão em: src/main/java/ (mesmo pacote do .java)        │
 * │ - Arquivos .css/.js vão em: src/main/resources/ (mesmo pacote)         │
 * │                                                                          │
 * │ EXEMPLO CORRETO:                                                         │
 * │   src/main/java/com/app/page/MinhaPagina.java                          │
 * │   src/main/java/com/app/page/MinhaPagina.html                          │
 * │   src/main/resources/com/app/page/estilo.css                           │
 * │   src/main/resources/com/app/page/script.js                            │
 * └─────────────────────────────────────────────────────────────────────────┘
 * 
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │ PROBLEMA: Mudanças no HTML não aparecem                                 │
 * ├─────────────────────────────────────────────────────────────────────────┤
 * │ CAUSA:    Cache do Wicket/Browser                                       │
 * │                                                                          │
 * │ SOLUÇÃO:                                                                 │
 * │ 1. Limpar cache do Maven: mvn clean                                     │
 * │ 2. Recompilar: mvn compile                                              │
 * │ 3. Forçar atualização no browser: Ctrl+F5 ou Ctrl+Shift+R              │
 * │ 4. Em desenvolvimento, configure:                                        │
 * │    getResourceSettings().setResourcePollFrequency(Duration.seconds(1)); │
 * └─────────────────────────────────────────────────────────────────────────┘
 * 
 * ==============================================================================
 * 5. ERROS DE AJAX
 * ==============================================================================
 * 
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │ PROBLEMA: Ajax não atualiza componentes                                 │
 * ├─────────────────────────────────────────────────────────────────────────┤
 * │ CAUSA:    Componente não marcado para atualização ou sem outputMarkupId │
 * │                                                                          │
 * │ SOLUÇÃO:                                                                 │
 * │ 1. Marcar componente com outputMarkupId:                                │
 * │    meuComponente.setOutputMarkupId(true);                               │
 * │                                                                          │
 * │ 2. Adicionar ao AjaxRequestTarget:                                      │
 * │    target.add(meuComponente);                                           │
 * │                                                                          │
 * │ 3. Se o componente está dentro de outro, adicionar o pai:               │
 * │    target.add(containerPai); // Atualiza tudo dentro                   │
 * └─────────────────────────────────────────────────────────────────────────┘
 * 
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │ PROBLEMA: Ajax Debug Window mostra erro                                 │
 * ├─────────────────────────────────────────────────────────────────────────┤
 * │ CAUSA:    Erro no código Java durante requisição Ajax                   │
 * │                                                                          │
 * │ SOLUÇÃO:                                                                 │
 * │ 1. Abra Ajax Debug Window: Pressione ~ no teclado                       │
 * │ 2. Veja o stack trace completo do erro                                  │
 * │ 3. Corrija o erro no código Java                                        │
 * │ 4. Verifique também os logs do servidor                                 │
 * └─────────────────────────────────────────────────────────────────────────┘
 * 
 * ==============================================================================
 * 6. ERROS DE DEPENDÊNCIAS
 * ==============================================================================
 * 
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │ PROBLEMA: NoSuchMethodError ou ClassNotFoundException                   │
 * ├─────────────────────────────────────────────────────────────────────────┤
 * │ CAUSA:    Conflito de versões de dependências                           │
 * │                                                                          │
 * │ EXEMPLO:                                                                 │
 * │   org.apache.logging.log4j.util.LoaderUtil.getClassLoaders()...        │
 * │                                                                          │
 * │ SOLUÇÃO:                                                                 │
 * │ 1. Ver árvore de dependências:                                          │
 * │    mvn dependency:tree                                                   │
 * │                                                                          │
 * │ 2. Excluir dependência conflitante no pom.xml:                          │
 * │    <dependency>                                                          │
 * │        <groupId>org.apache.poi</groupId>                                │
 * │        <artifactId>poi</artifactId>                                     │
 * │        <exclusions>                                                      │
 * │            <exclusion>                                                   │
 * │                <groupId>org.apache.logging.log4j</groupId>              │
 * │                <artifactId>log4j-api</artifactId>                       │
 * │            </exclusion>                                                  │
 * │        </exclusions>                                                     │
 * │    </dependency>                                                         │
 * │                                                                          │
 * │ 3. Limpar e recompilar:                                                 │
 * │    mvn clean compile                                                     │
 * └─────────────────────────────────────────────────────────────────────────┘
 * 
 * ==============================================================================
 * 7. ERROS DE CONFIGURAÇÃO
 * ==============================================================================
 * 
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │ PROBLEMA: Modo DEPLOYMENT não muda                                      │
 * ├─────────────────────────────────────────────────────────────────────────┤
 * │ CAUSA:    Configuração incorreta ou ordem errada dos parâmetros         │
 * │                                                                          │
 * │ SOLUÇÃO (Windows PowerShell):                                            │
 * │   mvn `"-Dwicket.configuration=deployment`" jetty:run                   │
 * │   (note o escape com backtick antes das aspas)                          │
 * │                                                                          │
 * │ SOLUÇÃO (Linux/Mac):                                                     │
 * │   mvn -Dwicket.configuration=deployment jetty:run                       │
 * │                                                                          │
 * │ VERIFICAR:                                                               │
 * │ - Não sobrescreva getConfigurationType() em WicketApplication           │
 * │ - Verifique logs: "Started Wicket ... in DEPLOYMENT mode"               │
 * └─────────────────────────────────────────────────────────────────────────┘
 * 
 * ==============================================================================
 * 8. ERROS DE DESENVOLVIMENTO
 * ==============================================================================
 * 
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │ PROBLEMA: Encoding incorreto (acentos aparecem como ???)                │
 * ├─────────────────────────────────────────────────────────────────────────┤
 * │ CAUSA:    Falta configuração UTF-8                                      │
 * │                                                                          │
 * │ SOLUÇÃO:                                                                 │
 * │ 1. Em WicketApplication.init():                                         │
 * │    getRequestCycleSettings().setResponseRequestEncoding("UTF-8");       │
 * │    getMarkupSettings().setDefaultMarkupEncoding("UTF-8");               │
 * │                                                                          │
 * │ 2. Em pom.xml:                                                           │
 * │    <properties>                                                          │
 * │        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>│
 * │    </properties>                                                         │
 * │                                                                          │
 * │ 3. Salvar arquivos em UTF-8 na IDE                                      │
 * └─────────────────────────────────────────────────────────────────────────┘
 * 
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │ PROBLEMA: Session perdida ou dados não persistem                        │
 * ├─────────────────────────────────────────────────────────────────────────┤
 * │ CAUSA:    Session não customizada ou dados não serializáveis            │
 * │                                                                          │
 * │ SOLUÇÃO:                                                                 │
 * │ 1. Criar Session customizada:                                           │
 * │    public class MinhaSession extends WebSession {                       │
 * │        private String dados; // Deve ser Serializable!                  │
 * │        ...                                                               │
 * │    }                                                                     │
 * │                                                                          │
 * │ 2. Sobrescrever newSession em WicketApplication:                        │
 * │    @Override                                                             │
 * │    public WebSession newSession(Request request, Response response) {   │
 * │        return new MinhaSession(request);                                │
 * │    }                                                                     │
 * │                                                                          │
 * │ 3. Acessar session:                                                      │
 * │    MinhaSession session = (MinhaSession) Session.get();                 │
 * │    ou                                                                    │
 * │    MinhaSession session = MinhaSession.get();                           │
 * └─────────────────────────────────────────────────────────────────────────┘
 * 
 * ==============================================================================
 * 9. PROBLEMAS DE PERFORMANCE
 * ==============================================================================
 * 
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │ PROBLEMA: Página carrega lentamente                                     │
 * ├─────────────────────────────────────────────────────────────────────────┤
 * │ CAUSAS COMUNS:                                                           │
 * │ 1. Carregamento desnecessário de dados no construtor                    │
 * │ 2. Queries N+1 (carregar dados em loop)                                │
 * │ 3. Models não detachable                                                │
 * │                                                                          │
 * │ SOLUÇÕES:                                                                │
 * │ 1. Use LoadableDetachableModel para dados pesados                       │
 * │ 2. Implemente paginação para listas grandes                             │
 * │ 3. Use lazy loading quando possível                                     │
 * │ 4. Cache dados frequentemente acessados                                 │
 * │ 5. Em produção, use minificação e cache de recursos                     │
 * └─────────────────────────────────────────────────────────────────────────┘
 * 
 * ==============================================================================
 * 10. DICAS GERAIS
 * ==============================================================================
 * 
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │ BOAS PRÁTICAS                                                            │
 * ├─────────────────────────────────────────────────────────────────────────┤
 * │ ✓ Sempre adicione serialVersionUID = 1L em páginas e componentes        │
 * │ ✓ Use CompoundPropertyModel para formulários simples                    │
 * │ ✓ Sempre teste em modo DEVELOPMENT antes de DEPLOYMENT                  │
 * │ ✓ Use WicketTester para testes unitários                                │
 * │ ✓ Mantenha lógica de negócio fora de páginas/componentes                │
 * │ ✓ Use Models ao invés de passar objetos diretamente                     │
 * │ ✓ Prefira BookmarkablePageLink quando possível                          │
 * │ ✓ Sempre detach Models manualmente quando necessário                    │
 * └─────────────────────────────────────────────────────────────────────────┘
 * 
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │ FERRAMENTAS ÚTEIS                                                        │
 * ├─────────────────────────────────────────────────────────────────────────┤
 * │ • Ajax Debug Window: Pressione ~ no teclado                             │
 * │ • Browser DevTools: F12                                                  │
 * │ • Wicket Console: Em desenvolvimento, mostra informações úteis           │
 * │ • Maven Dependency Tree: mvn dependency:tree                             │
 * │ • Logs do Wicket: Configure log4j para DEBUG quando necessário          │
 * └─────────────────────────────────────────────────────────────────────────┘
 * 
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │ COMANDOS ÚTEIS                                                           │
 * ├─────────────────────────────────────────────────────────────────────────┤
 * │ mvn clean                    - Limpa build anterior                     │
 * │ mvn compile                  - Compila código                           │
 * │ mvn test                     - Executa testes                           │
 * │ mvn jetty:run                - Inicia servidor                          │
 * │ mvn dependency:tree          - Mostra árvore de dependências            │
 * │ mvn help:effective-pom       - Mostra POM efetivo                       │
 * └─────────────────────────────────────────────────────────────────────────┘
 * 
 * ==============================================================================
 * RECURSOS ADICIONAIS
 * ==============================================================================
 * 
 * • Documentação Oficial: https://wicket.apache.org/
 * • Wiki do Wicket: https://cwiki.apache.org/confluence/display/WICKET
 * • Stack Overflow: Tag [apache-wicket]
 * • Mailing List: users@wicket.apache.org
 * 
 * ==============================================================================
 * 
 * @author HVivox Certidões
 * @version 1.0
 * @since 2026-01-05
 */
public class TroubleshootingDocumentacao {
    // Esta é uma classe de documentação apenas.
    // Consulte o conteúdo acima para soluções de problemas.
    
    private TroubleshootingDocumentacao() {
        // Construtor privado - classe não deve ser instanciada
    }
}


