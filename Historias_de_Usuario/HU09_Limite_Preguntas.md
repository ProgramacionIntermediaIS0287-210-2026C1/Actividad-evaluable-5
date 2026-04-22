# Historia de Usuario 9: Límite de Preguntas por Examen

## 📖 Descripción General
Como administrador técnico, quiero asegurar que un examen cargado en la memoria no consuma todos los recursos, para mantener la estabilidad limitando los bancos a un máximo de 50 preguntas.

## 🎯 Nivel de Dificultad
**Medio** (Lógica de dominio/aplicación).

## ✅ Criterios de Aceptación
1. Modificar el servicio encargado de cargar e iniciar el examen (`ExamApplicationService` o el repositorio).
2. Si al obtener todas las preguntas la cantidad devuelta supera 50, se debe arrojar una excepción indicando "El banco de preguntas excede el límite máximo permitido".
3. El sistema de consola debe capturar este mensaje y mostrarlo, abortando el inicio del examen.

## 🛠️ Pistas de Implementación
* Puedes verificar el tamaño en `ExamApplicationService.iniciarExamen()`. Si `repository.getAllQuestions().size() > 50`, lanzas la excepción.
* Considera `IllegalStateException` y captúrala apropiadamente en la UI.
