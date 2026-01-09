package com.hvivox.certidoes.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;

/**
 * ====================================================================
 * BEHAVIOR CUSTOMIZADO - MODAL BOOTSTRAP
 * ====================================================================
 * 
 * Behavior customizado que adiciona confirmação via Modal Bootstrap antes
 * de executar uma ação (como exclusão, cancelamento, etc.).
 * 
 * CONCEITOS DEMONSTRADOS:
 * - Behavior: adiciona comportamento a componentes sem modificar sua classe
 * - Reutilização: pode ser usado em qualquer componente (Link, Button, etc.)
 * - Encapsulamento: lógica de confirmação centralizada
 * - ComponentTag: modifica atributos HTML do componente
 * - Modal Bootstrap: interface mais amigável que confirm() nativo
 * 
 * VANTAGENS DO MODAL BOOTSTRAP:
 * - Interface mais moderna e amigável
 * - Consistente com o design do sistema (Bootstrap)
 * - Melhor experiência do usuário
 * - Personalizável (cores, ícones, etc.)
 * 
 * CONTEXTO DO PROJETO (CERTIDÕES):
 * Este behavior é usado para confirmar exclusão de certidões antes de
 * executar a ação, evitando exclusões acidentais.
 * 
 * COMO USAR:
 * Link<Void> linkExcluir = new Link<>("linkExcluir") {
 * 
 * @Override
 *           public void onClick() {
 *           // ação de exclusão
 *           }
 *           };
 *           linkExcluir.add(new ConfirmacaoBehavior("Tem certeza que deseja
 *           excluir?"));
 * 
 *           OU com mensagem padrão:
 *           linkExcluir.add(new ConfirmacaoBehavior());
 */
public class ConfirmacaoBehavior extends Behavior {
    private static final long serialVersionUID = 1L;

    /**
     * Mensagem padrão de confirmação
     */
    private static final String MENSAGEM_PADRAO = "Tem certeza que deseja executar esta ação?";

    /**
     * Mensagem de confirmação customizada
     */
    private final String mensagem;

    /**
     * Construtor com mensagem padrão.
     * 
     * EXEMPLO DE USO:
     * link.add(new ConfirmacaoBehavior());
     */
    public ConfirmacaoBehavior() {
        this.mensagem = MENSAGEM_PADRAO;
    }

    /**
     * Construtor com mensagem customizada.
     * 
     * EXEMPLO DE USO:
     * link.add(new ConfirmacaoBehavior("Tem certeza que deseja excluir esta
     * certidão?"));
     * 
     * @param mensagem Mensagem de confirmação a ser exibida
     */
    public ConfirmacaoBehavior(String mensagem) {
        this.mensagem = mensagem != null ? mensagem : MENSAGEM_PADRAO;
    }

    /**
     * Modifica o atributo onclick do componente para abrir modal Bootstrap.
     * 
     * Este método é chamado quando o componente é renderizado e permite
     * modificar os atributos HTML do componente.
     * 
     * ESTRATÉGIA PARA LINKS DO WICKET:
     * Links do Wicket geram um href especial que contém o estado da página.
     * Quando clicamos, o Wicket intercepta e executa o onClick().
     * 
     * Para funcionar corretamente:
     * 1. Capturamos o href gerado pelo Wicket
     * 2. Prevenimos o comportamento padrão
     * 3. Mostramos o modal
     * 4. Se confirmar, fazemos uma requisição para o href capturado
     * 
     * @param component Componente ao qual o behavior está anexado
     * @param tag       Tag HTML do componente
     */
    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        // Escapar aspas e quebras de linha na mensagem para JavaScript
        String mensagemEscapada = mensagem.replace("'", "\\'")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "");

        // Adicionar atributo data-* com a mensagem
        tag.put("data-confirmacao-mensagem", mensagemEscapada);

        // Adicionar classe CSS para identificar elementos que precisam de confirmação
        String classeAtual = tag.getAttribute("class");
        String novaClasse = (classeAtual != null ? classeAtual + " " : "") + "wicket-confirmacao";
        tag.put("class", novaClasse);

        // Para Links do Wicket, vamos usar uma abordagem com listener global
        // que intercepta o clique antes do Wicket processar
        // O JavaScript será adicionado via listener no BasePage.html

        // Adicionar onclick que intercepta e mostra modal
        // Se confirmar, faz o link clicar programaticamente para o Wicket processar
        StringBuilder js = new StringBuilder();
        js.append("var mensagem = this.getAttribute('data-confirmacao-mensagem'); ");
        js.append("if (!mensagem) { return true; } "); // Se não tem mensagem, deixa passar
        js.append("// Verificar se já foi confirmado (flag para evitar loop) ");
        js.append("if (this.dataset.confirmado === 'true') { ");
        js.append("    this.dataset.confirmado = 'false'; ");
        js.append("    return true; "); // Deixa o Wicket processar
        js.append("} ");
        js.append("event.preventDefault(); ");
        js.append("event.stopPropagation(); ");
        js.append("var elemento = this; ");
        js.append("var callbackConfirmar = function() { ");
        js.append("    // Marcar como confirmado ");
        js.append("    elemento.dataset.confirmado = 'true'; ");
        js.append("    // Fazer o link clicar programaticamente ");
        js.append("    // Isso vai fazer o Wicket processar corretamente ");
        js.append("    var clickEvent = new MouseEvent('click', { ");
        js.append("        view: window, ");
        js.append("        bubbles: true, ");
        js.append("        cancelable: true, ");
        js.append("        buttons: 1 ");
        js.append("    }); ");
        js.append("    elemento.dispatchEvent(clickEvent); ");
        js.append("}; ");
        js.append("abrirModalConfirmacao(mensagem, callbackConfirmar); ");
        js.append("return false;");

        // Adicionar o onclick
        tag.put("onclick", js.toString());
    }

    /**
     * Obtém a mensagem de confirmação.
     * 
     * @return Mensagem de confirmação
     */
    public String getMensagem() {
        return mensagem;
    }
}
