package com.hvivox.certidoes.component;

import com.hvivox.certidoes.domain.Certidao;
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

        // Tipo da certidão (usando método do enum - refatorado)
        Label tipoLabel = new Label("tipo", Model.of(
                model.getObject() != null && model.getObject().getTipo() != null
                        ? model.getObject().getTipo().getDescricao()
                        : ""));
        add(tipoLabel);

        // Interessado
        add(new Label("interessado", new PropertyModel<>(model, "interessado")));

        // Data de emissão
        add(new Label("dataEmissao", new PropertyModel<>(model, "dataEmissao")));

        // Badge de status (usando método do enum - refatorado)
        // O enum já retorna o HTML formatado do badge
        Label statusBadge = new Label("statusBadge", Model.of(
                model.getObject() != null && model.getObject().getStatus() != null
                        ? model.getObject().getStatus().getBadgeHtml()
                        : "<span class='badge badge-secondary'>Sem Status</span>"));
        statusBadge.setEscapeModelStrings(false); // Permite HTML no badge
        add(statusBadge);

        // Link para detalhes da certidão
        PageParameters params = new PageParameters();
        params.add("id", new PropertyModel<>(model, "id").getObject());
        add(new BookmarkablePageLink<>("linkDetalhes",
                com.hvivox.certidoes.page.CertidaoDetailPage.class, params));
    }
}
