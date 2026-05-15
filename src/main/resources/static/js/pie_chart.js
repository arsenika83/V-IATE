
(function(){
    // ----- Исходные данные по умолчанию (5 секторов)
    const three = document.getElementById('three_grade').innerText;
    const four = document.getElementById('four_grade').innerText;
    const five = document.getElementById('five_grade').innerText;

    let data = [
        { name: "Удовл.", value: three, color: "#943636" },
        { name: "Хорошо", value: four, color: "#499BED" },
        { name: "Отлично", value: five, color: "#739842" },
    ];

    const canvas = document.getElementById('pieCanvas');
    const ctx = canvas.getContext('2d');
    let activeIndex = null;      // индекс подсвеченного сектора
    let animationFrame = null;    // не используется для анимации перерисовки, просто флаг

    // функции для отрисовки с учётом активного индекса (отодвигаем или легкая тень)
    // но для эффекта выдвижения используем сдвиг? сделаем просто увеличение яркости/обводку и легкий offset?
    // реализуем элегантный эффект: активный сектор получает белую обводку + легкое "выезжание" по радиусу на 6px
    // метод рисования: рисуем сектора с возможностью "explode" для активного индекса

    function getTotal() {
        return data.reduce((sum, item) => sum + Math.max(0, item.value), 0);
    }

    // обновить текст в центре
    function updateTotalDisplay() {
        const total = getTotal();
        document.getElementById('totalValue').innerText = total;
    }

    // перерисовка всей диаграммы с эффектом explode для активного сектора
    function drawPie() {
        if (!canvas || !ctx) return;
        const total = getTotal();
        if (total === 0) {
            // рисуем серый круг с сообщением
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.beginPath();
            ctx.arc(canvas.width/2, canvas.height/2, canvas.width/2, 0, Math.PI*2);
            ctx.fillStyle = "#e2e8f0";
            ctx.fill();
            ctx.fillStyle = "#4a627a";
            ctx.font = "bold 16px 'Segoe UI'";
            ctx.shadowBlur = 0;
            ctx.fillText("Нет данных", canvas.width/2-45, canvas.height/2+6);
            updateTotalDisplay();
            return;
        }

        const w = canvas.width;
        const h = canvas.height;
        const centerX = w/2;
        const centerY = h/2;
        const radius = w/2 - 2;   // отступ для обводки
        let startAngle = -Math.PI/2;  // начинаем с 12 часов (верх)

        ctx.clearRect(0, 0, w, h);

        // временный массив для хранения углов и данных (чтобы потом добавить обводку поверх)
        const sectors = [];
        let currentStart = startAngle;
        for (let i = 0; i < data.length; i++) {
            const val = Math.max(0, data[i].value);
            if (val === 0) continue;
            const angle = (val / total) * Math.PI * 2;
            const endAngle = currentStart + angle;
            sectors.push({
                index: i,
                start: currentStart,
                end: endAngle,
                value: val,
                name: data[i].name,
                color: data[i].color,
                explode: (activeIndex === i)
            });
            currentStart = endAngle;
        }

        // Рисуем все сектора (для explode меняем центр)
        for (let s of sectors) {
            const explodeOffset = s.explode ? 12 : 0;
            let offsetX = 0, offsetY = 0;
            if (explodeOffset > 0) {
                const midAngle = s.start + (s.end - s.start)/2;
                offsetX = Math.cos(midAngle) * explodeOffset;
                offsetY = Math.sin(midAngle) * explodeOffset;
            }
            const cx = centerX + offsetX;
            const cy = centerY + offsetY;
            ctx.beginPath();
            ctx.arc(cx, cy, radius, s.start, s.end);
            ctx.lineTo(cx, cy);
            ctx.fillStyle = s.color;
            ctx.fill();
            // добавим легкую тень и обводку для выделения
            ctx.save();
            ctx.shadowBlur = 0;
            ctx.strokeStyle = "#ffffff";
            ctx.lineWidth = 2.5;
            ctx.stroke();
            ctx.restore();
        }

        // добавим дополнительно контур и эффект для активного сектора - яркая обводка
        for (let s of sectors) {
            if (s.explode) {
                const explodeOffset = 12;
                const midAngle = s.start + (s.end - s.start)/2;
                const offsetX = Math.cos(midAngle) * explodeOffset;
                const offsetY = Math.sin(midAngle) * explodeOffset;
                const cx = centerX + offsetX;
                const cy = centerY + offsetY;
                ctx.beginPath();
                ctx.arc(cx, cy, radius, s.start, s.end);
                ctx.lineTo(cx, cy);
                ctx.lineWidth = 3;
                ctx.strokeStyle = "gold";
                ctx.shadowBlur = 6;
                ctx.shadowColor = "rgba(0,0,0,0.3)";
                ctx.stroke();
                ctx.shadowBlur = 0;
            }
        }

        // нарисовать центральное белое кольцо? не обязательно, потому что поверх есть div, но для красоты добавим белый круг с прозрачностью?
        // не трогаем, чтобы не перекрыть текст, но можно нарисовать маленькую точку (не нужно)
        // обновим текст суммы
        updateTotalDisplay();
        renderLegend();
    }

    // отрисовать легенду на панели
    function renderLegend() {
        const legendContainer = document.getElementById('legendList');
        if (!legendContainer) return;
        const total = getTotal();
        legendContainer.innerHTML = '';
        data.forEach((item, idx) => {
            const percent = total === 0 ? 0 : ((item.value / total) * 100).toFixed(1);
            const li = document.createElement('li');
            li.className = 'legend-item';
            li.setAttribute('data-index', idx);
            // подсветка фона если активный
            if (activeIndex === idx) {
                li.style.backgroundColor = "#ffe6d5";
                li.style.borderLeft = "3px solid #ff9940";
            } else {
                li.style.backgroundColor = "rgba(245, 248, 250, 0.7)";
                li.style.borderLeft = "none";
            }
            li.innerHTML = `
                    <div class="legend-color" style="background: ${item.color};"></div>
                    <div class="legend-label">${escapeHtml(item.name)}</div>
                    <div class="legend-value">${item.value}</div>
                    <div class="legend-percent">${percent}%</div>
                `;
            li.addEventListener('click', (e) => {
                e.stopPropagation();
                setActiveIndex(idx);
            });
            legendContainer.appendChild(li);
        });

        // если нет данных — показать сообщение
        if (data.length === 0) {
            const emptyMsg = document.createElement('div');
            emptyMsg.className = 'note';
            emptyMsg.style.textAlign = 'center';
            emptyMsg.style.padding = '20px';
            emptyMsg.innerText = 'Нет категорий. Добавьте через форму выше.';
            legendContainer.appendChild(emptyMsg);
        }
    }

    // небольшой helper для экранирования
    function escapeHtml(str) {
        return str.replace(/[&<>]/g, function(m) {
            if (m === '&') return '&amp;';
            if (m === '<') return '&lt;';
            if (m === '>') return '&gt;';
            return m;
        });
    }

    // установить активный сектор, перерисовать диаграмму и легенду
    function setActiveIndex(index) {
        if (index !== undefined && (index < 0 || index >= data.length)) {
            activeIndex = null;
        } else {
            activeIndex = (activeIndex === index) ? null : index;
        }
        drawPie();
        renderLegend();
    }

    // обновить все (после изменения данных)
    function refreshAll(resetActive = true) {
        if (resetActive) activeIndex = null;
        drawPie();
        renderLegend();
    }

    // обработчик клика по canvas для определения сектора
    function handleCanvasClick(e) {
        if (data.length === 0 || getTotal() === 0) return;
        const rect = canvas.getBoundingClientRect();
        const scaleX = canvas.width / rect.width;   // canvas intrinsic size 500x500
        const scaleY = canvas.height / rect.height;
        const mouseX = (e.clientX - rect.left) * scaleX;
        const mouseY = (e.clientY - rect.top) * scaleY;

        const w = canvas.width, h = canvas.height;
        const centerX = w/2, centerY = h/2;
        const dx = mouseX - centerX;
        const dy = mouseY - centerY;
        const distance = Math.hypot(dx, dy);
        const radius = w/2 - 2;
        if (distance > radius) return; // мимо круга

        let angle = Math.atan2(dy, dx);
        if (angle < -Math.PI/2) angle += Math.PI*2;
        // начало отсчета у нас -PI/2 (верх), приводим угол в систему от -PI/2
        let userAngle = angle;
        // преобразуем в диапазон от -PI/2 до 2pi - PI/2
        let startRef = -Math.PI/2;
        let total = getTotal();
        if (total === 0) return;
        let cumStart = startRef;
        for (let i = 0; i < data.length; i++) {
            const val = Math.max(0, data[i].value);
            if (val === 0) continue;
            const sliceAngle = (val / total) * Math.PI*2;
            const cumEnd = cumStart + sliceAngle;
            // нормализуем угол пользователя в диапазон [cumStart, cumEnd] с циклической коррекцией?
            let tempAngle = userAngle;
            if (cumEnd > Math.PI*2 + startRef) {
                // но наши углы до 2pi + offset? так как startRef = -pi/2, максимальный угол будет 3pi/2, а userAngle может быть от -pi до pi, преобразуем
                // проще: переведём userAngle в диапазон [startRef, startRef+2pi)
                let startBase = startRef;
                let endBase = startRef + Math.PI*2;
                let normAngle = userAngle;
                if (normAngle < startBase) normAngle += Math.PI*2;
                if (normAngle >= startBase && normAngle <= cumEnd) {
                    setActiveIndex(i);
                    return;
                }
                // дополнительно для случая, когда cumEnd > endBase (не бывает)
            } else {
                if (userAngle >= cumStart && userAngle < cumEnd) {
                    setActiveIndex(i);
                    return;
                }
            }
            cumStart = cumEnd;
        }
        // если не попали точно (погрешности), то ничего не делаем
    }

    // инициализация событий и установка размера canvas (фиксированный 500x500 retina)
    function initCanvas() {
        canvas.width = 500;
        canvas.height = 500;
        canvas.style.width = '100%';
        canvas.style.height = '100%';
        drawPie();
        canvas.addEventListener('click', handleCanvasClick);
    }

    // старт
    initCanvas();
    renderLegend();
    updateTotalDisplay();
    // следим за изменением окна, но canvas фикс, но перерисовка при ресайзе не нужна. однако при изменении масштаба шрифтов?
    window.addEventListener('resize', () => { drawPie(); }); // небольшая перерисовка, для безопасности
    renderLegend();
})();
