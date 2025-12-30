package com.hvivox.certidoes.documentacao;

/**
 * ====================================================================
 * DOCUMENTAÇÃO: MODELOS (MODELS) - MÓDULO 3: MODELOS
 * ====================================================================
 * 
 * Esta classe contém a documentação sobre Models no Apache Wicket.
 * 
 * Models são a camada de abstração que conecta os dados aos componentes,
 * permitindo desacoplamento e reutilização.
 */
public class ModelosDocumentacao {

    /**
     * Retorna o conteúdo HTML formatado da documentação sobre Revisão de Models.
     * 
     * ITEM 1: Revisão de Models
     * 
     * @return HTML formatado com a documentação
     */
    public static String getConteudoRevisao() {
        StringBuilder html = new StringBuilder();
        html.append("<h6>Revisão de Models</h6>");
        html.append(
                "<p>Os <strong>Models</strong> são a camada de abstração que conecta os dados aos componentes no Wicket.</p>");

        html.append("<h6>O que é um Model?</h6>");
        html.append(
                "<p>Um <code>Model</code> é uma interface (<code>IModel&lt;T&gt;</code>) que fornece dados aos componentes Wicket.</p>");
        html.append(
                "<p>Em vez de passar objetos diretamente aos componentes, passamos um <code>Model</code> que encapsula o objeto.</p>");

        html.append("<h6>Interface IModel&lt;T&gt;</h6>");
        html.append("<pre><code>");
        html.append("public interface IModel&lt;T&gt; extends Serializable {\n");
        html.append("    T getObject();           // Obtém o objeto do modelo\n");
        html.append("    void setObject(T object); // Define o objeto do modelo\n");
        html.append("    void detach();            // Libera recursos (opcional)\n");
        html.append("}\n");
        html.append("</code></pre>");

        html.append("<h6>Por que usar Models?</h6>");
        html.append("<ul>");
        html.append("<li><strong>Desacoplamento</strong>: Separa os dados da apresentação</li>");
        html.append("<li><strong>Reutilização</strong>: O mesmo Model pode ser usado em múltiplos componentes</li>");
        html.append("<li><strong>Lazy Loading</strong>: Permite carregar dados sob demanda</li>");
        html.append("<li><strong>Serialização</strong>: Facilita a serialização de páginas</li>");
        html.append(
                "<li><strong>Atualização automática</strong>: Componentes são atualizados quando o Model muda</li>");
        html.append("</ul>");

        html.append("<h6>Model vs Objeto Direto</h6>");
        html.append("<p><strong>❌ ERRADO:</strong> Passar objeto diretamente</p>");
        html.append("<pre><code>");
        html.append("String nome = \"João\";\n");
        html.append("add(new Label(\"nome\", nome)); // ❌ Não funciona bem\n");
        html.append("</code></pre>");

        html.append("<p><strong>✅ CORRETO:</strong> Usar Model</p>");
        html.append("<pre><code>");
        html.append("String nome = \"João\";\n");
        html.append("add(new Label(\"nome\", Model.of(nome))); // ✅ Correto\n");
        html.append("</code></pre>");

        html.append("<h6>Tipos de Models no Wicket</h6>");
        html.append("<ul>");
        html.append("<li><strong>Model</strong>: Modelo simples para valores imutáveis</li>");
        html.append("<li><strong>PropertyModel</strong>: Acessa propriedades de objetos</li>");
        html.append("<li><strong>CompoundPropertyModel</strong>: Bind automático de propriedades</li>");
        html.append("<li><strong>LoadableDetachableModel</strong>: Carrega dados sob demanda</li>");
        html.append("<li><strong>Model</strong> customizado: Implementação própria de IModel</li>");
        html.append("</ul>");

        html.append("<h6>Exemplo Básico:</h6>");
        html.append("<pre><code>");
        html.append("// Criar um Model simples\n");
        html.append("IModel&lt;String&gt; model = Model.of(\"Olá, Wicket!\");\n");
        html.append("\n");
        html.append("// Usar o Model em um componente\n");
        html.append("add(new Label(\"mensagem\", model));\n");
        html.append("</code></pre>");

        html.append("<h6>Links Relacionados:</h6>");
        html.append("<ul>");
        html.append(
                "<li><a href=\"https://wicket.apache.org/learn/guide/models.html\" target=\"_blank\">Documentação Oficial: Models</a></li>");
        html.append(
                "<li><a href=\"https://cwiki.apache.org/confluence/display/WICKET/Working+with+Wicket+models\" target=\"_blank\">Trabalhando com Models (Wiki)</a></li>");
        html.append("</ul>");

        return html.toString();
    }

