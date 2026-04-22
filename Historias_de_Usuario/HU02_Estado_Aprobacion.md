# Historia de Usuario 2: Estado de Aprobación

## 📖 Descripción General
Como estudiante, quiero saber de manera inmediata al finalizar la prueba si aprobé o reprobé el examen, para no tener que calcular el porcentaje mentalmente.

## 🎯 Nivel de Dificultad
**Sencilla** (Condicionales simples y salida de consola).

## ✅ Criterios de Aceptación
1. Luego de imprimir la puntuación final y el porcentaje de acierto, se debe imprimir `"Estado: APROBADO"` (idealmente en verde) si el porcentaje es **mayor o igual al 60%**.
2. Si el porcentaje es menor al 60%, se debe imprimir `"Estado: REPROBADO"` (idealmente en rojo).
3. Asegurarse de que este mensaje forme parte de la impresión de resultados en la consola.

## 🛠️ Pistas de Implementación
* Verifica dónde se finaliza el examen dentro de `ConsoleUI.java`.
* Puedes utilizar secuencias de escape ANSI (`\u001B[32m` para verde y `\u001B[31m` para rojo).
