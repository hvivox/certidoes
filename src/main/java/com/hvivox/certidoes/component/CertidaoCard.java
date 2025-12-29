package com.hvivox.certidoes.component;

import com.hvivox.certidoes.domain.Certidao;
import com.hvivox.certidoes.domain.CertidaoStatus;
import com.hvivox.certidoes.domain.CertidaoTipo;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * ====================================================================
 *  COMPONENTE CUSTOMIZADO
 * ====================================================================

 * 1. COMPONENT (Componente Java)
 * - Esta classe: CertidaoCard.java
 * - Estende Panel (componente container do Wicket)
 * - Define a lógica e comportamento do componente
 * 
 * 2. MARKUP (HTML)
 * - Arquivo: CertidaoCard.html
 * - Define a estrutura visual (HTML)
 * - Usa wicket:id para vincular elementos ao componente
 * 
 * 3. MODEL (Modelo de Dados)
 * - IModel<Certidao> - fornece os dados da certidão
 * - PropertyModel - acessa propriedades do objeto
 * - Desacopla os dados da apresentação

 * 
 * COMO USAR:
 * Certidao certidao = ...; // obter certidão
 * add(new CertidaoCard("certidaoCard", Model.of(certidao)));
 * 
 * <div wicket:id="certidaoCard"></div>
 * 
 * VANTAGENS:
 * - Reutilizável: pode ser usado em múltiplas páginas
 * - Encapsulado: lógica e apresentação juntas
 * - Flexível: aceita qualquer Model<Certidao>
 * - Manutenível: mudanças no card afetam todos os usos
 */
public class CertidaoCard extends Panel {
    private static final long serialVersionUID = 1L;

    /**
     * Construtor do componente.
     * 
     * @param id    ID do componente (wicket:id no HTML)
     * @param model Modelo que fornece os dados da Certidao
     */
    public CertidaoCard(String id, IModel<Certidao> model) {
        super(id, model);

        // O modelo (IModel<Certidao>) fornece os dados
        // PropertyModel acessa propriedades específicas do objeto

        // Número da certidão
        add(new Label("numero", new PropertyModel<>(model, "numero")));

        // Tipo da certidão (formatado)
        Label tipoLabel = new Label("tipo",
                Model.of(formatTipo(model.getObject() != null ? model.getObject().getTipo() : null)));
        tipoLabel.setEscapeModelStrings(false); // Permite HTML no label
        add(tipoLabel);

        // Interessado
        add(new Label("interessado", new PropertyModel<>(model, "interessado")));

        // Data de emissão
        add(new Label("dataEmissao", new PropertyModel<>(model, "dataEmissao")));

        // Badge de status (classe CSS baseada no status)
        // Este componente exibe o status formatado com badge colorido
        Label statusBadge = new Label("statusBadge", formatStatus(model.getObject()));
        statusBadge.setEscapeModelStrings(false); // Permite HTML no badge
        add(statusBadge);

        // Link para detalhes da certidão
        PageParameters params = new PageParameters();
        params.add("id", new PropertyModel<>(model, "id").getObject());
        add(new BookmarkablePageLink<>("linkDetalhes",
                com.hvivox.certidoes.page.CertidaoDetailPage.class, params));
    }

    /**
     * Formata o tipo da certidão para exibição.
     * 
     * @param tipo Tipo da certidão
     * @return String formatada
     */
    private String formatTipo(CertidaoTipo tipo) {
        if (tipo == null)
            return "";
        switch (tipo) {
            case NEGATIVA:
                return "Negativa";
            case POSITIVA:
                return "Positiva";
            case POSITIVA_COM_EFEITO_DE_NEGATIVA:
                return "Positiva com Efeito de Negativa";
            default:
                return tipo.toString();
        }
    }

    /**
     * Formata o status da certidão com badge HTML colorido.
     * 
     * @param certidao Certidão
     * @return HTML do badge
     */
    private String formatStatus(Certidao certidao) {
        if (certidao == null || certidao.getStatus() == null) {
            return "<span class='badge badge-secondary'>Sem Status</span>";
        }

        CertidaoStatus status = certidao.getStatus();
        String texto = "";
        String classe = "";

        switch (status) {
            case RASCUNHO:
                texto = "Rascunho";
                classe = "badge-secondary";
                break;
            case EMITIDA:
                texto = "Emitida";
                classe = "badge-success";
                break;
            case CANCELADA:
                texto = "Cancelada";
                classe = "badge-danger";
                break;
            default:
                texto = status.toString();
                classe = "badge-secondary";
        }

        return "<span class='badge " + classe + "'>" + texto + "</span>";
    }
}