    /**
     * Retorna o conteúdo HTML formatado da documentação sobre Padrões (Standards).
     * 
     * ITEM 2: Padrões (Standards)
     * 
     * @return HTML formatado com a documentação
     */
    public static String getConteudoPadroes() {
        StringBuilder html = new StringBuilder();
        html.append("<h6>Padrões (Standards) de Uso de Models</h6>");
        html.append(
                "<p>Existem <strong>padrões e boas práticas</strong> para usar Models no Wicket de forma eficiente e segura.</p>");

        html.append("<h6>1. Model.of() - Modelo Simples</h6>");
        html.append("<p><strong>Quando usar:</strong> Para valores simples, imutáveis ou que não mudam.</p>");
        html.append("<pre><code>");
        html.append("// String simples\n");
        html.append("add(new Label(\"titulo\", Model.of(\"Minha Página\")));\n");
        html.append("\n");
        html.append("// Número\n");
        html.append("add(new Label(\"contador\", Model.of(42)));\n");
        html.append("\n");
        html.append("// Objeto completo (cuidado com serialização!)\n");
        html.append("Certidao certidao = ...;\n");
        html.append("add(new Label(\"numero\", Model.of(certidao.getNumero())));\n");
        html.append("</code></pre>");

        html.append("<h6>2. PropertyModel - Acessar Propriedades</h6>");
        html.append("<p><strong>Quando usar:</strong> Para acessar propriedades de objetos de forma dinâmica.</p>");
        html.append("<pre><code>");
        html.append("Certidao certidao = new Certidao();\n");
        html.append("certidao.setNumero(\"123/2024\");\n");
        html.append("\n");
        html.append("// Acessa certidao.getNumero() automaticamente\n");
        html.append("add(new Label(\"numero\", new PropertyModel&lt;&gt;(certidao, \"numero\")));\n");
        html.append("\n");
        html.append("// Acessa propriedades aninhadas\n");
        html.append("add(new Label(\"tipoDescricao\", new PropertyModel&lt;&gt;(certidao, \"tipo.descricao\")));\n");
        html.append("</code></pre>");
        html.append("<p><strong>Vantagens:</strong></p>");
        html.append("<ul>");
        html.append("<li>Atualização automática quando o objeto muda</li>");
        html.append("<li>Suporta propriedades aninhadas (ex: \"pessoa.endereco.rua\")</li>");
        html.append("<li>Fácil de usar e entender</li>");
        html.append("</ul>");

        html.append("<h6>3. CompoundPropertyModel - Bind Automático</h6>");
        html.append(
                "<p><strong>Quando usar:</strong> Em formulários onde os campos têm o mesmo nome das propriedades do objeto.</p>");
        html.append("<pre><code>");
        html.append("Certidao certidao = new Certidao();\n");
        html.append("\n");
        html.append("// Criar formulário com CompoundPropertyModel\n");
        html.append("Form&lt;Certidao&gt; form = new Form&lt;&gt;(\"form\", \n");
        html.append("    new CompoundPropertyModel&lt;&gt;(certidao));\n");
        html.append("\n");
        html.append("// Campos se vinculam automaticamente pelo wicket:id\n");
        html.append("form.add(new TextField&lt;&gt;(\"numero\")); // Vincula a certidao.numero\n");
        html.append("form.add(new TextField&lt;&gt;(\"interessado\")); // Vincula a certidao.interessado\n");
        html.append("</code></pre>");
        html.append("<p><strong>Vantagens:</strong></p>");
        html.append("<ul>");
        html.append("<li>Menos código: não precisa especificar o Model em cada campo</li>");
        html.append("<li>Bind automático pelo nome do wicket:id</li>");
        html.append("<li>Ideal para formulários CRUD</li>");
        html.append("</ul>");

        html.append("<h6>4. Boas Práticas</h6>");
        html.append("<h6>✅ FAZER:</h6>");
        html.append("<ul>");
        html.append("<li>Usar <code>Model.of()</code> para valores simples e imutáveis</li>");
        html.append("<li>Usar <code>PropertyModel</code> para acessar propriedades de objetos</li>");
        html.append("<li>Usar <code>CompoundPropertyModel</code> em formulários</li>");
        html.append("<li>Usar <code>LoadableDetachableModel</code> para objetos pesados ou de banco de dados</li>");
        html.append("<li>Marcar campos não serializáveis como <code>transient</code></li>");
        html.append("</ul>");

        html.append("<h6>❌ EVITAR:</h6>");
        html.append("<ul>");
        html.append("<li>Passar objetos diretamente aos componentes (sem Model)</li>");
        html.append("<li>Armazenar objetos grandes em Models estáticos</li>");
        html.append("<li>Manter referências a objetos não serializáveis em Models</li>");
        html.append("<li>Usar <code>Model.of()</code> com objetos que mudam frequentemente</li>");
        html.append("<li>Criar Models desnecessariamente complexos</li>");
        html.append("</ul>");

        html.append("<h6>5. Padrão de Nomenclatura</h6>");
        html.append("<p>Use nomes descritivos para seus Models:</p>");
        html.append("<pre><code>");
        html.append("// ✅ BOM\n");
        html.append("IModel&lt;Certidao&gt; certidaoModel = Model.of(certidao);\n");
        html.append("IModel&lt;String&gt; nomeModel = new PropertyModel&lt;&gt;(pessoa, \"nome\");\n");
        html.append("\n");
        html.append("// ❌ EVITAR\n");
        html.append("IModel m = Model.of(certidao);\n");
        html.append("IModel n = new PropertyModel(pessoa, \"nome\");\n");
        html.append("</code></pre>");

        html.append("<h6>6. Exemplo Completo: Comparação de Padrões</h6>");
        html.append("<pre><code>");
        html.append("// Cenário: Exibir dados de uma Certidão\n");
        html.append("Certidao certidao = repository.findById(1L);\n");
        html.append("\n");
        html.append("// Padrão 1: Model.of() - Valor direto\n");
        html.append("add(new Label(\"numero\", Model.of(certidao.getNumero())));\n");
        html.append("\n");
        html.append("// Padrão 2: PropertyModel - Propriedade dinâmica\n");
        html.append("add(new Label(\"numero\", new PropertyModel&lt;&gt;(certidao, \"numero\")));\n");
        html.append("\n");
        html.append("// Padrão 3: CompoundPropertyModel - Bind automático\n");
        html.append("setDefaultModel(new CompoundPropertyModel&lt;&gt;(certidao));\n");
        html.append("add(new Label(\"numero\")); // Vincula automaticamente\n");
        html.append("</code></pre>");

        html.append("<h6>7. Quando Usar Cada Padrão?</h6>");
        html.append("<table class=\"table table-bordered\">");
        html.append("<thead>");
        html.append("<tr>");
        html.append("<th>Padrão</th>");
        html.append("<th>Quando Usar</th>");
        html.append("<th>Exemplo</th>");
        html.append("</tr>");
        html.append("</thead>");
        html.append("<tbody>");
        html.append("<tr>");
        html.append("<td><code>Model.of()</code></td>");
        html.append("<td>Valores simples, imutáveis</td>");
        html.append("<td>Labels, mensagens, constantes</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td><code>PropertyModel</code></td>");
        html.append("<td>Acessar propriedades de objetos</td>");
        html.append("<td>Exibir dados de entidades</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td><code>CompoundPropertyModel</code></td>");
        html.append("<td>Formulários com bind automático</td>");
        html.append("<td>Formulários CRUD</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td><code>LoadableDetachableModel</code></td>");
        html.append("<td>Objetos pesados, banco de dados</td>");
        html.append("<td>Entidades JPA, objetos grandes</td>");
        html.append("</tr>");
        html.append("</tbody>");
        html.append("</table>");

        html.append("<h6>Links Relacionados:</h6>");
        html.append("<ul>");
        html.append(
                "<li><a href=\"https://wicket.apache.org/learn/guide/models.html\" target=\"_blank\">Documentação Oficial: Models</a></li>");
        html.append(
                "<li><a href=\"https://cwiki.apache.org/confluence/display/WICKET/Working+with+Wicket+models\" target=\"_blank\">Trabalhando com Models (Wiki)</a></li>");
        html.append("</ul>");

        return html.toString();
    }

