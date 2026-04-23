document.addEventListener('DOMContentLoaded', function () {

    const btn   = document.getElementById('toggleDifficulty');
    const panel = document.getElementById('difficultyPanel');

    btn.addEventListener('click', () => {
        const isHidden = panel.classList.toggle('is-hidden');
        btn.querySelector('i').className = isHidden
            ? 'fa-solid fa-plus'
            : 'fa-solid fa-xmark';
    });

});