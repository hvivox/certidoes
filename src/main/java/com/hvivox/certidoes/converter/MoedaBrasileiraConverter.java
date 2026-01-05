package com.hvivox.certidoes.converter;

import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * MÓDULO 7 - ITEM 54: CONVERSOR CUSTOMIZADO PARA MOEDA BRASILEIRA
 * 
 * Converte entre String e BigDecimal, aplicando formatação de moeda brasileira.
 * 
 * FORMATO: R$ 1.234,56
 * 
 * EXEMPLOS:
 * - Input: "1234.56" → Output: "R$ 1.234,56"
 * - Input: "R$ 1.234,56" → Output: BigDecimal(1234.56)
 * 
 * OBSERVAÇÕES:
 * - Usa BigDecimal para precisão em valores monetários
 * - Aceita entrada com ou sem "R$"
 * - Aceita separadores de milhar ou não
 * - Vírgula como separador decimal (padrão brasileiro)
 */
public class MoedaBrasileiraConverter implements IConverter<BigDecimal> {
    
    private static final long serialVersionUID = 1L;
    private static final Locale LOCALE_BR = new Locale("pt", "BR");

    @Override
    public BigDecimal convertToObject(String value, Locale locale) throws ConversionException {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        
        try {
            // Remove símbolo de moeda e espaços
            String valorLimpo = value.replaceAll("R\\$", "").trim();
            
            // Usar NumberFormat brasileiro para fazer o parse
            NumberFormat nf = NumberFormat.getInstance(LOCALE_BR);
            Number numero = nf.parse(valorLimpo);
            
            return BigDecimal.valueOf(numero.doubleValue());
            
        } catch (ParseException e) {
            throw new ConversionException("Formato de moeda inválido. Use: R$ 1.234,56")
                .setResourceKey("MoedaBrasileiraConverter.invalid");
        }
    }

    @Override
    public String convertToString(BigDecimal value, Locale locale) {
        if (value == null) {
            return "";
        }
        
        // Configurar formatação brasileira
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(LOCALE_BR);
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        
        DecimalFormat df = new DecimalFormat("R$ #,##0.00", symbols);
        
        return df.format(value);
    }
}