    /**
     * Retorna o conteúdo HTML formatado da documentação sobre
     * LoadableDetachableModel.
     * 
     * ITEM 7: LoadableDetachableModel
     * 
     * @return HTML formatado com a documentação
     */
    public static String getConteudoLoadableDetachableModel() {
        StringBuilder html = new StringBuilder();
        html.append("<h6>LoadableDetachableModel</h6>");
        html.append(
                "<p>O <code>LoadableDetachableModel</code> é um tipo especial de Model que carrega objetos sob demanda e os libera da memória automaticamente.</p>");

        html.append("<h6>O que é LoadableDetachableModel?</h6>");
        html.append(
                "<p>É uma classe abstrata do Wicket que estende <code>AbstractReadOnlyModel</code> e implementa o padrão <strong>Lazy Loading</strong>.</p>");
        html.append(
                "<p>Diferente de <code>Model.of()</code>, o <code>LoadableDetachableModel</code> não mantém o objeto em memória entre requisições.</p>");

        html.append("<h6>Como Funciona?</h6>");
        html.append("<p>O <code>LoadableDetachableModel</code> funciona em três etapas:</p>");
        html.append("<ol>");
        html.append("<li><strong>load()</strong>: Carrega o objeto quando necessário (lazy loading)</li>");
        html.append(
                "<li><strong>attach()</strong>: Mantém o objeto em memória durante o processamento da requisição</li>");
        html.append("<li><strong>detach()</strong>: Libera o objeto da memória após o uso</li>");
        html.append("</ol>");

        html.append("<h6>Estrutura da Classe:</h6>");
        html.append("<pre><code>");
        html.append(
                "public abstract class LoadableDetachableModel&lt;T&gt; extends AbstractReadOnlyModel&lt;T&gt; {\n");
        html.append("    protected abstract T load();  // Você implementa este método\n");
        html.append("    // attach() e detach() são gerenciados automaticamente pelo Wicket\n");
        html.append("}\n");
        html.append("</code></pre>");

        html.append("<h6>Vantagens do LoadableDetachableModel:</h6>");
        html.append("<ul>");
        html.append(
                "<li><strong>Evita problemas de serialização</strong>: Apenas dados leves (como ID) são serializados, não o objeto completo</li>");
        html.append("<li><strong>Lazy Loading</strong>: O objeto só é carregado quando realmente necessário</li>");
        html.append("<li><strong>Performance</strong>: Libera memória automaticamente após o uso</li>");
        html.append("<li><strong>Segurança</strong>: Evita manter objetos grandes em memória entre requisições</li>");
        html.append(
                "<li><strong>Atualização automática</strong>: Se o objeto mudar no banco, será recarregado na próxima requisição</li>");
        html.append("</ul>");

        html.append("<h6>Quando Usar LoadableDetachableModel?</h6>");
        html.append("<ul>");
        html.append("<li>Objetos pesados (entidades JPA, objetos grandes)</li>");
        html.append("<li>Objetos que vêm de banco de dados</li>");
        html.append("<li>Quando você quer evitar problemas de serialização</li>");
        html.append("<li>Quando o objeto pode mudar entre requisições</li>");
        html.append("<li>Quando você não quer manter o objeto em memória entre requisições</li>");
        html.append("</ul>");

        html.append("<h6>Exemplo de Implementação:</h6>");
        html.append("<pre><code>");
        html.append("public class CertidaoLoadableDetachableModel extends LoadableDetachableModel&lt;Certidao&gt; {\n");
        html.append("    private final Long certidaoId;  // Apenas o ID é serializado\n");
        html.append("    private transient CertidaoRepository repository;  // Não serializável\n");
        html.append("\n");
        html.append("    public CertidaoLoadableDetachableModel(Long certidaoId) {\n");
        html.append("        this.certidaoId = certidaoId;\n");
        html.append("    }\n");
        html.append("\n");
        html.append("    @Override\n");
        html.append("    protected Certidao load() {\n");
        html.append("        // Carrega do repositório/banco de dados\n");
        html.append("        return repository.findById(certidaoId).orElseThrow(...);\n");
        html.append("    }\n");
        html.append("}\n");
        html.append("</code></pre>");

        html.append("<h6>Exemplo de Uso:</h6>");
        html.append("<pre><code>");
        html.append("// Criar o Model com o ID\n");
        html.append("CertidaoLoadableDetachableModel model = new CertidaoLoadableDetachableModel(1L);\n");
        html.append("\n");
        html.append("// Usar o Model em componentes\n");
        html.append("add(new Label(\"numero\", new PropertyModel&lt;&gt;(model, \"numero\")));\n");
        html.append("add(new Label(\"interessado\", new PropertyModel&lt;&gt;(model, \"interessado\")));\n");
        html.append("</code></pre>");

        html.append("<h6>Comparação: Model.of() vs LoadableDetachableModel</h6>");
        html.append("<table class=\"table table-bordered\">");
        html.append("<thead>");
        html.append("<tr>");
        html.append("<th>Aspecto</th>");
        html.append("<th>Model.of()</th>");
        html.append("<th>LoadableDetachableModel</th>");
        html.append("</tr>");
        html.append("</thead>");
        html.append("<tbody>");
        html.append("<tr>");
        html.append("<td><strong>Serialização</strong></td>");
        html.append("<td>Serializa o objeto completo</td>");
        html.append("<td>Serializa apenas dados leves (ID)</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td><strong>Memória</strong></td>");
        html.append("<td>Mantém objeto em memória</td>");
        html.append("<td>Libera objeto após uso</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td><strong>Carregamento</strong></td>");
        html.append("<td>Imediato</td>");
        html.append("<td>Sob demanda (lazy)</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td><strong>Atualização</strong></td>");
        html.append("<td>Objeto pode ficar desatualizado</td>");
        html.append("<td>Recarrega a cada requisição</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td><strong>Uso recomendado</strong></td>");
        html.append("<td>Valores simples, imutáveis</td>");
        html.append("<td>Objetos pesados, de banco de dados</td>");
        html.append("</tr>");
        html.append("</tbody>");
        html.append("</table>");

        html.append("<h6>Exemplo no Projeto:</h6>");
        html.append(
                "<p>Veja <code>CertidaoLoadableDetachableModel.java</code> que demonstra uma implementação completa.</p>");
        html.append(
                "<p>Este Model pode ser usado em qualquer página que precise exibir dados de uma Certidão sem manter o objeto em memória.</p>");

        html.append("<h6>Boas Práticas:</h6>");
        html.append("<ul>");
        html.append("<li>Armazene apenas dados leves no Model (IDs, chaves primárias)</li>");
        html.append("<li>Marque dependências não serializáveis como <code>transient</code></li>");
        html.append("<li>Recrie dependências no método <code>load()</code> se necessário</li>");
        html.append("<li>Use para objetos que vêm de banco de dados ou serviços externos</li>");
        html.append("<li>Evite usar para objetos simples ou valores primitivos</li>");
        html.append("</ul>");

        html.append("<h6>Links Relacionados:</h6>");
        html.append("<ul>");
        html.append(
                "<li><a href=\"https://wicket.apache.org/learn/guide/models.html#models_3\" target=\"_blank\">Documentação Oficial: LoadableDetachableModel</a></li>");
        html.append(
                "<li><a href=\"https://cwiki.apache.org/confluence/display/WICKET/LoadableDetachableModel\" target=\"_blank\">LoadableDetachableModel (Wiki)</a></li>");
        html.append("</ul>");

        return html.toString();
    }

