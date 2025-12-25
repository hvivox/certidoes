package com.hvivox.certidoes.page;

import com.hvivox.certidoes.BasePage;
import com.hvivox.certidoes.domain.Certidao;
import com.hvivox.certidoes.infra.CertidaoRepository;
import com.hvivox.certidoes.infra.InMemoryCertidaoRepository;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.List;

public class CertidaoListPage extends BasePage {
    private static final long serialVersionUID = 1L;
    
    // Repositório (por enquanto instância direta, depois vamos melhorar)
    private final CertidaoRepository repository = new InMemoryCertidaoRepository();

    public CertidaoListPage(final PageParameters parameters) {
        super();
        
        // Buscar todas as certidões
        List<Certidao> certidoes = repository.findAll();
        
        // Container para a tabela (para poder esconder se não houver dados)
        WebMarkupContainer tableContainer = new WebMarkupContainer("tableContainer");
        tableContainer.setVisible(!certidoes.isEmpty());
        add(tableContainer);
        
        // ListView para repetir as linhas da tabela
        ListView<Certidao> certidoesList = new ListView<Certidao>("certidoes", certidoes) {
            @Override
            protected void populateItem(ListItem<Certidao> item) {
                Certidao certidao = item.getModelObject();
                
                // Colunas simples
                item.add(new Label("id", certidao.getId()));
                item.add(new Label("numero", certidao.getNumero()));
                item.add(new Label("tipo", formatTipo(certidao.getTipo())));
                item.add(new Label("interessado", certidao.getInteressado()));
                item.add(new Label("dataEmissao", certidao.getDataEmissao()));
                item.add(new Label("status", formatStatus(certidao.getStatus())));
                
                // Links de ação
                PageParameters params = new PageParameters();
                params.add("id", certidao.getId());
                
                // Link Ver (vamos criar a página no Passo 4)
                BookmarkablePageLink<Void> linkVer = new BookmarkablePageLink<>("linkVer", CertidaoDetailPage.class, params);
                item.add(linkVer);
                
                // Link Editar (vamos usar a mesma página do formulário)
                BookmarkablePageLink<Void> linkEditar = new BookmarkablePageLink<>("linkEditar", CertidaoFormPage.class, params);
                item.add(linkEditar);
                
                // Link Excluir (simples, por enquanto só redireciona)
                Link<Void> linkExcluir = new Link<Void>("linkExcluir") {
                    @Override
                    public void onClick() {
                        // Por enquanto só redireciona, implementaremos a exclusão no Passo 3
                        setResponsePage(CertidaoListPage.class);
                    }
                };
                item.add(linkExcluir);
            }
        };
        tableContainer.add(certidoesList);
        
        // Mensagem quando não houver dados
        WebMarkupContainer emptyMessage = new WebMarkupContainer("emptyMessage");
        emptyMessage.setVisible(certidoes.isEmpty());
        add(emptyMessage);
        
        // Link "Nova Certidão" no topo
        add(new BookmarkablePageLink<>("linkNovaCertidao", CertidaoFormPage.class));
    }
    
    /**
     * Formata o tipo da certidão para exibição
     */
    private String formatTipo(com.hvivox.certidoes.domain.CertidaoTipo tipo) {
        if (tipo == null) return "";
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
     * Formata o status da certidão para exibição
     */
    private String formatStatus(com.hvivox.certidoes.domain.CertidaoStatus status) {
        if (status == null) return "";
        switch (status) {
            case RASCUNHO:
                return "Rascunho";
            case EMITIDA:
                return "Emitida";
            case CANCELADA:
                return "Cancelada";
            default:
                return status.toString();
        }
    }
}