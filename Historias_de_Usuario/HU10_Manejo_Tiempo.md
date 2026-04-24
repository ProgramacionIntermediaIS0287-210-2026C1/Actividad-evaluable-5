# Historia de Usuario 10: Medición de Tiempo Consumido

## 📖 Descripción General
Como profesor, quiero que el sistema le diga al estudiante cuánto tiempo tardó (en minutos o segundos) completando su examen, para que pueda autogestionar su velocidad de lectura en futuras pruebas.

## 🎯 Nivel de Dificultad
**Medio** (Manejo de estado en capa de aplicación/presentación).

## ✅ Criterios de Aceptación
1. Registra el tiempo exacto en que comienza el examen en la capa de UI o en el ApplicationService.
2. Al llamar al método de finalización del examen, registra la hora actual de nueva cuenta.
3. Calcula la diferencia en segundos entre los dos instantes.
4. Imprime en la consola / interfaz gráfica el resultado con un texto como `Tiempo total del intento: 45 segundos`.

## 🛠️ Pistas de Implementación
* Usa `System.currentTimeMillis()` al llamar `iniciarExamen` y luego en `finalizarExamen`.
* Divide la diferencia que sacaste entre `1000` para conseguir total de segundos. Imprime esto junto a la variable ResultadoDTO en la terminal.
