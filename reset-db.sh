#!/bin/bash
echo "Останавливаем и удаляем контейнеры..."
docker-compose down -v

echo "Удаляем volumes..."
docker volume prune -f

echo "Удаляем ненужные образы..."
docker image prune -f

echo "Пересоздаём базу данных..."
docker-compose up -d --build

echo "Ждём запуска PostgreSQL..."
sleep 5

echo "Проверяем выполнение init-скрипта..."
docker-compose logs postgres | grep "running /docker-entrypoint-initdb.d"
