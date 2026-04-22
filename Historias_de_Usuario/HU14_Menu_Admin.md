# Historia de Usuario 14: Panel Administrativo Provisional

## 📖 Descripción General
Como coordinador tecnológico, quiero un menú especial paralelo al de los estudiantes que me permita poder listar TODOS los intentos abiertos, guardados y cerrados almacenados en la memoria activa, además del grado de asertividad que ha tenido todo el conglomerado de evaluados.

## 🎯 Nivel de Dificultad
**Difícil** (Nuevo módulo de Presentation, roles).

## ✅ Criterios de Aceptación
1. Al cargar la aplicación (en consola), debo tener 2 opciones: `(1) Soy Estudiante` -> Redirige al flow clásico de pruebas;  `(2) Soy Docente` -> Redirige al menú estricto de profesores.
2. Si selecciono docente, solicitará un pin (Hardcodeado es aceptable por fines de la prueba como "1234").
3. Si la verificación es contundente, mostrará una lista de estadísticas tomando uso del repositorio para recolector cuántos estudiantes presentaron, y cuál es la suma de los promedios numéricos agregados de todos ellos. 
4. Agrega un servicio de aplicación nuevo (`StatsApplicationService`).

## 🛠️ Pistas de Implementación
* Al abrir la app en `Main.java`, necesitarás un UI de arranque o router simple.
* Agrupa las estadísticas en la capa de Domain/Application antes de bajarlas al UI (No hacer cálculos de nota en `InstructorUI.java`).