    public static String getConteudoDetachableModels() {
        StringBuilder html = new StringBuilder();
        html.append("<h6>Modelos Destacáveis (Detachable Models)</h6>");
        html.append(
                "<p>Models destacáveis são Models que implementam o método <code>detach()</code> para liberar objetos da memória após o uso.</p>");
        html.append(
                "<p>O Wicket chama <code>detach()</code> automaticamente após processar cada requisição, permitindo que objetos pesados sejam liberados da memória.</p>");

        html.append("<h6>O que são Detachable Models?</h6>");
        html.append(
                "<p>Qualquer Model que implementa <code>IModel&lt;T&gt;</code> pode implementar <code>detach()</code> para liberar recursos.</p>");
        html.append(
                "<p>O método <code>detach()</code> é chamado automaticamente pelo Wicket após o processamento da requisição.</p>");

        html.append("<h6>Interface IModel com detach():</h6>");
        html.append("<pre><code>");
        html.append("public interface IModel&lt;T&gt; extends Serializable {\n");
        html.append("    T getObject();\n");
        html.append("    void setObject(T object);\n");
        html.append("    void detach();  // Libera recursos (opcional)\n");
        html.append("}\n");
        html.append("</code></pre>");

        html.append("<h6>Ciclo de Vida Completo:</h6>");
        html.append("<ol>");
        html.append("<li><strong>getObject()</strong>: Obtém o objeto do Model (pode carregar se necessário)</li>");
        html.append("<li><strong>attach()</strong>: Mantém objeto em memória durante processamento (automático)</li>");
        html.append("<li><strong>Processamento</strong>: Componentes usam o objeto para renderizar</li>");
        html.append("<li><strong>detach()</strong>: Libera objeto da memória após uso (chamado automaticamente)</li>");
        html.append("</ol>");

        html.append("<h6>Por que Usar Detachable Models?</h6>");
        html.append("<ul>");
        html.append("<li><strong>Economia de memória</strong>: Libera objetos grandes após o uso</li>");
        html.append(
                "<li><strong>Evita serialização</strong>: Objetos não precisam ser serializados entre requisições</li>");
        html.append("<li><strong>Performance</strong>: Reduz o tamanho das páginas serializadas</li>");
        html.append(
                "<li><strong>Atualização</strong>: Objetos são recarregados a cada requisição (sempre atualizados)</li>");
        html.append("</ul>");

        html.append("<h6>Tipos de Detachable Models:</h6>");
        html.append("<ul>");
        html.append("<li><strong>LoadableDetachableModel</strong>: Carrega objeto sob demanda e libera após uso</li>");
        html.append("<li><strong>Model customizado</strong>: Implementação própria com detach() personalizado</li>");
        html.append("</ul>");

        html.append("<h6>Exemplo: LoadableDetachableModel</h6>");
        html.append("<pre><code>");
        html.append("public class CertidaoLoadableDetachableModel extends LoadableDetachableModel&lt;Certidao&gt; {\n");
        html.append("    private final Long certidaoId;\n");
        html.append("    private transient CertidaoRepository repository;\n");
        html.append("\n");
        html.append("    @Override\n");
        html.append("    protected Certidao load() {\n");
        html.append("        return repository.findById(certidaoId).orElseThrow(...);\n");
        html.append("    }\n");
        html.append("    // detach() é gerenciado automaticamente pelo Wicket\n");
        html.append("}\n");
        html.append("</code></pre>");

        html.append("<h6>Exemplo no Projeto:</h6>");
        html.append("<p><code>CertidaoDetailPage</code> usa <code>CertidaoLoadableDetachableModel</code>:</p>");
        html.append("<pre><code>");
        html.append("CertidaoLoadableDetachableModel model = new CertidaoLoadableDetachableModel(id);\n");
        html.append("add(new Label(\"numero\", new PropertyModel&lt;&gt;(model, \"numero\")));\n");
        html.append("// Objeto é carregado quando necessário\n");
        html.append("// Objeto é liberado após renderização\n");
        html.append("</code></pre>");

        html.append("<h6>Quando Implementar detach() Manualmente:</h6>");
        html.append(
                "<p>Geralmente você não precisa implementar <code>detach()</code> manualmente. Use <code>LoadableDetachableModel</code> que já faz isso.</p>");
        html.append("<p>Implemente <code>detach()</code> manualmente apenas se:</p>");
        html.append("<ul>");
        html.append("<li>Precisar liberar recursos específicos (conexões, streams, etc.)</li>");
        html.append("<li>Quiser limpar cache ou estado temporário</li>");
        html.append("<li>Estiver criando um Model customizado complexo</li>");
        html.append("</ul>");

        html.append("<h6>Boas Práticas:</h6>");
        html.append("<ul>");
        html.append("<li>Use <code>LoadableDetachableModel</code> para objetos de banco de dados</li>");
        html.append("<li>Armazene apenas IDs ou chaves primárias no Model</li>");
        html.append("<li>Marque dependências não serializáveis como <code>transient</code></li>");
        html.append("<li>Recrie dependências no método <code>load()</code></li>");
        html.append("</ul>");

        return html.toString();
    }

