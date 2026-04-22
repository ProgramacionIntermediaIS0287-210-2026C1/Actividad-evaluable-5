# Historia de Usuario 13: Interrupción de Exámen

## 📖 Descripción General
Como alumno en entornos frágiles de telecomunicación, quiero poder ingresar un texto como `"PAUSAR"` que me permita abortar el proceso del test momentáneamente y salir del software, con la oportunidad de retomarlo desde las preguntas faltantes al volverme a autenticar.

## 🎯 Nivel de Dificultad
**Difícil** (Manejo de estado permanente e infraestructura).

## ✅ Criterios de Aceptación
1. Que el ingreso de la palabra reservada "PAUSAR" (o botón asociado si es GUI) termine de tajo la ejecución de la evaluación sin generar una nota total aún, guardando este estado "pausado" asociado a su intento en el `ExamAttemptRepository`.
2. Como se requiere retentiva para salir y volver del software, requerirás implementar persistencia a base de disco (serialización de estado a un '.json' o guardar avances temporales en un `.csv`).
3. Al volver a "ingresar su ID de estudiante", el sistema debe verificar en primer lugar si existe un intento pausado, si es así, salta las preguntas ya respondidas y reanuda en donde el sujeto quedó.

## 🛠️ Pistas de Implementación
* Analiza cómo está funcionado el ciclo principal en el `ConsoleUI`.
* Modifica la bandera de terminación prematura (break command line interface).
* Será necesario tocar profundamente el modelo de dominio agregando funciones como `ExamAttempt.setPaused(true)`.
