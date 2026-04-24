# Historia de Usuario 1: Personalización de Mensajes de Consola

## 📖 Descripción General
Como usuario del sistema, quiero ver un mensaje de bienvenida y despedida más amigable y personalizado en la consola, para sentir una interacción más cercana con la aplicación.

## 🎯 Nivel de Dificultad
**Sencilla** (Cambio de textos y salidas estándar).

## ✅ Criterios de Aceptación
1. Al iniciar la aplicación, el mensaje `"=== SISTEMA DE EVALUACIÓN (DDD & CLEAN ARCHITECTURE) ==="` debe ser reemplazado por un banner ASCII interactivo o un mensaje de bienvenida institucional de la Universidad.
2. Al finalizar el examen y mostrar la nota, imprimir un mensaje de despedida agradeciendo por usar la plataforma.
3. Todo el texto de bienvenida de la terminal debe imprimirse usando algún color en consola (códigos ANSI).

## 🛠️ Pistas de Implementación
* Revisa el archivo `ConsoleUI.java` dentro de la capa `presentation`.
* Busca los métodos asociados al inicio de consola en `start()`.