    public static String getConteudoSerializacao() {
        StringBuilder html = new StringBuilder();
        html.append("<h6>Serialização de Models</h6>");
        html.append(
                "<p>O Wicket serializa páginas e componentes entre requisições para manter o estado. Models devem ser serializáveis ou usar estratégias para evitar problemas.</p>");

        html.append("<h6>Por que o Wicket Serializa?</h6>");
        html.append("<ul>");
        html.append("<li><strong>Estado da página</strong>: Mantém valores de campos, seleções, etc.</li>");
        html.append("<li><strong>Navegação</strong>: Permite voltar/avançar no histórico do navegador</li>");
        html.append("<li><strong>Componentes</strong>: Mantém estado de componentes entre requisições</li>");
        html.append("<li><strong>Models</strong>: Preserva referências aos dados</li>");
        html.append("</ul>");

        html.append("<h6>O que é Serializável?</h6>");
        html.append(
                "<p>Um objeto é serializável se implementa <code>Serializable</code> e todos os seus campos também são serializáveis.</p>");
        html.append(
                "<p><strong>Serializáveis:</strong> String, Integer, Long, Date, enums, objetos que implementam Serializable</p>");
        html.append(
                "<p><strong>Não serializáveis:</strong> Connections, Streams, Threads, Repositories (geralmente)</p>");

        html.append("<h6>Problema Comum:</h6>");
        html.append("<pre><code>");
        html.append("// ❌ ERRO: Repository não é serializável\n");
        html.append("public class CertidaoDetailPage extends BasePage {\n");
        html.append("    private CertidaoRepository repository;  // Não serializável!\n");
        html.append("    // Wicket tentará serializar e falhará\n");
        html.append("}\n");
        html.append("</code></pre>");
        html.append(
                "<p><strong>Erro resultante:</strong> <code>NotSerializableException</code> ao tentar serializar a página</p>");

        html.append("<h6>Solução 1: Usar transient</h6>");
        html.append("<pre><code>");
        html.append("// ✅ CORRETO: Marcar como transient\n");
        html.append("public class CertidaoDetailPage extends BasePage {\n");
        html.append("    private transient CertidaoRepository repository;\n");
        html.append("\n");
        html.append("    private CertidaoRepository getRepository() {\n");
        html.append("        if (repository == null) {\n");
        html.append("            repository = new InMemoryCertidaoRepository();\n");
        html.append("        }\n");
        html.append("        return repository;\n");
        html.append("    }\n");
        html.append("}\n");
        html.append("</code></pre>");
        html.append(
                "<p><strong>Como funciona:</strong> Campo <code>transient</code> não é serializado. Recriado quando necessário.</p>");

        html.append("<h6>Solução 2: LoadableDetachableModel</h6>");
        html.append("<pre><code>");
        html.append("// ✅ CORRETO: Armazenar apenas ID (serializável)\n");
        html.append("public class CertidaoLoadableDetachableModel extends LoadableDetachableModel&lt;Certidao&gt; {\n");
        html.append("    private final Long certidaoId;  // Serializável!\n");
        html.append("    private transient CertidaoRepository repository;  // Não serializado\n");
        html.append("\n");
        html.append("    @Override\n");
        html.append("    protected Certidao load() {\n");
        html.append("        return repository.findById(certidaoId).orElseThrow(...);\n");
        html.append("    }\n");
        html.append("}\n");
        html.append("</code></pre>");
        html.append("<p><strong>Vantagem:</strong> Apenas o ID é serializado, não o objeto completo.</p>");

        html.append("<h6>Exemplos no Projeto:</h6>");
        html.append(
                "<p><strong>CertidaoDetailPage:</strong> Usa <code>transient</code> para repositório e <code>LoadableDetachableModel</code> para certidão</p>");
        html.append("<p><strong>CertidaoListPage:</strong> Usa <code>transient</code> para repositório</p>");
        html.append("<p><strong>CertidaoFormPage:</strong> Usa <code>transient</code> para repositório</p>");

        html.append("<h6>Problemas Comuns e Soluções:</h6>");
        html.append("<table class=\"table table-bordered\">");
        html.append("<thead>");
        html.append("<tr>");
        html.append("<th>Problema</th>");
        html.append("<th>Solução</th>");
        html.append("</tr>");
        html.append("</thead>");
        html.append("<tbody>");
        html.append("<tr>");
        html.append("<td>Repository não serializável</td>");
        html.append("<td>Marcar como <code>transient</code> e recriar quando necessário</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td>Objeto grande em Model</td>");
        html.append("<td>Usar <code>LoadableDetachableModel</code> e armazenar apenas ID</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td>Conexão de banco</td>");
        html.append("<td>Nunca armazenar em páginas. Usar repositório com <code>transient</code></td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td>Thread ou ExecutorService</td>");
        html.append("<td>Marcar como <code>transient</code> ou não armazenar</td>");
        html.append("</tr>");
        html.append("</tbody>");
        html.append("</table>");

        html.append("<h6>Boas Práticas:</h6>");
        html.append("<ul>");
        html.append("<li>Marque dependências não serializáveis como <code>transient</code></li>");
        html.append("<li>Use <code>LoadableDetachableModel</code> para objetos pesados</li>");
        html.append("<li>Armazene apenas IDs ou valores primitivos em Models</li>");
        html.append("<li>Recrie dependências <code>transient</code> quando necessário (lazy initialization)</li>");
        html.append("<li>Evite armazenar objetos grandes diretamente em páginas</li>");
        html.append("<li>Teste serialização em modo de desenvolvimento</li>");
        html.append("</ul>");

        html.append("<h6>Como Testar Serialização:</h6>");
        html.append(
                "<p>O Wicket detecta problemas de serialização em modo de desenvolvimento. Se houver erro, você verá:</p>");
        html.append("<pre><code>");
        html.append("java.io.NotSerializableException: com.hvivox.certidoes.infra.CertidaoRepository\n");
        html.append("</code></pre>");
        html.append("<p><strong>Solução:</strong> Marque o campo como <code>transient</code>.</p>");

        return html.toString();
    }

