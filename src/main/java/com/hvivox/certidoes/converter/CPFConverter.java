package com.hvivox.certidoes.converter;

import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import java.util.Locale;

/**
 * MÓDULO 7 - ITEM 54: CONVERSOR CUSTOMIZADO PARA CPF
 * 
 * Converte entre String e CPF, aplicando formatação automática.
 * 
 * FORMATO: ###.###.###-##
 * 
 * EXEMPLOS:
 * - Input: "12345678901" → Output: "123.456.789-01"
 * - Input: "123.456.789-01" → Output: "12345678901" (apenas números)
 * 
 * USO:
 * 
 * // Em um FormComponent:
 * TextField<String> cpfField = new TextField<>("cpf");
 * cpfField.setType(String.class);
 * 
 * // Sobrescrever getConverter():
 * @Override
 * public <C> IConverter<C> getConverter(Class<C> type) {
 *     if (type == String.class) {
 *         return (IConverter<C>) new CPFConverter();
 *     }
 *     return super.getConverter(type);
 * }
 */
public class CPFConverter implements IConverter<String> {
    
    private static final long serialVersionUID = 1L;

    /**
     * Converte de String (formatada ou não) para String (apenas números).
     * Remove formatação para armazenar/processar.
     */
    @Override
    public String convertToObject(String value, Locale locale) throws ConversionException {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        
        // Remove formatação (pontos, traços, espaços)
        String cpfLimpo = value.replaceAll("[^0-9]", "");
        
        // Validar comprimento
        if (cpfLimpo.length() != 11) {
            throw new ConversionException("CPF deve conter 11 dígitos")
                .setResourceKey("CPFConverter.invalid");
        }
        
        return cpfLimpo;
    }

    /**
     * Converte de String (apenas números) para String (formatada).
     * Aplica formatação para exibição.
     */
    @Override
    public String convertToString(String value, Locale locale) {
        if (value == null || value.trim().isEmpty()) {
            return "";
        }
        
        // Remove qualquer formatação existente
        String cpfLimpo = value.replaceAll("[^0-9]", "");
        
        // Aplicar formatação ###.###.###-##
        if (cpfLimpo.length() == 11) {
            return String.format("%s.%s.%s-%s",
                cpfLimpo.substring(0, 3),
                cpfLimpo.substring(3, 6),
                cpfLimpo.substring(6, 9),
                cpfLimpo.substring(9, 11)
            );
        }
        
        // Se não tiver 11 dígitos, retornar como está
        return value;
    }
}

