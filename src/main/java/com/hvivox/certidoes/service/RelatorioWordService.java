package com.hvivox.certidoes.service;

import com.hvivox.certidoes.domain.Certidao;
import org.apache.poi.xwpf.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * MÓDULO 7 - ITEM 59: RELATÓRIOS COM APACHE POI - WORD
 * 
 * Serviço para geração de relatórios de certidões em formato Word (.docx).
 * 
 * Utiliza Apache POI para criar documentos Word com:
 * - Título formatado
 * - Tabela com dados das certidões
 * - Estilização profissional
 */
public class RelatorioWordService implements RelatorioService {
    
    @Override
    public ByteArrayOutputStream gerarRelatorio(List<Certidao> certidoes) throws Exception {
        // Criar documento
        XWPFDocument document = new XWPFDocument();
        
        // Adicionar título
        XWPFParagraph titulo = document.createParagraph();
        titulo.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun runTitulo = titulo.createRun();
        runTitulo.setText("Relatório de Certidões");
        runTitulo.setBold(true);
        runTitulo.setFontSize(18);
        runTitulo.setFontFamily("Arial");
        runTitulo.addBreak();
        
        // Adicionar data do relatório
        XWPFParagraph dataRelatorio = document.createParagraph();
        dataRelatorio.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun runData = dataRelatorio.createRun();
        runData.setText("Gerado em: " + new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date()));
        runData.setFontSize(10);
        runData.setFontFamily("Arial");
        runData.addBreak();
        runData.addBreak();
        
        // Criar tabela
        XWPFTable table = document.createTable(certidoes.size() + 1, 6);
        table.setWidth("100%");
        
        // Cabeçalho da tabela
        XWPFTableRow headerRow = table.getRow(0);
        configurarCelulaHeader(headerRow.getCell(0), "ID");
        configurarCelulaHeader(headerRow.getCell(1), "Número");
        configurarCelulaHeader(headerRow.getCell(2), "Tipo");
        configurarCelulaHeader(headerRow.getCell(3), "Interessado");
        configurarCelulaHeader(headerRow.getCell(4), "Data Emissão");
        configurarCelulaHeader(headerRow.getCell(5), "Status");
        
        // Preencher dados
        int rowIndex = 1;
        for (Certidao certidao : certidoes) {
            XWPFTableRow row = table.getRow(rowIndex++);
            
            configurarCelula(row.getCell(0), certidao.getId() != null ? certidao.getId().toString() : "");
            configurarCelula(row.getCell(1), certidao.getNumero() != null ? certidao.getNumero() : "");
            configurarCelula(row.getCell(2), certidao.getTipo() != null ? certidao.getTipo().getDescricao() : "");
            configurarCelula(row.getCell(3), certidao.getInteressado() != null ? certidao.getInteressado() : "");
            configurarCelula(row.getCell(4), certidao.getDataEmissao() != null ? certidao.getDataEmissao() : "");
            configurarCelula(row.getCell(5), certidao.getStatus() != null ? certidao.getStatus().getDescricao() : "");
        }
        
        // Adicionar rodapé
        XWPFParagraph rodape = document.createParagraph();
        rodape.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun runRodape = rodape.createRun();
        runRodape.addBreak();
        runRodape.addBreak();
        runRodape.setText("Total de certidões: " + certidoes.size());
        runRodape.setFontSize(10);
        runRodape.setItalic(true);
        runRodape.setFontFamily("Arial");
        
        // Escrever para ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.write(outputStream);
        document.close();
        
        return outputStream;
    }
    
    /**
     * Configura célula do cabeçalho
     */
    private void configurarCelulaHeader(XWPFTableCell cell, String text) {
        cell.setColor("2E75B5"); // Azul escuro
        XWPFParagraph paragraph = cell.getParagraphs().get(0);
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        run.setBold(true);
        run.setColor("FFFFFF"); // Branco
        run.setFontSize(11);
        run.setFontFamily("Arial");
    }
    
    /**
     * Configura célula de dados
     */
    private void configurarCelula(XWPFTableCell cell, String text) {
        XWPFParagraph paragraph = cell.getParagraphs().get(0);
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        run.setFontSize(10);
        run.setFontFamily("Arial");
    }
    
    @Override
    public String getContentType() {
        return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    }
    
    @Override
    public String getFileExtension() {
        return ".docx";
    }
}

