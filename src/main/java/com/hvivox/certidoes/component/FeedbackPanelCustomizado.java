package com.hvivox.certidoes.component;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

/**
 * ====================================================================
 * FEEDBACK PANEL CUSTOMIZADO
 * ====================================================================
 * 
 * MÓDULO 4 - ITEM 7: Feedback Avançado
 * 
 * FeedbackPanel customizado com apresentação melhorada:
 * - Banner vermelho destacado para erros
 * - Ícone de alerta
 * - Botão de fechar (X)
 * - Mensagens mais visíveis e profissionais
 * 
 * CARACTERÍSTICAS:
 * - Mantém compatibilidade com FeedbackPanel padrão do Wicket
 * - Estilização Bootstrap customizada
 * - Suporta todos os níveis de feedback (error, warn, info, success)
 * - Botão de fechar funcional via JavaScript
 * 
 * COMO USAR:
 * add(new FeedbackPanelCustomizado("feedback"));
 * 
 * MENSAGENS:
 * - getSession().error() - Banner vermelho
 * - getSession().warn() - Banner amarelo/laranja
 * - getSession().info() - Banner azul
 * - getSession().success() - Banner verde
 */
public class FeedbackPanelCustomizado extends FeedbackPanel {
    private static final long serialVersionUID = 1L;

    public FeedbackPanelCustomizado(String id) {
        super(id);
        setOutputMarkupId(true);
    }

    public FeedbackPanelCustomizado(String id, IFeedbackMessageFilter filter) {
        super(id, filter);
        setOutputMarkupId(true);
    }

    @Override
    protected String getCSSClass(FeedbackMessage message) {
        // Usar classes nativas do Bootstrap 4
        String cssClass = "alert";

        switch (message.getLevel()) {
            case FeedbackMessage.ERROR:
                cssClass += " alert-danger"; // Bootstrap usa "danger" para erros
                break;
            case FeedbackMessage.WARNING:
                cssClass += " alert-warning";
                break;
            case FeedbackMessage.INFO:
                cssClass += " alert-info";
                break;
            case FeedbackMessage.SUCCESS:
                cssClass += " alert-success";
                break;
            default:
                cssClass += " alert-info";
        }

        return cssClass;
    }

}
