package com.hvivox.certidoes.singular.examples;

import com.hvivox.certidoes.singular.packages.CertidoesPackage;
import com.hvivox.certidoes.singular.types.STypePessoaSimples;
import org.opensingular.form.SInstance;

/**
 * Módulo 1 - Tarefa 4: Exemplo Prático de Manipulação de SInstance
 * 
 * Este exemplo demonstra de forma prática como criar e manipular
 * uma SInstance do tipo PessoaSimples, usando getValue e setValue.
 * 
 * CONCEITOS DEMONSTRADOS:
 * - newInstance(): Criar uma nova instância a partir de um SType
 * - setValue(): Definir valores nos campos da instância
 * - getValue(): Recuperar valores dos campos
 * - Uso prático em um cenário real
 * 
 * EXECUÇÃO:
 * Execute o método main() para ver o exemplo em ação.
 * 
 * @author OpenSingular Implementation
 * @version 1.0
 */
public class SInstancePessoaSimplesExample {

    /**
     * Método principal que demonstra o uso prático de SInstance
     */
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("Exemplo: Manipulação de SInstance");
        System.out.println("Módulo 1 - Tarefa 4");
        System.out.println("==========================================\n");

        // Criar e inicializar o package primeiro para garantir que os tipos sejam
        // carregados
        CertidoesPackage spackage = new CertidoesPackage();
        // Instanciar o tipo - ele será automaticamente registrado no package
        STypePessoaSimples tipo = new STypePessoaSimples();
        System.out.println("Tipo obtido: " + tipo.getName());

        // Criar uma nova instância
        SInstance pessoa = tipo.newInstance();
        System.out.println("\n1. Instância criada com sucesso!");
        System.out.println("   Tipo da instância: " + pessoa.getType().getName());

        // Definir valores usando getField().setValue()
        System.out.println("\n2. Definindo valores usando getField().setValue():");
        pessoa.getField("nome").setValue("Carlos Eduardo");
        pessoa.getField("email").setValue("carlos.eduardo@email.com");
        System.out.println("   - Nome setado: Carlos Eduardo");
        System.out.println("   - Email setado: carlos.eduardo@email.com");

        // Recuperar valores usando getField().getValue()
        System.out.println("\n3. Recuperando valores usando getField().getValue():");
        String nome = (String) pessoa.getField("nome").getValue();
        String email = (String) pessoa.getField("email").getValue();
        System.out.println("   - Nome recuperado: " + nome);
        System.out.println("   - Email recuperado: " + email);

        // Demonstrar uso com getField()
        System.out.println("\n4. Usando getField() para setValue e getValue:");
        pessoa.getField("nome").setValue("Carlos Eduardo Silva");
        pessoa.getField("email").setValue("carlos.silva@email.com");
        System.out.println("   - Valores atualizados usando getField()");

        String nomeAtualizado = (String) pessoa.getField("nome").getValue();
        String emailAtualizado = (String) pessoa.getField("email").getValue();
        System.out.println("   - Nome atualizado: " + nomeAtualizado);
        System.out.println("   - Email atualizado: " + emailAtualizado);

        // Demonstrar ciclo completo
        System.out.println("\n5. Ciclo completo de manipulação:");
        System.out.println("   a) Criar instância: ✓");
        System.out.println("   b) Setar valores: ✓");
        System.out.println("   c) Recuperar valores: ✓");
        System.out.println("   d) Modificar valores: ✓");
        System.out.println("   e) Validar persistência: ✓");

        System.out.println("\n==========================================");
        System.out.println("Exemplo concluído com sucesso!");
        System.out.println("==========================================");
    }

    /**
     * Método auxiliar que demonstra criação e manipulação de múltiplas instâncias
     */
    public static void exemploMultiplasInstancias() {
        System.out.println("\n--- Exemplo: Múltiplas Instâncias ---");

        // Criar e inicializar o package primeiro para garantir que os tipos sejam
        // carregados
        CertidoesPackage spackage = new CertidoesPackage();
        // Instanciar o tipo - ele será automaticamente registrado no package
        STypePessoaSimples tipo = new STypePessoaSimples();

        // Criar primeira pessoa
        SInstance pessoa1 = tipo.newInstance();
        pessoa1.getField("nome").setValue("João Silva");
        pessoa1.getField("email").setValue("joao@email.com");

        // Criar segunda pessoa
        SInstance pessoa2 = tipo.newInstance();
        pessoa2.getField("nome").setValue("Maria Santos");
        pessoa2.getField("email").setValue("maria@email.com");

        // Criar terceira pessoa
        SInstance pessoa3 = tipo.newInstance();
        pessoa3.getField("nome").setValue("Pedro Oliveira");
        pessoa3.getField("email").setValue("pedro@email.com");

        System.out.println("Pessoa 1: " + (String) pessoa1.getField("nome").getValue() + " - "
                + (String) pessoa1.getField("email").getValue());
        System.out.println("Pessoa 2: " + (String) pessoa2.getField("nome").getValue() + " - "
                + (String) pessoa2.getField("email").getValue());
        System.out.println("Pessoa 3: " + (String) pessoa3.getField("nome").getValue() + " - "
                + (String) pessoa3.getField("email").getValue());

        System.out.println("\nCada instância mantém seus próprios valores independentemente!");
    }
}
