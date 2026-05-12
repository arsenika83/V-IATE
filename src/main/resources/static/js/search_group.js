class SearchManager {
    constructor() {
        this.searchInput = document.getElementById('searchInput');
        this.clearButton = document.getElementById('clearSearch');
        this.searchStats = document.getElementById('searchStats');
        this.resultCountSpan = document.getElementById('resultCount');
        this.searchQuerySpan = document.getElementById('searchQuery');
        this.group_buttons = document.querySelectorAll('.button-href');
        this.noResultsDiv = document.getElementById('noResults');
        this.currentSearchTerm = '';
        this.currentFilter = null;

        this.init();
    }

    init() {
        this.bindEvents();
        this.updateStats();
    }

    bindEvents() {
        // Поиск при вводе текста (с debounce)
        let debounceTimer;
        this.searchInput.addEventListener('input', (e) => {
            clearTimeout(debounceTimer);
            debounceTimer = setTimeout(() => {
                this.performSearch(e.target.value);
            }, 300);
        });

        // Очистка поиска
        this.clearButton.addEventListener('click', () => {
            this.clearSearch();
        });

        // Быстрые фильтры
        document.querySelectorAll('.search-tag').forEach(tag => {
            tag.addEventListener('click', () => {
                const filter = tag.getAttribute('data-filter');
                this.applyQuickFilter(filter);
            });
        });

        // Показывать/скрывать кнопку очистки
        this.searchInput.addEventListener('input', () => {
            this.clearButton.style.display = this.searchInput.value ? 'block' : 'none';
        });
    }

    performSearch(searchTerm) {
        this.currentSearchTerm = searchTerm.trim().toLowerCase();
        this.currentFilter = null;
        let visibleCount = 0;

        // Снимаем выделение с быстрых фильтров
        document.querySelectorAll('.search-tag').forEach(tag => {
            tag.classList.remove('active', 'bg-primary', 'text-white');
        });

        if (!this.currentSearchTerm) {
            this.showAllAssignments();
            this.updateStats(this.group_buttons.length);
            this.searchQuerySpan.innerHTML = '';
            return;
        }

        // Поиск по каждому заданию
        this.group_buttons.forEach(group_button => {
            const title = group_button.textContent || '';

            const searchableText = `${title}`.toLowerCase();
            const isMatch = searchableText.includes(this.currentSearchTerm);

            if (isMatch) {
                group_button.classList.remove('hidden');
                this.highlightText(group_button, this.currentSearchTerm);
                visibleCount++;
            } else {
                group_button.classList.add('hidden');
                this.removeHighlights(group_button);
            }
        });

        this.toggleNoResults(visibleCount === 0);
        this.updateStats(visibleCount);
        this.searchQuerySpan.innerHTML = `по запросу "${this.currentSearchTerm}"`;
    }

    applyQuickFilter(filterType) {
        // Сбрасываем текстовый поиск
        if (this.searchInput.value) {
            this.searchInput.value = '';
            this.clearButton.style.display = 'none';
        }
        this.currentSearchTerm = '';
        this.currentFilter = filterType;
        let visibleCount = 0;

        // Визуальное выделение активного фильтра
        document.querySelectorAll('.search-tag').forEach(tag => {
            if (tag.getAttribute('data-filter') === filterType) {
                tag.classList.add('active', 'bg-primary', 'text-white');
            } else {
                tag.classList.remove('active', 'bg-primary', 'text-white');
            }
        });

        this.group_buttons.forEach(group_button => {
            let isMatch = false;

            switch(filterType) {
                case 'course_1':
                    const course_1 = group_button.querySelector('.button-href') !== null;
                    isMatch = course_1;
                    break;
                default:
                    isMatch = true;
            }

            if (isMatch) {
                group_button.classList.remove('hidden');
                this.removeHighlights(group_button);
                visibleCount++;
            } else {
                group_button.classList.add('hidden');
            }
        });

        this.toggleNoResults(visibleCount === 0);
        this.updateStats(visibleCount);
        this.searchQuerySpan.innerHTML = filterType === 'course_1' ? '(1 курс)' : '';
    }

    showAllAssignments() {
        this.group_buttons.forEach(group_button => {
            group_button.classList.remove('hidden');
            this.removeHighlights(group_button);
        });
        this.toggleNoResults(false);
    }

    highlightText(element, searchTerm) {
        this.removeHighlights(element);

        const searchableElements = [
            element
        ];

        searchableElements.forEach(el => {
            if (el && el.textContent) {
                const regex = new RegExp(`(${this.escapeRegex(searchTerm)})`, 'gi');
                const originalText = el.textContent;
                const highlightedText = originalText.replace(regex, '<mark class="search-highlight">$1</mark>');

                if (highlightedText !== originalText) {
                    el.innerHTML = highlightedText;
                }
            }
        });
    }

    removeHighlights(element) {
        const highlights = element.querySelectorAll('.search-highlight');
        highlights.forEach(highlight => {
            const parent = highlight.parentNode;
            parent.innerHTML = parent.textContent;
        });
    }

    escapeRegex(string) {
        return string.replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
    }

    clearSearch() {
        this.searchInput.value = '';
        this.clearButton.style.display = 'none';
        this.currentSearchTerm = '';
        this.currentFilter = null;
        this.showAllAssignments();
        this.updateStats(this.group_buttons.length);
        this.searchQuerySpan.innerHTML = '';

        // Снимаем выделение с фильтров
        document.querySelectorAll('.search-tag').forEach(tag => {
            tag.classList.remove('active', 'bg-primary', 'text-white');
        });

        // Анимация
        this.searchInput.focus();
    }

    updateStats(count = null) {
        const visibleCount = count !== null ? count : this.group_buttons.length;
        const total = this.group_buttons.length;

        if (visibleCount === total) {
            this.resultCountSpan.innerHTML = `📊 Всего групп: ${total}`;
        } else {
            this.resultCountSpan.innerHTML = `🔍 Найдено: ${visibleCount}`;

            // Добавляем анимацию
            const badge = document.createElement('div');
            badge.className = 'result-count-badge position-fixed top-0 end-0 m-3 p-2 bg-success text-white rounded';
            badge.innerHTML = `Найдено ${visibleCount}`;
            document.body.appendChild(badge);
            setTimeout(() => badge.remove(), 2000);
        }
    }

    toggleNoResults(show) {
        this.noResultsDiv.style.display = show ? 'block' : 'none';
    }
}

// Инициализация при загрузке страницы
document.addEventListener('DOMContentLoaded', () => {
    const searchManager = new SearchManager();

    // Добавляем горячие клавиши Ctrl+F или / для фокуса на поиск
    document.addEventListener('keydown', (e) => {
        if ((e.ctrlKey && e.key === 'f') || e.key === '/') {
            e.preventDefault();
            document.getElementById('searchInput').focus();
        }
    });
});
