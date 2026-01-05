package com.hvivox.certidoes.converter;

import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import java.util.Locale;

/**
 * MÓDULO 7 - ITEM 54: CONVERSOR CUSTOMIZADO PARA CNPJ
 * 
 * Converte entre String e CNPJ, aplicando formatação automática.
 * 
 * FORMATO: ##.###.###/####-##
 * 
 * EXEMPLOS:
 * - Input: "12345678000195" → Output: "12.345.678/0001-95"
 * - Input: "12.345.678/0001-95" → Output: "12345678000195" (apenas números)
 */
public class CNPJConverter implements IConverter<String> {
    
    private static final long serialVersionUID = 1L;

    @Override
    public String convertToObject(String value, Locale locale) throws ConversionException {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        
        // Remove formatação (pontos, traços, barras, espaços)
        String cnpjLimpo = value.replaceAll("[^0-9]", "");
        
        // Validar comprimento
        if (cnpjLimpo.length() != 14) {
            throw new ConversionException("CNPJ deve conter 14 dígitos")
                .setResourceKey("CNPJConverter.invalid");
        }
        
        return cnpjLimpo;
    }

    @Override
    public String convertToString(String value, Locale locale) {
        if (value == null || value.trim().isEmpty()) {
            return "";
        }
        
        // Remove qualquer formatação existente
        String cnpjLimpo = value.replaceAll("[^0-9]", "");
        
        // Aplicar formatação ##.###.###/####-##
        if (cnpjLimpo.length() == 14) {
            return String.format("%s.%s.%s/%s-%s",
                cnpjLimpo.substring(0, 2),
                cnpjLimpo.substring(2, 5),
                cnpjLimpo.substring(5, 8),
                cnpjLimpo.substring(8, 12),
                cnpjLimpo.substring(12, 14)
            );
        }
        
        return value;
    }
}

