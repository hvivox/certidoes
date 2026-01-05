package com.hvivox.certidoes.converter;

import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import java.util.Locale;

/**
 * MÓDULO 7 - ITEM 54: CONVERSOR CUSTOMIZADO PARA CEP
 * 
 * Converte entre String e CEP, aplicando formatação automática.
 * 
 * FORMATO: #####-###
 * 
 * EXEMPLOS:
 * - Input: "12345678" → Output: "12345-678"
 * - Input: "12345-678" → Output: "12345678" (apenas números)
 */
public class CEPConverter implements IConverter<String> {
    
    private static final long serialVersionUID = 1L;

    @Override
    public String convertToObject(String value, Locale locale) throws ConversionException {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        
        // Remove formatação (traços, espaços)
        String cepLimpo = value.replaceAll("[^0-9]", "");
        
        // Validar comprimento
        if (cepLimpo.length() != 8) {
            throw new ConversionException("CEP deve conter 8 dígitos")
                .setResourceKey("CEPConverter.invalid");
        }
        
        return cepLimpo;
    }

    @Override
    public String convertToString(String value, Locale locale) {
        if (value == null || value.trim().isEmpty()) {
            return "";
        }
        
        // Remove qualquer formatação existente
        String cepLimpo = value.replaceAll("[^0-9]", "");
        
        // Aplicar formatação #####-###
        if (cepLimpo.length() == 8) {
            return String.format("%s-%s",
                cepLimpo.substring(0, 5),
                cepLimpo.substring(5, 8)
            );
        }
        
        return value;
    }
}

