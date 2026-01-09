package com.hvivox.certidoes;

import com.hvivox.certidoes.converter.CEPConverter;
import com.hvivox.certidoes.converter.CNPJConverter;
import com.hvivox.certidoes.converter.CPFConverter;
import com.hvivox.certidoes.converter.MoedaBrasileiraConverter;
import org.apache.wicket.util.convert.ConversionException;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * MÓDULO 7 - ITEM 55: TESTE UNITÁRIO 3 - Converters Customizados
 * 
 * Testa os conversores customizados.
 * 
 * TESTES:
 * - CPFConverter formata corretamente
 * - CNPJConverter formata corretamente
 * - CEPConverter formata corretamente
 * - MoedaBrasileiraConverter formata corretamente
 * - Validações funcionam
 */
public class TestConverters {

    private static final Locale LOCALE_BR = new Locale("pt", "BR");

    @Test
    public void cpfConverterFormatsCorrectly() {
        CPFConverter converter = new CPFConverter();

        // Testar formatação
        String formatted = converter.convertToString("12345678901", LOCALE_BR);
        assertEquals("123.456.789-01", formatted);

        // Testar remoção de formatação
        String unformatted = converter.convertToObject("123.456.789-01", LOCALE_BR);
        assertEquals("12345678901", unformatted);
    }

    @Test(expected = ConversionException.class)
    public void cpfConverterValidatesLength() {
        CPFConverter converter = new CPFConverter();

        // Deve lançar exceção para CPF inválido (menos de 11 dígitos)
        converter.convertToObject("123456789", LOCALE_BR);
    }

    @Test
    public void cnpjConverterFormatsCorrectly() {
        CNPJConverter converter = new CNPJConverter();

        // Testar formatação
        String formatted = converter.convertToString("12345678000195", LOCALE_BR);
        assertEquals("12.345.678/0001-95", formatted);

        // Testar remoção de formatação
        String unformatted = converter.convertToObject("12.345.678/0001-95", LOCALE_BR);
        assertEquals("12345678000195", unformatted);
    }

    @Test(expected = ConversionException.class)
    public void cnpjConverterValidatesLength() {
        CNPJConverter converter = new CNPJConverter();

        // Deve lançar exceção para CNPJ inválido
        converter.convertToObject("12345678", LOCALE_BR);
    }

    @Test
    public void cepConverterFormatsCorrectly() {
        CEPConverter converter = new CEPConverter();

        // Testar formatação
        String formatted = converter.convertToString("12345678", LOCALE_BR);
        assertEquals("12345-678", formatted);

        // Testar remoção de formatação
        String unformatted = converter.convertToObject("12345-678", LOCALE_BR);
        assertEquals("12345678", unformatted);
    }

    @Test
    public void moedaBrasileiraConverterFormatsCorrectly() {
        MoedaBrasileiraConverter converter = new MoedaBrasileiraConverter();

        // Testar formatação
        BigDecimal value = new BigDecimal("1234.56");
        String formatted = converter.convertToString(value, LOCALE_BR);
        assertTrue(formatted.contains("1.234,56"));
        assertTrue(formatted.contains("R$"));
    }

    @Test
    public void moedaBrasileiraConverterParsesCorrectly() {
        MoedaBrasileiraConverter converter = new MoedaBrasileiraConverter();

        // Testar parse
        BigDecimal value = converter.convertToObject("R$ 1.234,56", LOCALE_BR);
        assertEquals(new BigDecimal("1234.56"), value);
    }

    @Test
    public void convertersHandleNullValues() {
        CPFConverter cpfConverter = new CPFConverter();
        CNPJConverter cnpjConverter = new CNPJConverter();
        CEPConverter cepConverter = new CEPConverter();
        MoedaBrasileiraConverter moedaConverter = new MoedaBrasileiraConverter();

        // Todos devem retornar null ou string vazia para valores nulos
        assertNull(cpfConverter.convertToObject(null, LOCALE_BR));
        assertEquals("", cpfConverter.convertToString(null, LOCALE_BR));

        assertNull(cnpjConverter.convertToObject(null, LOCALE_BR));
        assertEquals("", cnpjConverter.convertToString(null, LOCALE_BR));

        assertNull(cepConverter.convertToObject(null, LOCALE_BR));
        assertEquals("", cepConverter.convertToString(null, LOCALE_BR));

        assertNull(moedaConverter.convertToObject(null, LOCALE_BR));
        assertEquals("", moedaConverter.convertToString(null, LOCALE_BR));
    }
}
