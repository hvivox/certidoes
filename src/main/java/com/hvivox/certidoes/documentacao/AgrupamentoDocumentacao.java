package com.hvivox.certidoes.documentacao;

/**
 * ====================================================================
 * DOCUMENTAÇÃO: AGRUPAMENTO DE COMPONENTES - MÓDULO 5
 * ====================================================================
 * 
 * Esta classe contém a documentação sobre Agrupamento de Componentes no Wicket.
 * 
 * O agrupamento de componentes é uma técnica fundamental para organizar
 * a estrutura da aplicação e melhorar a manutenibilidade do código.
 */
public class AgrupamentoDocumentacao {

    /**
     * Retorna o conteúdo HTML formatado da documentação sobre Agrupamento.
     * 
     * @return HTML formatado com a documentação
     */
    public static String getConteudo() {
        StringBuilder html = new StringBuilder();

        html.append("<h6>Agrupamento de Componentes no Wicket</h6>");
        html.append("<p>O <strong>agrupamento de componentes</strong> é uma técnica essencial para organizar ");
        html.append("a estrutura hierárquica da sua aplicação Wicket de forma lógica e manutenível.</p>");

        html.append("<h6>Por que Agrupar Componentes?</h6>");
        html.append("<ul>");
        html.append("<li><strong>Organização:</strong> Mantém o código limpo e estruturado</li>");
        html.append("<li><strong>Reutilização:</strong> Facilita a criação de componentes reutilizáveis</li>");
        html.append("<li><strong>Manutenibilidade:</strong> Torna o código mais fácil de manter e evoluir</li>");
        html.append("<li><strong>Visibilidade:</strong> Permite mostrar/ocultar grupos inteiros de componentes</li>");
        html.append("<li><strong>Encapsulamento:</strong> Agrupa funcionalidades relacionadas</li>");
        html.append("</ul>");

        html.append("<h6>Técnicas de Agrupamento:</h6>");

        // 1. WebMarkupContainer
        html.append("<div class='alert alert-info mt-3'>");
        html.append("<h6 class='mb-2'><i class='fas fa-box'></i> 1. WebMarkupContainer</h6>");
        html.append("<p>Container genérico para agrupar componentes relacionados.</p>");
        html.append("<pre><code>");
        html.append("// Criar container\n");
        html.append("WebMarkupContainer formContainer = new WebMarkupContainer(\"formContainer\");\n\n");
        html.append("// Adicionar componentes ao container\n");
        html.append("formContainer.add(new TextField(\"nome\"));\n");
        html.append("formContainer.add(new TextField(\"email\"));\n\n");
        html.append("// Adicionar container à página\n");
        html.append("add(formContainer);\n\n");
        html.append("// Controlar visibilidade do grupo\n");
        html.append("formContainer.setVisible(condicao);\n");
        html.append("</code></pre>");
        html.append("</div>");

        // 2. Form
        html.append("<div class='alert alert-success mt-3'>");
        html.append("<h6 class='mb-2'><i class='fas fa-file-alt'></i> 2. Form (Agrupamento de Campos)</h6>");
        html.append("<p>Formulários agrupam campos relacionados para submissão.</p>");
        html.append("<pre><code>");
        html.append("// Criar formulário\n");
        html.append("Form&lt;Void&gt; form = new Form&lt;&gt;(\"form\") {\n");
        html.append("    @Override\n");
        html.append("    protected void onSubmit() {\n");
        html.append("        // Processar dados\n");
        html.append("    }\n");
        html.append("};\n\n");
        html.append("// Agrupar campos no formulário\n");
        html.append("form.add(new TextField(\"nome\"));\n");
        html.append("form.add(new TextField(\"email\"));\n");
        html.append("form.add(new Button(\"submit\"));\n");
        html.append("</code></pre>");
        html.append("</div>");

        // 3. Panel
        html.append("<div class='alert alert-warning mt-3'>");
        html.append("<h6 class='mb-2'><i class='fas fa-layer-group'></i> 3. Panel (Componente Reutilizável)</h6>");
        html.append("<p>Panels encapsulam conjuntos de componentes em uma unidade reutilizável.</p>");
        html.append("<pre><code>");
        html.append("// Criar Panel customizado\n");
        html.append("public class EnderecoPanel extends Panel {\n");
        html.append("    public EnderecoPanel(String id, IModel&lt;Endereco&gt; model) {\n");
        html.append("        super(id, model);\n");
        html.append("        add(new TextField(\"rua\"));\n");
        html.append("        add(new TextField(\"cidade\"));\n");
        html.append("        add(new TextField(\"cep\"));\n");
        html.append("    }\n");
        html.append("}\n\n");
        html.append("// Usar em múltiplas páginas\n");
        html.append("add(new EnderecoPanel(\"enderecoResidencial\", model));\n");
        html.append("add(new EnderecoPanel(\"enderecoComercial\", model));\n");
        html.append("</code></pre>");
        html.append("</div>");

        // 4. ListView
        html.append("<div class='alert alert-primary mt-3'>");
        html.append("<h6 class='mb-2'><i class='fas fa-list'></i> 4. ListView (Agrupamento Repetitivo)</h6>");
        html.append("<p>ListView agrupa componentes repetidos para cada item de uma lista.</p>");
        html.append("<pre><code>");
        html.append("// Criar lista de componentes\n");
        html.append("ListView&lt;Certidao&gt; listView = new ListView&lt;&gt;(\"certidoes\", certidoesList) {\n");
        html.append("    @Override\n");
        html.append("    protected void populateItem(ListItem&lt;Certidao&gt; item) {\n");
        html.append("        // Agrupar componentes para cada item\n");
        html.append("        item.add(new Label(\"numero\"));\n");
        html.append("        item.add(new Label(\"tipo\"));\n");
        html.append("        item.add(new Label(\"status\"));\n");
        html.append("    }\n");
        html.append("};\n");
        html.append("</code></pre>");
        html.append("</div>");

        // 5. Fragment
        html.append("<div class='alert alert-secondary mt-3'>");
        html.append("<h6 class='mb-2'><i class='fas fa-puzzle-piece'></i> 5. Fragment (Agrupamento Condicional)</h6>");
        html.append("<p>Fragments permitem agrupar diferentes conjuntos de componentes condicionalmente.</p>");
        html.append("<pre><code>");
        html.append("// Definir fragments no HTML\n");
        html.append("&lt;wicket:fragment wicket:id=\"viewMode\"&gt;\n");
        html.append("    &lt;span wicket:id=\"valor\"&gt;&lt;/span&gt;\n");
        html.append("&lt;/wicket:fragment&gt;\n\n");
        html.append("&lt;wicket:fragment wicket:id=\"editMode\"&gt;\n");
        html.append("    &lt;input wicket:id=\"campo\" /&gt;\n");
        html.append("&lt;/wicket:fragment&gt;\n\n");
        html.append("// Usar no Java\n");
        html.append("if (isEditMode) {\n");
        html.append("    add(new Fragment(\"content\", \"editMode\", this));\n");
        html.append("} else {\n");
        html.append("    add(new Fragment(\"content\", \"viewMode\", this));\n");
        html.append("}\n");
        html.append("</code></pre>");
        html.append("</div>");

        html.append("<h6>Hierarquia de Componentes:</h6>");
        html.append("<p>Os componentes formam uma árvore hierárquica:</p>");
        html.append("<pre><code>");
        html.append("Page\n");
        html.append("  └─ Form\n");
        html.append("      ├─ WebMarkupContainer (\"dadosPessoais\")\n");
        html.append("      │   ├─ TextField (\"nome\")\n");
        html.append("      │   ├─ TextField (\"email\")\n");
        html.append("      │   └─ TextField (\"telefone\")\n");
        html.append("      ├─ WebMarkupContainer (\"endereco\")\n");
        html.append("      │   ├─ TextField (\"rua\")\n");
        html.append("      │   ├─ TextField (\"cidade\")\n");
        html.append("      │   └─ TextField (\"cep\")\n");
        html.append("      └─ Button (\"submit\")\n");
        html.append("</code></pre>");

        html.append("<h6>Boas Práticas de Agrupamento:</h6>");
        html.append("<ul>");
        html.append("<li><strong>Agrupe por Funcionalidade:</strong> Componentes relacionados devem estar juntos</li>");
        html.append("<li><strong>Use Nomes Descritivos:</strong> IDs dos containers devem indicar seu propósito</li>");
        html.append("<li><strong>Evite Aninhamento Excessivo:</strong> Máximo de 3-4 níveis de profundidade</li>");
        html.append("<li><strong>Reutilize com Panels:</strong> Crie Panels para grupos que se repetem</li>");
        html.append(
                "<li><strong>Controle Visibilidade:</strong> Use setVisible() em containers para mostrar/ocultar grupos</li>");
        html.append("<li><strong>setOutputMarkupId(true):</strong> Necessário para Ajax e visibilidade dinâmica</li>");
        html.append("</ul>");

        html.append("<h6>Controle de Visibilidade de Grupos:</h6>");
        html.append("<pre><code>");
        html.append("// Mostrar/ocultar grupo inteiro\n");
        html.append("WebMarkupContainer secaoAvancada = new WebMarkupContainer(\"secaoAvancada\");\n");
        html.append("secaoAvancada.setVisible(usuario.isAdmin());\n\n");
        html.append("// Necessário para Ajax\n");
        html.append("secaoAvancada.setOutputMarkupId(true);\n");
        html.append("secaoAvancada.setOutputMarkupPlaceholderTag(true);\n\n");
        html.append("// Alternar visibilidade com Ajax\n");
        html.append("AjaxLink link = new AjaxLink(\"toggle\") {\n");
        html.append("    @Override\n");
        html.append("    public void onClick(AjaxRequestTarget target) {\n");
        html.append("        secaoAvancada.setVisible(!secaoAvancada.isVisible());\n");
        html.append("        target.add(secaoAvancada);\n");
        html.append("    }\n");
        html.append("};\n");
        html.append("</code></pre>");

        html.append("<h6>Exemplos no Projeto:</h6>");
        html.append("<ul>");
        html.append("<li><strong>CertidaoFormPage:</strong> Agrupamento de campos em formulário</li>");
        html.append("<li><strong>CertidaoListPage:</strong> ListView agrupando dados de certidões</li>");
        html.append("<li><strong>CertidaoCard:</strong> Panel agrupando informações de uma certidão</li>");
        html.append("<li><strong>ComponentesDemoPage:</strong> Múltiplos WebMarkupContainers organizando seções</li>");
        html.append("</ul>");

        html.append("<h6>Links Relacionados:</h6>");
        html.append("<ul>");
        html.append(
                "<li><a href='https://wicket.apache.org/learn/guide/componentQueueing.html' target='_blank'>Component Queueing</a></li>");
        html.append(
                "<li><a href='https://wicket.apache.org/learn/guide/componentLifecycle.html' target='_blank'>Component Lifecycle</a></li>");
        html.append(
                "<li><a href='https://cwiki.apache.org/confluence/display/WICKET/Best+Practices' target='_blank'>Best Practices (Wiki)</a></li>");
        html.append("</ul>");

        return html.toString();
    }
}