    public static String getConteudoEstaticoDinamico() {
        StringBuilder html = new StringBuilder();
        html.append("<h6>Estático vs Dinâmico</h6>");
        html.append(
                "<p>Models podem ser <strong>estáticos</strong> (valor capturado uma vez) ou <strong>dinâmicos</strong> (valor acessado a cada uso).</p>");

        html.append("<h6>Diferença Conceitual:</h6>");
        html.append(
                "<p><strong>Model Estático:</strong> Captura o valor no momento da criação e mantém esse valor fixo.</p>");
        html.append(
                "<p><strong>Model Dinâmico:</strong> Acessa o valor dinamicamente a cada vez que é solicitado.</p>");

        html.append("<h6>Model.of() - Estático:</h6>");
        html.append("<pre><code>");
        html.append("Certidao certidao = new Certidao();\n");
        html.append("certidao.setNumero(\"001/2024\");\n");
        html.append("\n");
        html.append("// Criar Model estático\n");
        html.append("IModel&lt;String&gt; modelEstatico = Model.of(certidao.getNumero());\n");
        html.append("add(new Label(\"numero\", modelEstatico));\n");
        html.append("\n");
        html.append("// Se certidao.setNumero(\"002/2024\") for chamado depois,\n");
        html.append("// o Label ainda mostrará \"001/2024\" (valor capturado)\n");
        html.append("</code></pre>");
        html.append("<p><strong>Características:</strong></p>");
        html.append("<ul>");
        html.append("<li>Valor capturado no momento da criação</li>");
        html.append("<li>Não atualiza se o objeto original mudar</li>");
        html.append("<li>Serializa o valor completo</li>");
        html.append("<li>Mais leve (não mantém referência ao objeto)</li>");
        html.append("</ul>");

        html.append("<h6>PropertyModel - Dinâmico:</h6>");
        html.append("<pre><code>");
        html.append("Certidao certidao = new Certidao();\n");
        html.append("certidao.setNumero(\"001/2024\");\n");
        html.append("\n");
        html.append("// Criar Model dinâmico\n");
        html.append("IModel&lt;String&gt; modelDinamico = new PropertyModel&lt;&gt;(certidao, \"numero\");\n");
        html.append("add(new Label(\"numero\", modelDinamico));\n");
        html.append("\n");
        html.append("// Se certidao.setNumero(\"002/2024\") for chamado depois,\n");
        html.append("// o Label mostrará \"002/2024\" (valor atualizado)\n");
        html.append("</code></pre>");
        html.append("<p><strong>Características:</strong></p>");
        html.append("<ul>");
        html.append("<li>Valor acessado dinamicamente a cada uso</li>");
        html.append("<li>Atualiza automaticamente se o objeto mudar</li>");
        html.append("<li>Mantém referência ao objeto</li>");
        html.append("<li>Serializa a referência ao objeto (se o objeto for serializável)</li>");
        html.append("</ul>");

        html.append("<h6>Comparação Prática:</h6>");
        html.append("<table class=\"table table-bordered\">");
        html.append("<thead>");
        html.append("<tr>");
        html.append("<th>Aspecto</th>");
        html.append("<th>Model.of() - Estático</th>");
        html.append("<th>PropertyModel - Dinâmico</th>");
        html.append("</tr>");
        html.append("</thead>");
        html.append("<tbody>");
        html.append("<tr>");
        html.append("<td><strong>Quando atualiza</strong></td>");
        html.append("<td>Nunca (valor fixo)</td>");
        html.append("<td>Sempre (acessa dinamicamente)</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td><strong>Serialização</strong></td>");
        html.append("<td>Valor completo</td>");
        html.append("<td>Referência ao objeto</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td><strong>Performance</strong></td>");
        html.append("<td>Mais rápido (valor já capturado)</td>");
        html.append("<td>Um pouco mais lento (acessa propriedade)</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td><strong>Uso de memória</strong></td>");
        html.append("<td>Menor (apenas o valor)</td>");
        html.append("<td>Maior (referência ao objeto)</td>");
        html.append("</tr>");
        html.append("</tbody>");
        html.append("</table>");

        html.append("<h6>Quando Usar Cada Um:</h6>");
        html.append("<h6>✅ Use Model.of() (Estático) quando:</h6>");
        html.append("<ul>");
        html.append("<li>Valor é imutável ou não muda</li>");
        html.append("<li>Valor é calculado uma vez (ex: data formatada, soma, etc.)</li>");
        html.append("<li>Valor é uma constante ou mensagem fixa</li>");
        html.append("<li>Você quer garantir que o valor não mude</li>");
        html.append("<li>Performance é crítica e o valor não muda</li>");
        html.append("</ul>");

        html.append("<h6>✅ Use PropertyModel (Dinâmico) quando:</h6>");
        html.append("<ul>");
        html.append("<li>Propriedade do objeto pode mudar</li>");
        html.append("<li>Você quer que o componente atualize automaticamente</li>");
        html.append("<li>Objeto é editável e valores podem ser modificados</li>");
        html.append("<li>Você está usando em formulários</li>");
        html.append("<li>Valor vem de um objeto que pode ser atualizado</li>");
        html.append("</ul>");

        html.append("<h6>Exemplo Prático no Projeto:</h6>");
        html.append("<p><strong>CertidaoDetailPage:</strong> Usa <code>PropertyModel</code> porque:</p>");
        html.append("<ul>");
        html.append("<li>Certidão pode ser atualizada no banco entre requisições</li>");
        html.append("<li>Usa <code>LoadableDetachableModel</code> que recarrega a cada requisição</li>");
        html.append("<li>PropertyModel garante que sempre mostra o valor atual</li>");
        html.append("</ul>");
        html.append("<pre><code>");
        html.append("CertidaoLoadableDetachableModel model = new CertidaoLoadableDetachableModel(id);\n");
        html.append("// PropertyModel acessa dinamicamente - sempre atualizado\n");
        html.append("add(new Label(\"numero\", new PropertyModel&lt;&gt;(model, \"numero\")));\n");
        html.append("</code></pre>");

        html.append("<h6>Impacto na Performance:</h6>");
        html.append("<p><strong>Model.of():</strong> Valor já está disponível, acesso direto (O(1))</p>");
        html.append(
                "<p><strong>PropertyModel:</strong> Acessa propriedade via reflection, pequeno overhead (ainda muito rápido)</p>");
        html.append(
                "<p><strong>Conclusão:</strong> A diferença de performance é mínima. Escolha baseado na necessidade de atualização.</p>");

        return html.toString();
    }

