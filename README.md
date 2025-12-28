# Projeto Certidões - Apache Wicket

Sistema de gerenciamento de certidões desenvolvido com Apache Wicket 7.18.0.

## Módulo 1: Primeiros Passos com Wicket

Este documento explica os conceitos fundamentais do Apache Wicket implementados neste projeto.

---

## 📚 Visão Geral do Wicket

Apache Wicket é um framework web orientado a componentes para Java. Diferente de frameworks baseados em ações (como Struts) ou MVC tradicional, o Wicket utiliza um modelo de programação orientado a componentes, similar ao desenvolvimento de aplicações desktop (Swing, JavaFX).

### Principais Características

- **Orientado a Componentes**: Desenvolve componentes reutilizáveis
- **Separação Clara**: HTML e Java ficam separados (sem código Java no HTML)
- **Stateful**: Mantém estado do componente entre requisições
- **Type-Safe**: Verificação de tipos em tempo de compilação
- **POJO**: Componentes são classes Java simples (POJOs)

---

## ✨ Vantagens de Usar Wicket

### 1. Separação de Responsabilidades
- **HTML puro**: Sem código Java misturado no HTML
- **Lógica Java**: Em classes separadas
- **Designers**: Podem trabalhar no HTML sem conhecer Java

### 2. Orientação a Componentes
- **Componentes reutilizáveis**: Crie uma vez, use em qualquer lugar
- **Hierarquia de componentes**: Composição de componentes
- **Herança de páginas**: BasePage para layout comum

### 3. Type-Safety
- **Erros detectados em tempo de compilação**: Não em runtime
- **Refatoração segura**: IDEs podem refatorar com segurança
- **Menos erros**: Erros de digitação são detectados antes

### 4. Estado Automático
- **Wicket gerencia estado**: Automaticamente
- **Serialização transparente**: Estado é serializado automaticamente
- **Sessão distribuída**: Suporta clustering

### 5. Testabilidade
- **Componentes são POJOs**: Fácil de testar
- **Testes unitários**: Simples de escrever
- **WicketTester**: Para testes de integração

### 6. Sem XML de Configuração
- **Configuração via código Java**: Mais flexível
- **Menos arquivos**: Menos configuração
- **Dinâmico**: Configuração pode ser dinâmica

---

## 🏗️ Estrutura Geral de uma Aplicação Wicket

### 1. Wicket Application (`WicketApplication.java`)

Classe principal que estende `WebApplication`:
- Configuração global da aplicação
- Define a página inicial (HomePage)
- Configurações de encoding, recursos, etc.

**Localização**: `src/main/java/com/hvivox/certidoes/WicketApplication.java`

### 2. Páginas (Pages)

Classes que estendem `WebPage`:
- Uma página = uma classe Java + um arquivo HTML
- Exemplo: `HomePage.java` + `HomePage.html`
- Usam `<wicket:extend>` para herdar layout da BasePage

**Exemplos no projeto**:
- `HomePage.java` - Página inicial
- `CertidaoListPage.java` - Lista de certidões
- `CertidaoFormPage.java` - Formulário de certidão
- `CertidaoDetailPage.java` - Detalhes da certidão
- `ComponentesDemoPage.java` - Demonstração de componentes

### 3. Componentes (Components)

Blocos de construção reutilizáveis:

#### Componentes Básicos

| Componente | Descrição | Exemplo |
|------------|---------|---------|
| `Label` | Exibe texto estático ou dinâmico | `new Label("id", "Texto")` |
| `TextField` | Campo de texto (input type="text") | `new TextField<>("id", model)` |
| `TextArea` | Área de texto multilinha | `new TextArea<>("id", model)` |
| `CheckBox` | Caixa de seleção | `new CheckBox("id", model)` |
| `DropDownChoice` | Lista suspensa (select) | `new DropDownChoice<>("id", model, lista)` |
| `Button` | Botão de submissão | `new Button("id")` |
| `Link` | Link com ação customizada | `new Link<>("id") { onClick() {...} }` |
| `BookmarkablePageLink` | Link para página (URL amigável) | `new BookmarkablePageLink<>("id", Page.class)` |
| `Form` | Formulário HTML | `new Form<>("id")` |
| `ListView` | Repete componentes para cada item | `new ListView<>("id", lista)` |
| `WebMarkupContainer` | Container genérico | `new WebMarkupContainer("id")` |
| `FeedbackPanel` | Exibe mensagens de feedback | `new FeedbackPanel("id")` |

**Ver demonstração completa**: Acesse `/componentes-demo` ou clique em "Componentes Wicket" no menu.

### 4. Modelos (Models)

Conectam dados aos componentes:
- `PropertyModel`: Acessa propriedades de objetos
- `CompoundPropertyModel`: Bind automático de propriedades
- `LoadableDetachableModel`: Carrega dados sob demanda
- `Model`: Modelo simples para valores

**Exemplo**:
```java
TextField<String> campo = new TextField<>("nome", 
    new PropertyModel<>(pessoa, "nome"));
```

### 5. Web.xml

Configuração do servlet container:
- Define o `WicketFilter`
- Mapeia todas as requisições para o Wicket
- Configura a classe Application

**Localização**: `src/main/webapp/WEB-INF/web.xml`

### 6. Recursos (Resources)

CSS, JavaScript, imagens:
- Podem ser empacotados com componentes
- Versionamento automático
- Carregamento otimizado

