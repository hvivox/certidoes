# Prompt para Continuar o Desenvolvimento - Projeto Certidões Wicket

## CONTEXTO DO PROJETO

Você é um desenvolvedor Java sênior especialista em Apache Wicket 7-9+. Estou trabalhando em um projeto Wicket chamado "certidoes" (sistema de gerenciamento de certidões) e preciso implementar os conceitos que estão faltando.

**Projeto**: certidoes (sistema de certidões)
**Versão Wicket**: 7.18.0
**Estrutura**: Maven
**Java**: 1.8

## OBJETIVO

Implementar os conceitos faltantes do conteúdo programático, módulo por módulo, de forma didática e prática.

## ESTRUTURA DO TRABALHO

- Trabalhar módulo por módulo (Módulo 1, depois 2, depois 3, etc.)
- Para cada módulo, primeiro listar o que está faltando
- Depois implementar cada item faltante, um por vez
- Sempre mostrar o código completo e explicar o que foi feito
- **Implementar um item por vez e ir avançando**

## REGRAS IMPORTANTES

1. **Código didático**: Deixar bem didático para consulta posterior
2. **Exemplos reais**: Quando possível, deixar os exemplos mais próximos de exemplos reais
3. **Foco no projeto**: Se possível, manter os exemplos voltados para a realidade do projeto (certidões)
4. **Simplicidade**: Evitar complexidade desnecessária - código simples e direto
5. **Um item por vez**: Implementar um item, testar, depois partir para o próximo
6. **Português**: Sempre usar português para comentários e mensagens
7. **Padrão existente**: Manter o padrão de código já estabelecido no projeto

## REGRAS DO PROJETO (IA segura)
- Não refatorar “por estética”.
- Não mudar lógica sem justificativa explícita.
- Mudanças devem ser mínimas e locais (um arquivo/um objetivo por vez).
- Sempre mostrar o diff e explicar o impacto.
- Após o termino de cada etapa da implementação, faça os testes necessário antes de continuar para a proxima etapa
- Após mudanças: rodar lint + build (e testes se existirem).

## Proteção de regra de negócio (zero “ajuste” silencioso)
- Proibido mudar regras de negócio, validações, cálculos, fluxos de status, integrações, descontos, arredondamentos, datas e condições sem solicitação explícita.
- Se você suspeitar que existe bug de regra de negócio, não corrija automaticamente:
  - aponte o risco,
  - mostre o trecho,
  - proponha a correção,
  - e pergunte se pode aplicar.
- Se o pedido for “refatorar/limpar”, a regra de negócio deve permanecer 100% equivalente (mesmas entradas → mesmas saídas).

## Dúvida = parar e perguntar (Se qualquer ponto for ambíguo):
- faça perguntas objetivas antes de implementar, especialmente sobre:
  - regra de negócio,
  - arredondamento/BigDecimal,
  - datas/fuso,
  - validações,
  - integrações externas.


## ESTADO ATUAL DO PROJETO

### MÓDULO 1: Primeiros passos com Wicket ✅ COMPLETO
- ✅ Visão geral do Wicket - Documentado
- ✅ Vantagens de usar Wicket - Documentado
- ✅ Componentes do Wicket - Página de demonstração criada (ComponentesDemoPage)
- ✅ Estrutura geral de uma aplicação Wicket - Documentado

**Arquivos criados**:
- `src/main/java/com/hvivox/certidoes/page/ComponentesDemoPage.java` - Demonstração de componentes
- `src/main/java/com/hvivox/certidoes/page/ComponentesDemoPage.html`
- `README.md` - Documentação completa

### MÓDULO 2: Arquitetura do Wicket 🟡 EM ANDAMENTO

**ITENS IMPLEMENTADOS**:

#### ✅ Item 1: Session Customizada (COMPLETO)
- **Arquivo**: `src/main/java/com/hvivox/certidoes/session/CertidoesSession.java`
- **Funcionalidade**: Session customizada com contador de certidões excluídas
- **Configuração**: `WicketApplication.newSession()` retorna `CertidoesSession`
- **Uso**: Incrementa contador em `CertidaoListPage` ao excluir certidão
- **Dashboard**: `HomePage` exibe o contador de certidões excluídas
- **Status**: ✅ Funcionando

#### ✅ Item 2: RequestCycleListener (COMPLETO)
- **Arquivo**: `src/main/java/com/hvivox/certidoes/listener/CertidoesRequestCycleListener.java`
- **Funcionalidade**: Intercepta ciclo de requisição para logging
- **Configuração**: Registrado em `WicketApplication.init()`
- **Flag de controle**: `HABILITAR_MONITORAMENTO = false` (desabilitado por padrão)
- **Status**: ✅ Funcionando (pode ser habilitado alterando a flag)