    public static String getConteudoUsandoModel() {
        StringBuilder html = new StringBuilder();
        html.append("<h6>Usando Model (Conceitos Avançados)</h6>");
        html.append(
                "<p>Além dos padrões básicos, Models podem ser usados de forma avançada: propriedades aninhadas, chaining, composição e Models customizados.</p>");

        html.append("<h6>1. Propriedades Aninhadas</h6>");
        html.append(
                "<p><code>PropertyModel</code> suporta acesso a propriedades aninhadas usando notação de ponto.</p>");
        html.append("<pre><code>");
        html.append("// Acessar propriedade aninhada\n");
        html.append("add(new Label(\"tipoDescricao\", new PropertyModel&lt;&gt;(certidao, \"tipo.descricao\")));\n");
        html.append("// Equivale a: certidao.getTipo().getDescricao()\n");
        html.append("\n");
        html.append("// Acessar propriedade ainda mais aninhada\n");
        html.append("add(new Label(\"statusBadge\", new PropertyModel&lt;&gt;(certidao, \"status.badgeHtml\")));\n");
        html.append("// Equivale a: certidao.getStatus().getBadgeHtml()\n");
        html.append("</code></pre>");
        html.append("<p><strong>Exemplo no Projeto:</strong></p>");
        html.append("<p><code>CertidaoDetailPage</code> usa propriedades aninhadas:</p>");
        html.append("<pre><code>");
        html.append("certidaoContainer.add(new Label(\"tipo\", \n");
        html.append("    new PropertyModel&lt;&gt;(certidaoModel, \"tipo.descricao\")));\n");
        html.append("certidaoContainer.add(new Label(\"status\", \n");
        html.append("    new PropertyModel&lt;&gt;(certidaoModel, \"status.descricao\")));\n");
        html.append("</code></pre>");

        html.append("<h6>2. Chaining de Models</h6>");
        html.append("<p>Models podem ser encadeados, onde um Model fornece dados para outro Model.</p>");
        html.append("<pre><code>");
        html.append("// Model principal (certidão)\n");
        html.append("IModel&lt;Certidao&gt; certidaoModel = Model.of(certidao);\n");
        html.append("\n");
        html.append("// Model aninhado (tipo da certidão)\n");
        html.append("IModel&lt;CertidaoTipo&gt; tipoModel = new PropertyModel&lt;&gt;(certidaoModel, \"tipo\");\n");
        html.append("\n");
        html.append("// Usar o tipoModel em um componente\n");
        html.append("add(new Label(\"tipoDescricao\", new PropertyModel&lt;&gt;(tipoModel, \"descricao\")));\n");
        html.append("</code></pre>");
        html.append(
                "<p><strong>Quando usar:</strong> Quando você precisa reutilizar um Model intermediário em múltiplos lugares.</p>");

        html.append("<h6>3. Models Compostos</h6>");
        html.append("<p>Você pode combinar múltiplos Models para criar valores compostos.</p>");
        html.append("<pre><code>");
        html.append("// Criar Model composto (concatenação)\n");
        html.append("IModel&lt;String&gt; numeroModel = new PropertyModel&lt;&gt;(certidao, \"numero\");\n");
        html.append("IModel&lt;String&gt; tipoModel = new PropertyModel&lt;&gt;(certidao, \"tipo.descricao\");\n");
        html.append("\n");
        html.append("// Model customizado que combina valores\n");
        html.append("IModel&lt;String&gt; composto = new AbstractReadOnlyModel&lt;String&gt;() {\n");
        html.append("    @Override\n");
        html.append("    public String getObject() {\n");
        html.append("        return numeroModel.getObject() + \" - \" + tipoModel.getObject();\n");
        html.append("    }\n");
        html.append("};\n");
        html.append("add(new Label(\"numeroTipo\", composto));\n");
        html.append("</code></pre>");

        html.append("<h6>4. Models Customizados</h6>");
        html.append(
                "<p>Você pode criar Models customizados implementando <code>IModel&lt;T&gt;</code> ou estendendo classes base.</p>");
        html.append("<pre><code>");
        html.append("// Model customizado para formatação\n");
        html.append("public class DataFormatadaModel extends AbstractReadOnlyModel&lt;String&gt; {\n");
        html.append("    private final IModel&lt;String&gt; dataModel;\n");
        html.append("\n");
        html.append("    public DataFormatadaModel(IModel&lt;String&gt; dataModel) {\n");
        html.append("        this.dataModel = dataModel;\n");
        html.append("    }\n");
        html.append("\n");
        html.append("    @Override\n");
        html.append("    public String getObject() {\n");
        html.append("        String data = dataModel.getObject();\n");
        html.append("        // Formatar data (ex: dd/MM/yyyy → dd de MMMM de yyyy)\n");
        html.append("        return formatarData(data);\n");
        html.append("    }\n");
        html.append("}\n");
        html.append("</code></pre>");

        html.append("<h6>5. Usando Model com ListView</h6>");
        html.append("<p>ListView usa Models para cada item da lista, permitindo acesso dinâmico.</p>");
        html.append("<pre><code>");
        html.append("List&lt;Certidao&gt; certidoes = repository.findAll();\n");
        html.append("ListView&lt;Certidao&gt; listView = new ListView&lt;&gt;(\"certidoes\", certidoes) {\n");
        html.append("    @Override\n");
        html.append("    protected void populateItem(ListItem&lt;Certidao&gt; item) {\n");
        html.append("        // item.getModel() retorna IModel&lt;Certidao&gt; para este item\n");
        html.append("        item.add(new Label(\"numero\", \n");
        html.append("            new PropertyModel&lt;&gt;(item.getModel(), \"numero\")));\n");
        html.append("    }\n");
        html.append("};\n");
        html.append("</code></pre>");
        html.append(
                "<p><strong>Exemplo no Projeto:</strong> <code>CertidaoListPage</code> usa ListView com Models.</p>");

        html.append("<h6>6. Model com Wicket:enclosure</h6>");
        html.append("<p>Você pode usar Models para controlar visibilidade de componentes.</p>");
        html.append("<pre><code>");
        html.append("// HTML\n");
        html.append("&lt;div wicket:enclosure=\"certidao\"&gt;\n");
        html.append("    &lt;span wicket:id=\"numero\"&gt;&lt;/span&gt;\n");
        html.append("&lt;/div&gt;\n");
        html.append("\n");
        html.append("// Java\n");
        html.append("IModel&lt;Certidao&gt; certidaoModel = Model.of(certidao);\n");
        html.append("WebMarkupContainer enclosure = new WebMarkupContainer(\"certidao\", certidaoModel);\n");
        html.append("enclosure.add(new Label(\"numero\", new PropertyModel&lt;&gt;(certidaoModel, \"numero\")));\n");
        html.append("// O div só aparece se certidaoModel.getObject() != null\n");
        html.append("</code></pre>");

        html.append("<h6>7. Model com CompoundPropertyModel</h6>");
        html.append(
                "<p>CompoundPropertyModel permite bind automático usando o wicket:id como nome da propriedade.</p>");
        html.append("<pre><code>");
        html.append("// Definir Model composto na página\n");
        html.append("setDefaultModel(new CompoundPropertyModel&lt;&gt;(certidao));\n");
        html.append("\n");
        html.append("// Campos se vinculam automaticamente\n");
        html.append("add(new Label(\"numero\"));  // Vincula a certidao.numero\n");
        html.append("add(new Label(\"interessado\"));  // Vincula a certidao.interessado\n");
        html.append("</code></pre>");
        html.append(
                "<p><strong>Exemplo no Projeto:</strong> <code>CertidaoFormPage</code> usa CompoundPropertyModel no formulário.</p>");

        html.append("<h6>Boas Práticas para Uso Avançado:</h6>");
        html.append("<ul>");
        html.append("<li>Use propriedades aninhadas para acessar objetos relacionados</li>");
        html.append("<li>Evite encadeamento muito profundo (ex: \"pessoa.endereco.cidade.estado.nome\")</li>");
        html.append("<li>Trate valores null em propriedades aninhadas</li>");
        html.append("<li>Use Models customizados apenas quando necessário</li>");
        html.append("<li>Prefira Models padrão do Wicket quando possível</li>");
        html.append("</ul>");

        html.append("<h6>Exemplos no Projeto:</h6>");
        html.append("<ul>");
        html.append(
                "<li><strong>CertidaoDetailPage:</strong> Usa PropertyModel com propriedades aninhadas (tipo.descricao, status.descricao)</li>");
        html.append("<li><strong>CertidaoFormPage:</strong> Usa CompoundPropertyModel para bind automático</li>");
        html.append("<li><strong>CertidaoListPage:</strong> Usa ListView com Models para cada item</li>");
        html.append("<li><strong>CertidaoCard:</strong> Usa IModel&lt;Certidao&gt; com PropertyModel</li>");
        html.append("</ul>");

        return html.toString();
    }
}
