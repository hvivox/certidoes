/**
 * EXEMPLO DE RECURSO JAVASCRIPT CUSTOMIZADO
 * Carregado via JavaScriptResourceReference
 */

console.log('✅ custom-resource.js carregado com sucesso!');

// Adicionar interatividade quando o DOM estiver pronto
document.addEventListener('DOMContentLoaded', function() {
    console.log('🚀 Inicializando recursos customizados...');
    
    // Exemplo 1: Adicionar click handler no box customizado
    const customBox = document.querySelector('.custom-resource-box');
    if (customBox) {
        customBox.addEventListener('click', function() {
            this.style.transform = 'scale(0.98)';
            setTimeout(() => {
                this.style.transform = '';
            }, 200);
            
            alert('✨ Box customizado clicado!\n\nEste CSS/JS foi carregado via ResourceReference.');
        });
    }
    
    // Exemplo 2: Adicionar animação nos boxes de exemplo
    const exemploBoxes = document.querySelectorAll('.exemplo-box');
    exemploBoxes.forEach(function(box, index) {
        setTimeout(function() {
            box.style.opacity = '0';
            box.style.transition = 'opacity 0.5s ease-in';
            setTimeout(function() {
                box.style.opacity = '1';
            }, 50);
        }, index * 100);
    });
    
    console.log('✅ Recursos customizados inicializados!');
});

