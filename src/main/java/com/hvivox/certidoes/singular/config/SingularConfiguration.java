package com.hvivox.certidoes.singular.config;

/**
 * Módulo 1 - Tarefa 2: Configuração do OpenSingular
 * 
 * Classe responsável por configurar o OpenSingular no projeto.
 * 
 * NOTA: Na versão 1.8.1 do OpenSingular, a configuração pode ser feita
 * de forma mais simples através do registro automático de pacotes.
 * 
 * O OpenSingular detecta automaticamente os SPackages no classpath quando
 * são referenciados. A configuração explícita pode ser necessária apenas
 * para casos avançados (persistência, workflow, etc.).
 * 
 * @author OpenSingular Implementation
 * @version 1.0
 */
public class SingularConfiguration {

    /**
     * Inicializa a configuração básica do OpenSingular.
     * 
     * Esta configuração básica é suficiente para começar a usar
     * STypes e SInstances. Configurações avançadas (persistência,
     * workflow) serão adicionadas nos módulos correspondentes.
     */
    public static void initialize() {
        // Na versão 1.8.1, o OpenSingular funciona principalmente
        // através de descoberta automática de SPackages.
        // A configuração explícita será adicionada quando necessário
        // (ex: persistência, workflow, etc.)
    }
}
