# TaskMaster AI - Configuração

Este diretório contém a configuração do TaskMaster AI para o projeto Certidões.

## Estrutura

```
.taskmaster/
├── config.json          # Configuração principal do projeto
├── tasks.json           # Arquivo de tarefas (gerado automaticamente)
├── docs/
│   └── prd.txt         # Product Requirements Document
└── templates/
    └── example_prd.txt # Template de exemplo para PRD
```

## Configuração Inicial

### 1. Configurar Chaves de API

Crie um arquivo `.env` na raiz do projeto com suas chaves de API:

```env
ANTHROPIC_API_KEY=sua_chave_aqui
PERPLEXITY_API_KEY=sua_chave_aqui
# Adicione outras chaves conforme necessário
```

### 2. Usar o TaskMaster AI

O TaskMaster AI está configurado via MCP (Model Control Protocol) no Cursor. Você pode usar os comandos através do assistente ou via terminal:

```bash
# Listar tarefas
npx task-master list

# Mostrar próxima tarefa
npx task-master next

# Adicionar nova tarefa
npx task-master add-task "Descrição da tarefa"

# Marcar tarefa como concluída
npx task-master set-status 1 done
```

### 3. Gerar Tarefas a partir do PRD

Para gerar tarefas automaticamente a partir do PRD:

```bash
npx task-master parse-prd .taskmaster/docs/prd.txt
```

## Notas

- O arquivo `tasks.json` é gerado automaticamente e não deve ser editado manualmente na maioria dos casos
- O PRD em `.taskmaster/docs/prd.txt` pode ser atualizado conforme o projeto evolui
- As chaves de API devem ser mantidas no arquivo `.env` (que está no .gitignore)

## Integração com Cursor

O TaskMaster AI está configurado no arquivo `mcp.json` do Cursor, permitindo que você use os comandos diretamente através do assistente de IA.

