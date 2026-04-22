# Historia de Usuario 12: Exportar Calificaciones 

## 📖 Descripción General
Como administrador o profesor, deseo que los intentos completados de los estudiantes y el desglose de su calificación no se pierdan, guardándose en un archivo `calificaciones.txt` permanente de historial en mi computador local.

## 🎯 Nivel de Dificultad
**Difícil** (Creación de un nuevo módulo de infraestructura para reportes).

## ✅ Criterios de Aceptación
1. Diseñar e implementar un nuevo servicio `ExportService` (o integrarlo a nivel de un contrato con I/O de archivos de infraestructura).
2. Cada vez que finaliza un examen mediante el método de Application Service `finalizarExamen(studentId)`, automáticamente el sistema deberá llamar a este módulo para escribir (AppendMode) el resultado en el documento plano.
3. El archivo debe mostrar: Fecha, `StudentId`, y Calificación o Puntaje, separados por texto amigable. (ej. "20/05/2026 | ID: 9942 | 5/5").

## 🛠️ Pistas de Implementación
* Crea la interfaz (contrato) en la capa de Domain `domain/repository` (o `domain/service`). 
* Escribe la implementación concreta en la capa de Infrastructure usando `FileWriter` o `Files.writeString(...)`.
* Inyecta esta nueva dependencia en la composición raíz de tu proyecto (`Main.java`).
