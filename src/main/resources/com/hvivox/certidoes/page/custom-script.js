/**
 * EXEMPLO DE RECURSO JAVASCRIPT CUSTOMIZADO
 * Este arquivo demonstra um recurso JS carregado via JavaScriptResourceReference
 */

console.log('✅ custom-script.js carregado com sucesso!');

// Adicionar funcionalidade ao botão customizado
document.addEventListener('DOMContentLoaded', function() {
    const customButton = document.getElementById('customButton');
    
    if (customButton) {
        customButton.addEventListener('click', function() {
            // Exemplo de interação
            this.innerHTML = '<i class="fas fa-check"></i> Clicado!';
            this.classList.remove('btn-primary');
            this.classList.add('btn-success');
            
            // Resetar após 2 segundos
            setTimeout(() => {
                this.innerHTML = '<i class="fas fa-hand-pointer"></i> Clique Aqui!';
                this.classList.remove('btn-success');
                this.classList.add('btn-primary');
            }, 2000);
            
            console.log('🎯 Botão customizado clicado! Recurso JavaScript funcionando.');
        });
    }
});

