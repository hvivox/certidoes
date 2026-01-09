package com.hvivox.certidoes.service;

import com.hvivox.certidoes.domain.Certidao;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * MÓDULO 7 - ITEM 59: RELATÓRIOS COM APACHE POI - EXCEL
 * 
 * Serviço para geração de relatórios de certidões em formato Excel (.xlsx).
 * 
 * Utiliza Apache POI para criar planilhas Excel com:
 * - Cabeçalho formatado
 * - Dados organizados em tabela
 * - Estilização profissional
 */
public class RelatorioExcelService implements RelatorioService {
    
    @Override
    public ByteArrayOutputStream gerarRelatorio(List<Certidao> certidoes) throws Exception {
        // Criar workbook (arquivo Excel)
        Workbook workbook = new XSSFWorkbook();
        
        // Criar sheet (aba)
        Sheet sheet = workbook.createSheet("Certidões");
        
        // Criar estilos
        CellStyle headerStyle = criarEstiloHeader(workbook);
        CellStyle dataStyle = criarEstiloData(workbook);
        
        // Criar linha de cabeçalho
        Row headerRow = sheet.createRow(0);
        String[] colunas = {"ID", "Número", "Tipo", "Interessado", "Data Emissão", "Status"};
        
        for (int i = 0; i < colunas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(colunas[i]);
            cell.setCellStyle(headerStyle);
        }
        
        // Preencher dados
        int rowNum = 1;
        for (Certidao certidao : certidoes) {
            Row row = sheet.createRow(rowNum++);
            
            Cell cell0 = row.createCell(0);
            cell0.setCellValue(certidao.getId() != null ? certidao.getId() : 0);
            cell0.setCellStyle(dataStyle);
            
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(certidao.getNumero() != null ? certidao.getNumero() : "");
            cell1.setCellStyle(dataStyle);
            
            Cell cell2 = row.createCell(2);
            cell2.setCellValue(certidao.getTipo() != null ? certidao.getTipo().getDescricao() : "");
            cell2.setCellStyle(dataStyle);
            
            Cell cell3 = row.createCell(3);
            cell3.setCellValue(certidao.getInteressado() != null ? certidao.getInteressado() : "");
            cell3.setCellStyle(dataStyle);
            
            Cell cell4 = row.createCell(4);
            cell4.setCellValue(certidao.getDataEmissao() != null ? certidao.getDataEmissao() : "");
            cell4.setCellStyle(dataStyle);
            
            Cell cell5 = row.createCell(5);
            cell5.setCellValue(certidao.getStatus() != null ? certidao.getStatus().getDescricao() : "");
            cell5.setCellStyle(dataStyle);
        }
        
        // Ajustar largura das colunas
        for (int i = 0; i < colunas.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        // Escrever para ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        
        return outputStream;
    }
    
    /**
     * Cria estilo para cabeçalho
     */
    private CellStyle criarEstiloHeader(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        
        // Cor de fundo
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        // Fonte
        Font font = workbook.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        
        // Alinhamento
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        
        // Bordas
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        
        return style;
    }
    
    /**
     * Cria estilo para dados
     */
    private CellStyle criarEstiloData(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        
        // Bordas
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        
        // Alinhamento
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        
        return style;
    }
    
    @Override
    public String getContentType() {
        return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    }
    
    @Override
    public String getFileExtension() {
        return ".xlsx";
    }
}