**Localização**: `src/main/webapp/`

---

## 🔄 Fluxo de uma Requisição Wicket

1. **Cliente faz requisição HTTP**
2. **WicketFilter intercepta** a requisição
3. **Wicket identifica** a página/componente alvo
4. **Wicket restaura** o estado da página (se necessário)
5. **Processa a requisição** (eventos, validações, etc.)
6. **Renderiza** a resposta HTML
7. **Envia HTML** ao cliente

---

## 📁 Estrutura de Diretórios do Projeto

```
certidoes/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/hvivox/certidoes/
│   │   │       ├── WicketApplication.java    # Classe principal
│   │   │       ├── BasePage.java             # Página base
│   │   │       ├── HomePage.java              # Página inicial
│   │   │       ├── domain/                    # Entidades do domínio
│   │   │       │   ├── Certidao.java
│   │   │       │   ├── CertidaoTipo.java
│   │   │       │   └── CertidaoStatus.java
│   │   │       ├── infra/                     # Infraestrutura
│   │   │       │   ├── CertidaoRepository.java
│   │   │       │   └── InMemoryCertidaoRepository.java
│   │   │       └── page/                      # Páginas
│   │   │           ├── CertidaoListPage.java
│   │   │           ├── CertidaoFormPage.java
│   │   │           ├── CertidaoDetailPage.java
│   │   │           └── ComponentesDemoPage.java
│   │   ├── resources/                        # Recursos (log4j, etc.)
│   │   └── webapp/                           # Recursos web (CSS, JS, HTML)
│   │       ├── style.css
│   │       └── WEB-INF/
│   │           └── web.xml
│   └── test/                                 # Testes
└── pom.xml                                   # Configuração Maven
```

---

## 🎯 Como Usar Este Projeto

### Executar a Aplicação

```bash
mvn jetty:run
```

Acesse: `http://localhost:8080`

### Páginas Disponíveis

- **Home**: `/` - Página inicial
- **Listar Certidões**: `/certidoes` - Lista todas as certidões
- **Nova Certidão**: `/certidoes/nova` - Formulário para criar certidão
- **Editar Certidão**: `/certidoes/editar?id=1` - Formulário para editar
- **Detalhes**: `/certidoes/detalhes?id=1` - Detalhes da certidão
- **Componentes Demo**: `/componentes-demo` - Demonstração de componentes

### Menu de Navegação

Todas as páginas têm um menu no topo com links para:
- Home
- Listar Certidões
- Nova Certidão
- Componentes Wicket (demonstração)

---

## 📖 Conceitos Implementados no Módulo 1

### ✅ Visão Geral do Wicket
- Documentação completa em `WicketApplication.java`
- Explicação das características principais
- Comparação com outros frameworks

### ✅ Vantagens de Usar Wicket
- Documentação detalhada em `WicketApplication.java`
- Exemplos práticos de cada vantagem
- Benefícios para desenvolvimento

### ✅ Componentes do Wicket
- Página de demonstração: `ComponentesDemoPage.java`
- Exemplos de todos os componentes principais
- Código comentado e explicado

### ✅ Estrutura Geral de uma Aplicação Wicket
- Documentação em `WicketApplication.java`
- Documentação em `BasePage.java`
- Estrutura de diretórios explicada
- Fluxo de requisição documentado

---

## 🔍 Próximos Módulos

- **Módulo 2**: Arquitetura do Wicket
- **Módulo 3**: Modelos (Models)
- **Módulo 4**: Formulários (Forms)
- **Módulo 5**: Composição de páginas e componentes
- **Módulo 6**: Ajax e componentes ricos
- **Módulo 7**: Extras (Autenticação, I18N, Testes, etc.)

---

## 📝 Notas Importantes

### Separação HTML/Java

No Wicket, o HTML e o Java ficam separados:

**Java** (`MinhaPage.java`):
```java
add(new Label("titulo", "Meu Título"));
```

**HTML** (`MinhaPage.html`):
```html
<h1 wicket:id="titulo">Título</h1>
```

O `wicket:id` conecta o componente Java ao elemento HTML.

### Herança de Layout

Todas as páginas estendem `BasePage` e usam `<wicket:extend>`:

**HTML**:
```html
<wicket:extend>
    <h1 wicket:id="titulo">Título</h1>
</wicket:extend>
```

Isso herda o layout da `BasePage.html`.

### Mensagens de Feedback

Use o `FeedbackPanel` (já na BasePage) para exibir mensagens:

```java
getSession().info("Mensagem informativa");
getSession().error("Mensagem de erro");
getSession().success("Mensagem de sucesso");
getSession().warn("Mensagem de aviso");
```

---

## 🛠️ Tecnologias Utilizadas

- **Apache Wicket**: 7.18.0
- **Java**: 1.8
- **Maven**: Gerenciamento de dependências
- **Jetty**: Servidor de desenvolvimento
- **Bootstrap**: 4.6.2 (para UI)

---

## 📚 Referências

- [Documentação Oficial do Wicket](https://wicket.apache.org/)
- [Guia de Início Rápido](https://wicket.apache.org/learn/guide/)
- [API Reference](https://wicket.apache.org/apidocs/7.x/)

---

## 👨‍💻 Desenvolvimento

Este projeto foi desenvolvido como material didático para aprendizado do Apache Wicket, implementando os conceitos do Módulo 1: Primeiros Passos com Wicket.