**ITENS PENDENTES**:

#### ⏳ Item 3: Página de demonstração de arquitetura
- Criar página mostrando conceitos de arquitetura
- Demonstrar Session, RequestCycle, PageParameters, Page, Application
- Manter simples e didático

#### ⏳ Item 4: Componente customizado (demonstrando tríade)
- Criar componente reutilizável
- Demonstrar Component + Markup + Model
- Exemplo prático relacionado ao projeto de certidões

#### ⏳ Item 5: Behavior customizado
- Criar Behavior reutilizável
- Exemplo prático relacionado ao projeto

### MÓDULOS FUTUROS (Ainda não iniciados)
- **Módulo 3**: Modelos (Models)
- **Módulo 4**: Formulários (Forms)
- **Módulo 5**: Composição de páginas e componentes
- **Módulo 6**: Ajax e componentes ricos
- **Módulo 7**: Extras (Autenticação, I18N, Testes, etc.)

## ESTRUTURA DO PROJETO

```
certidoes/
├── src/main/java/com/hvivox/certidoes/
│   ├── WicketApplication.java          # Application (configurada com Session e Listener)
│   ├── BasePage.java                   # Página base com layout comum
│   ├── HomePage.java                   # Página inicial (com dashboard da Session)
│   ├── session/
│   │   └── CertidoesSession.java       # ✅ Session customizada (Item 1)
│   ├── listener/
│   │   └── CertidoesRequestCycleListener.java  # ✅ RequestCycleListener (Item 2)
│   ├── page/
│   │   ├── CertidaoListPage.java      # Lista certidões (usa Session)
│   │   ├── CertidaoFormPage.java      # Formulário criar/editar
│   │   ├── CertidaoDetailPage.java    # Detalhes da certidão
│   │   └── ComponentesDemoPage.java    # ✅ Demonstração de componentes (Módulo 1)
│   ├── domain/
│   │   ├── Certidao.java
│   │   ├── CertidaoTipo.java
│   │   └── CertidaoStatus.java
│   └── infra/
│       ├── CertidaoRepository.java
│       └── InMemoryCertidaoRepository.java
```

## PÁGINAS EXISTENTES

- **HomePage**: Página inicial com dashboard da Session
- **CertidaoListPage**: Lista todas as certidões
- **CertidaoFormPage**: Formulário para criar/editar certidão
- **CertidaoDetailPage**: Detalhes de uma certidão
- **ComponentesDemoPage**: Demonstração de componentes do Wicket

## CONCEITOS JÁ IMPLEMENTADOS

### Session Customizada (Item 1)
- Classe: `CertidoesSession` estende `WebSession`
- Funcionalidade: Contador de certidões excluídas
- Uso: `CertidoesSession.get().incrementarCertidoesExcluidas()`
- Dashboard: Exibido na `HomePage`

### RequestCycleListener (Item 2)
- Classe: `CertidoesRequestCycleListener` estende `AbstractRequestCycleListener`
- Funcionalidade: Logging do ciclo de requisição
- Flag: `HABILITAR_MONITORAMENTO = false` (desabilitado por padrão)
- Para habilitar: Alterar flag para `true` em `CertidoesRequestCycleListener.java`

## PRÓXIMOS PASSOS

**Continuar com Módulo 2 - Item 3**: Criar página de demonstração de arquitetura

A página deve demonstrar:
- Session customizada (mostrar dados da Session)
- RequestCycle (informações do ciclo)
- PageParameters (parâmetros da URL)
- Page (informações da página)
- Application (informações da Application)

**Importante**: Manter simples, didático e focado no projeto de certidões quando possível.

## FORMATO DE RESPOSTA ESPERADO

Para cada item:
1. "Implementação do item [Nome do Item]:"
   - Explicação do que será implementado
   - Código completo
   - Onde foi adicionado/modificado
   - Como testar

## INSTRUÇÕES PARA CONTINUAR

1. Analise o projeto atual
2. Identifique o que falta no Módulo 2 (Itens 3, 4 e 5)
3. Implemente um item por vez
4. Mantenha código simples, didático e focado no projeto
5. Teste cada implementação antes de avançar

---

**Última atualização**: Módulo 2 - Item 2 concluído
**Próximo item**: Módulo 2 - Item 3 (Página de demonstração de arquitetura)


