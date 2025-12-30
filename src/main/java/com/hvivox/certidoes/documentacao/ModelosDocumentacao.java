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
        html.append("<p>Os <strong>Models</strong> são a camada de abstração que conecta os dados aos componentes no Wicket.</p>");
        
        html.append("<h6>O que é um Model?</h6>");
        html.append("<p>Um <code>Model</code> é uma interface (<code>IModel&lt;T&gt;</code>) que fornece dados aos componentes Wicket.</p>");
        html.append("<p>Em vez de passar objetos diretamente aos componentes, passamos um <code>Model</code> que encapsula o objeto.</p>");
        
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
        html.append("<li><strong>Atualização automática</strong>: Componentes são atualizados quando o Model muda</li>");
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
        html.append("<li><a href=\"https://wicket.apache.org/learn/guide/models.html\" target=\"_blank\">Documentação Oficial: Models</a></li>");
        html.append("<li><a href=\"https://cwiki.apache.org/confluence/display/WICKET/Working+with+Wicket+models\" target=\"_blank\">Trabalhando com Models (Wiki)</a></li>");
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
        html.append("<p>Existem <strong>padrões e boas práticas</strong> para usar Models no Wicket de forma eficiente e segura.</p>");
        
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
        html.append("<p><strong>Quando usar:</strong> Em formulários onde os campos têm o mesmo nome das propriedades do objeto.</p>");
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
        html.append("<li><a href=\"https://wicket.apache.org/learn/guide/models.html\" target=\"_blank\">Documentação Oficial: Models</a></li>");
        html.append("<li><a href=\"https://cwiki.apache.org/confluence/display/WICKET/Working+with+Wicket+models\" target=\"_blank\">Trabalhando com Models (Wiki)</a></li>");
        html.append("</ul>");
        
        return html.toString();
    }
}

