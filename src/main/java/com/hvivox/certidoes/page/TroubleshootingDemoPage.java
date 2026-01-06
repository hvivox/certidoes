package com.hvivox.certidoes.page;

import com.hvivox.certidoes.BasePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * MÓDULO 7 - ITEM 60: PÁGINA DE TROUBLESHOOTING
 * 
 * Página que exibe problemas comuns e soluções de forma interativa.
 * 
 * @see com.hvivox.certidoes.documentacao.TroubleshootingDocumentacao
 */
public class TroubleshootingDemoPage extends BasePage {
    private static final long serialVersionUID = 1L;

    public TroubleshootingDemoPage(final PageParameters parameters) {
        super();

        add(new Label("titulo", Model.of("Troubleshooting - Apache Wicket")));

        // ==================== CATEGORIAS DE PROBLEMAS ====================
        
        List<Categoria> categorias = new ArrayList<>();
        
        // 1. ERROS DE INICIALIZAÇÃO
        Categoria inicializacao = new Categoria("1. Erros de Inicialização");
        inicializacao.addProblema(new Problema(
            "HTTP ERROR 503 Service Unavailable",
            "Servidor não está rodando ou falhou ao iniciar",
            "1. Verifique se o servidor está rodando: mvn jetty:run\n" +
            "2. Verifique erros no console do Maven\n" +
            "3. Verifique se a porta 8080 está disponível"
        ));
        inicializacao.addProblema(new Problema(
            "Address already in use: bind (porta 8080)",
            "Outro processo já está usando a porta 8080",
            "Windows: Get-Process -Name java | Stop-Process -Force\n" +
            "Linux/Mac: lsof -ti:8080 | xargs kill -9"
        ));
        inicializacao.addProblema(new Problema(
            "IllegalArgumentException: Argument 'X' may not be null",
            "Tentativa de passar null para método que não aceita null",
            "Remova a linha que passa null ou forneça um valor válido.\n" +
            "Exemplo: Remova getApplicationSettings().setPageExpiredErrorPage(null);"
        ));
        categorias.add(inicializacao);

        // 2. ERROS DE COMPONENTES
        Categoria componentes = new Categoria("2. Erros de Componentes");
        componentes.addProblema(new Problema(
            "NullPointerException ao acessar componente",
            "Componente sendo usado antes de ser criado/adicionado",
            "Sempre crie e adicione componentes ANTES de usá-los no construtor.\n" +
            "Ordem correta:\n" +
            "1. Criar componente\n" +
            "2. Adicionar à página (add)\n" +
            "3. Usar o componente"
        ));
        componentes.addProblema(new Problema(
            "Unable to find component with id 'X' in page",
            "wicket:id no HTML não corresponde ao ID no Java",
            "Garanta que os IDs sejam EXATAMENTE iguais (case-sensitive).\n" +
            "HTML:  <div wicket:id=\"meuComponente\"></div>\n" +
            "Java:  add(new Label(\"meuComponente\", \"texto\"));"
        ));
        componentes.addProblema(new Problema(
            "Component already added to parent",
            "Tentativa de adicionar mesmo componente duas vezes",
            "Use remove() antes de add(), ou use replace() para atualização.\n" +
            "Exemplo: remove(\"id\"); add(novoComponente);"
        ));
        categorias.add(componentes);

        // 3. ERROS DE SERIALIZAÇÃO
        Categoria serializacao = new Categoria("3. Erros de Serialização");
        serializacao.addProblema(new Problema(
            "NotSerializableException",
            "Objeto não serializável em página/componente",
            "SOLUÇÃO 1 - Marcar como transient:\n" +
            "  private transient CertidaoRepository repository;\n\n" +
            "SOLUÇÃO 2 - Usar LoadableDetachableModel para dados pesados\n\n" +
            "SOLUÇÃO 3 - Tornar a classe Serializable:\n" +
            "  public class MinhaClasse implements Serializable {\n" +
            "      private static final long serialVersionUID = 1L;\n" +
            "  }"
        ));
        categorias.add(serializacao);

        // 4. ERROS DE RECURSOS
        Categoria recursos = new Categoria("4. Erros de Recursos");
        recursos.addProblema(new Problema(
            "CSS/JS não carregam ou aparecem 404",
            "Arquivos em local incorreto",
            "CONVENÇÃO WICKET:\n" +
            "- Arquivos .java vão em: src/main/java/\n" +
            "- Arquivos .html vão em: src/main/java/ (mesmo pacote do .java)\n" +
            "- Arquivos .css/.js vão em: src/main/resources/ (mesmo pacote)\n\n" +
            "EXEMPLO CORRETO:\n" +
            "  src/main/java/com/app/page/MinhaPagina.java\n" +
            "  src/main/java/com/app/page/MinhaPagina.html\n" +
            "  src/main/resources/com/app/page/estilo.css"
        ));
        recursos.addProblema(new Problema(
            "Mudanças no HTML não aparecem",
            "Cache do Wicket/Browser",
            "1. Limpar cache do Maven: mvn clean\n" +
            "2. Recompilar: mvn compile\n" +
            "3. Forçar atualização no browser: Ctrl+F5\n" +
            "4. Configurar hot reload:\n" +
            "   getResourceSettings().setResourcePollFrequency(Duration.seconds(1));"
        ));
        categorias.add(recursos);

        // 5. ERROS DE AJAX
        Categoria ajax = new Categoria("5. Erros de Ajax");
        ajax.addProblema(new Problema(
            "Ajax não atualiza componentes",
            "Componente não marcado para atualização ou sem outputMarkupId",
            "1. Marcar componente com outputMarkupId:\n" +
            "   meuComponente.setOutputMarkupId(true);\n\n" +
            "2. Adicionar ao AjaxRequestTarget:\n" +
            "   target.add(meuComponente);\n\n" +
            "3. Se o componente está dentro de outro, adicionar o pai:\n" +
            "   target.add(containerPai); // Atualiza tudo dentro"
        ));
        ajax.addProblema(new Problema(
            "Ajax Debug Window mostra erro",
            "Erro no código Java durante requisição Ajax",
            "1. Abra Ajax Debug Window: Pressione ~ no teclado\n" +
            "2. Veja o stack trace completo do erro\n" +
            "3. Corrija o erro no código Java\n" +
            "4. Verifique também os logs do servidor"
        ));
        categorias.add(ajax);

        // 6. ERROS DE DEPENDÊNCIAS
        Categoria dependencias = new Categoria("6. Erros de Dependências");
        dependencias.addProblema(new Problema(
            "NoSuchMethodError ou ClassNotFoundException",
            "Conflito de versões de dependências",
            "1. Ver árvore de dependências: mvn dependency:tree\n\n" +
            "2. Excluir dependência conflitante no pom.xml:\n" +
            "   <dependency>\n" +
            "       <exclusions>\n" +
            "           <exclusion>\n" +
            "               <groupId>org.apache.logging.log4j</groupId>\n" +
            "               <artifactId>log4j-api</artifactId>\n" +
            "           </exclusion>\n" +
            "       </exclusions>\n" +
            "   </dependency>\n\n" +
            "3. Limpar e recompilar: mvn clean compile"
        ));
        categorias.add(dependencias);

        // 7. ERROS DE CONFIGURAÇÃO
        Categoria configuracao = new Categoria("7. Erros de Configuração");
        configuracao.addProblema(new Problema(
            "Modo DEPLOYMENT não muda",
            "Configuração incorreta ou ordem errada dos parâmetros",
            "Windows PowerShell:\n" +
            "  mvn `\"-Dwicket.configuration=deployment`\" jetty:run\n" +
            "  (note o escape com backtick antes das aspas)\n\n" +
            "Linux/Mac:\n" +
            "  mvn -Dwicket.configuration=deployment jetty:run\n\n" +
            "VERIFICAR:\n" +
            "- Verifique logs: \"Started Wicket ... in DEPLOYMENT mode\""
        ));
        categorias.add(configuracao);

        // 8. ERROS DE DESENVOLVIMENTO
        Categoria desenvolvimento = new Categoria("8. Erros de Desenvolvimento");
        desenvolvimento.addProblema(new Problema(
            "Encoding incorreto (acentos aparecem como ???)",
            "Falta configuração UTF-8",
            "1. Em WicketApplication.init():\n" +
            "   getRequestCycleSettings().setResponseRequestEncoding(\"UTF-8\");\n" +
            "   getMarkupSettings().setDefaultMarkupEncoding(\"UTF-8\");\n\n" +
            "2. Em pom.xml:\n" +
            "   <properties>\n" +
            "       <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\n" +
            "   </properties>\n\n" +
            "3. Salvar arquivos em UTF-8 na IDE"
        ));
        desenvolvimento.addProblema(new Problema(
            "Session perdida ou dados não persistem",
            "Session não customizada ou dados não serializáveis",
            "1. Criar Session customizada extends WebSession\n" +
            "2. Sobrescrever newSession em WicketApplication\n" +
            "3. Todos os campos devem ser Serializable!\n" +
            "4. Acessar: MinhaSession session = MinhaSession.get();"
        ));
        categorias.add(desenvolvimento);

        // 9. PROBLEMAS DE PERFORMANCE
        Categoria performance = new Categoria("9. Problemas de Performance");
        performance.addProblema(new Problema(
            "Página carrega lentamente",
            "Múltiplas causas possíveis",
            "CAUSAS COMUNS:\n" +
            "1. Carregamento desnecessário de dados no construtor\n" +
            "2. Queries N+1 (carregar dados em loop)\n" +
            "3. Models não detachable\n\n" +
            "SOLUÇÕES:\n" +
            "✓ Use LoadableDetachableModel para dados pesados\n" +
            "✓ Implemente paginação para listas grandes\n" +
            "✓ Use lazy loading quando possível\n" +
            "✓ Cache dados frequentemente acessados\n" +
            "✓ Em produção, use minificação e cache de recursos"
        ));
        categorias.add(performance);

        // ListView de categorias
        add(new ListView<Categoria>("categorias", categorias) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<Categoria> item) {
                Categoria categoria = item.getModelObject();
                item.add(new Label("nomeCategoria", categoria.getNome()));

                // ListView de problemas dentro de cada categoria
                item.add(new ListView<Problema>("problemas", categoria.getProblemas()) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    protected void populateItem(ListItem<Problema> problemaItem) {
                        Problema problema = problemaItem.getModelObject();
                        problemaItem.add(new Label("titulo", problema.getTitulo()));
                        problemaItem.add(new Label("causa", problema.getCausa()));
                        problemaItem.add(new Label("solucao", problema.getSolucao()));
                    }
                });
            }
        });

        // ==================== INFORMAÇÕES ADICIONAIS ====================

        add(new Label("boasPraticas", 
            "✓ Sempre adicione serialVersionUID = 1L em páginas e componentes\n" +
            "✓ Use CompoundPropertyModel para formulários simples\n" +
            "✓ Sempre teste em modo DEVELOPMENT antes de DEPLOYMENT\n" +
            "✓ Use WicketTester para testes unitários\n" +
            "✓ Mantenha lógica de negócio fora de páginas/componentes\n" +
            "✓ Use Models ao invés de passar objetos diretamente\n" +
            "✓ Prefira BookmarkablePageLink quando possível\n" +
            "✓ Sempre detach Models manualmente quando necessário"
        ));

        add(new Label("ferramentasUteis",
            "• Ajax Debug Window: Pressione ~ no teclado\n" +
            "• Browser DevTools: F12\n" +
            "• Wicket Console: Em desenvolvimento, mostra informações úteis\n" +
            "• Maven Dependency Tree: mvn dependency:tree\n" +
            "• Logs do Wicket: Configure log4j para DEBUG quando necessário"
        ));

        add(new Label("comandosUteis",
            "mvn clean                    - Limpa build anterior\n" +
            "mvn compile                  - Compila código\n" +
            "mvn test                     - Executa testes\n" +
            "mvn jetty:run                - Inicia servidor\n" +
            "mvn dependency:tree          - Mostra árvore de dependências\n" +
            "mvn help:effective-pom       - Mostra POM efetivo"
        ));
    }

    // ==================== CLASSES AUXILIARES ====================

    /**
     * Representa uma categoria de problemas
     */
    private static class Categoria implements Serializable {
        private static final long serialVersionUID = 1L;
        private String nome;
        private List<Problema> problemas = new ArrayList<>();

        public Categoria(String nome) {
            this.nome = nome;
        }

        public void addProblema(Problema problema) {
            this.problemas.add(problema);
        }

        public String getNome() {
            return nome;
        }

        public List<Problema> getProblemas() {
            return problemas;
        }
    }

    /**
     * Representa um problema e sua solução
     */
    private static class Problema implements Serializable {
        private static final long serialVersionUID = 1L;
        private String titulo;
        private String causa;
        private String solucao;

        public Problema(String titulo, String causa, String solucao) {
            this.titulo = titulo;
            this.causa = causa;
            this.solucao = solucao;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getCausa() {
            return causa;
        }

        public String getSolucao() {
            return solucao;
        }
    }
}


