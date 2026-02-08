package com.hvivox.certidoes.singular.types;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Módulo 1 - Tarefa 3: Teste do primeiro SType simples (PessoaSimples)
 * 
 * Testa que o STypePessoaSimples pode ser criado e possui a estrutura esperada.
 * 
 * NOTA: A validação completa dos campos será feita na Tarefa 4 (Manipulação de SInstance),
 * onde criaremos instâncias e verificaremos que os campos funcionam corretamente.
 */
public class STypePessoaSimplesTest {

    @Test
    public void testSTypePessoaSimplesCompila() {
        // Verifica que a classe compila e pode ser referenciada
        // Este é um teste básico para garantir que a estrutura está correta
        Class<?> tipo = STypePessoaSimples.class;
        assertNotNull("STypePessoaSimples deve existir", tipo);
        assertEquals("Nome da classe deve ser correto", 
                     "STypePessoaSimples", tipo.getSimpleName());
    }

    @Test
    public void testSTypePessoaSimplesPossuiAnotacaoSInfoType() {
        // Verifica que a anotação @SInfoType está presente
        // Esta anotação é obrigatória para o OpenSingular identificar o tipo
        boolean temAnotacao = STypePessoaSimples.class
            .isAnnotationPresent(org.opensingular.form.SInfoType.class);
        assertTrue("STypePessoaSimples deve ter anotação @SInfoType", temAnotacao);
    }
}
