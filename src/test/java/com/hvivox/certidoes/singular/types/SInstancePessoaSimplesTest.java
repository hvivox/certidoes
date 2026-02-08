package com.hvivox.certidoes.singular.types;

import com.hvivox.certidoes.singular.packages.CertidoesPackage;
import org.junit.Before;
import org.junit.Test;
import org.opensingular.form.SInstance;

import static org.junit.Assert.*;

/**
 * Módulo 1 - Tarefa 4: Teste de Manipulação de SInstance
 * 
 * Testa a criação e manipulação de SInstance do tipo PessoaSimples,
 * demonstrando getValue e setValue.
 * 
 * CONCEITOS TESTADOS:
 * - newInstance(): Criação de instância a partir de um SType
 * - setValue(): Definição de valores em campos da instância
 * - getValue(): Recuperação de valores dos campos
 * - Ciclo completo: criar, setar, recuperar e validar
 */
public class SInstancePessoaSimplesTest {

    private STypePessoaSimples tipo;

    /**
     * Inicializa o tipo antes de cada teste
     * No OpenSingular, os tipos precisam ser carregados através do SPackage
     */
    @Before
    public void setUp() {
        // Criar e inicializar o package primeiro para garantir que os tipos sejam carregados
        CertidoesPackage spackage = new CertidoesPackage();
        // Instanciar o tipo - ele será automaticamente registrado no package quando instanciado
        tipo = new STypePessoaSimples();
    }

    /**
     * Obtém o STypePessoaSimples
     */
    private STypePessoaSimples getTipo() {
        return tipo;
    }

    @Test
    public void testCriarSInstance() {
        // Testa que é possível criar uma nova SInstance
        STypePessoaSimples tipo = getTipo();
        assertNotNull("Tipo deve ser obtido do package", tipo);
        
        SInstance instance = tipo.newInstance();
        assertNotNull("SInstance deve ser criada com sucesso", instance);
        assertEquals("Tipo da instância deve ser STypePessoaSimples", 
                     tipo, instance.getType());
    }

    @Test
    public void testSetValueCampoNome() {
        // Testa que é possível definir valor do campo nome
        STypePessoaSimples tipo = getTipo();
        SInstance instance = tipo.newInstance();
        
        String valorNome = "João Silva";
        // Usar getField() para obter o campo e então setValue
        instance.getField("nome").setValue(valorNome);
        
        // Verifica que o valor foi setado usando getField().getValue()
        String valorRecuperado = (String) instance.getField("nome").getValue();
        assertEquals("Valor do campo nome deve ser o mesmo setado", 
                     valorNome, valorRecuperado);
    }

    @Test
    public void testSetValueCampoEmail() {
        // Testa que é possível definir valor do campo email
        STypePessoaSimples tipo = getTipo();
        SInstance instance = tipo.newInstance();
        
        String valorEmail = "joao.silva@email.com";
        // Usar getField() para obter o campo e então setValue
        instance.getField("email").setValue(valorEmail);
        
        // Verifica que o valor foi setado usando getField().getValue()
        String valorRecuperado = (String) instance.getField("email").getValue();
        assertEquals("Valor do campo email deve ser o mesmo setado", 
                     valorEmail, valorRecuperado);
    }

    @Test
    public void testGetValueRecuperaValores() {
        // Testa que é possível recuperar valores dos campos
        STypePessoaSimples tipo = getTipo();
        SInstance instance = tipo.newInstance();
        
        String nomeEsperado = "Maria Santos";
        String emailEsperado = "maria.santos@email.com";
        
        // Setar valores usando getField().setValue()
        instance.getField("nome").setValue(nomeEsperado);
        instance.getField("email").setValue(emailEsperado);
        
        // Recuperar valores usando getField().getValue()
        String nomeRecuperado = (String) instance.getField("nome").getValue();
        String emailRecuperado = (String) instance.getField("email").getValue();
        
        // Validar que os valores foram recuperados corretamente
        assertEquals("Nome recuperado deve ser igual ao setado", 
                     nomeEsperado, nomeRecuperado);
        assertEquals("Email recuperado deve ser igual ao setado", 
                     emailEsperado, emailRecuperado);
    }

    @Test
    public void testCicloCompletoSetValueGetValue() {
        // Testa ciclo completo: criar instância, setar valores, recuperar e validar
        STypePessoaSimples tipo = getTipo();
        SInstance instance = tipo.newInstance();
        
        // Valores iniciais
        String nomeInicial = "Pedro Oliveira";
        String emailInicial = "pedro.oliveira@email.com";
        
        // Setar valores usando getField().setValue()
        instance.getField("nome").setValue(nomeInicial);
        instance.getField("email").setValue(emailInicial);
        
        // Recuperar e validar usando getField().getValue()
        String nomeRecuperado = (String) instance.getField("nome").getValue();
        String emailRecuperado = (String) instance.getField("email").getValue();
        
        assertEquals("Nome deve ser persistido corretamente", 
                     nomeInicial, nomeRecuperado);
        assertEquals("Email deve ser persistido corretamente", 
                     emailInicial, emailRecuperado);
        
        // Modificar valores e validar novamente
        String nomeModificado = "Pedro Silva Oliveira";
        String emailModificado = "pedro.silva@email.com";
        
        instance.getField("nome").setValue(nomeModificado);
        instance.getField("email").setValue(emailModificado);
        
        nomeRecuperado = (String) instance.getField("nome").getValue();
        emailRecuperado = (String) instance.getField("email").getValue();
        
        assertEquals("Nome modificado deve ser persistido", 
                     nomeModificado, nomeRecuperado);
        assertEquals("Email modificado deve ser persistido", 
                     emailModificado, emailRecuperado);
    }

    @Test
    public void testGetValueComStringPath() {
        // Testa que também é possível usar getField() com string para getValue e setValue
        STypePessoaSimples tipo = getTipo();
        SInstance instance = tipo.newInstance();
        
        String nomeEsperado = "Ana Costa";
        String emailEsperado = "ana.costa@email.com";
        
        // Setar usando getField() com string
        instance.getField("nome").setValue(nomeEsperado);
        instance.getField("email").setValue(emailEsperado);
        
        // Recuperar usando getField() com string
        String nomeRecuperado = (String) instance.getField("nome").getValue();
        String emailRecuperado = (String) instance.getField("email").getValue();
        
        assertEquals("Nome deve ser recuperado usando getField()", 
                     nomeEsperado, nomeRecuperado);
        assertEquals("Email deve ser recuperado usando getField()", 
                     emailEsperado, emailRecuperado);
    }

    @Test
    public void testValoresIniciaisNull() {
        // Testa que valores iniciais são null
        STypePessoaSimples tipo = getTipo();
        SInstance instance = tipo.newInstance();
        
        String nomeInicial = (String) instance.getField("nome").getValue();
        String emailInicial = (String) instance.getField("email").getValue();
        
        assertNull("Nome inicial deve ser null", nomeInicial);
        assertNull("Email inicial deve ser null", emailInicial);
    }
}
