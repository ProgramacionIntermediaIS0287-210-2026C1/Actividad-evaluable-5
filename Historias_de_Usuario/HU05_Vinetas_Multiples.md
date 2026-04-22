# Historia de Usuario 5: Viñetas en Preguntas de Opción Múltiple

## 📖 Descripción General
Como evaluado, quiero que las opciones de las preguntas de múltiple respuesta sean listadas de una manera visualmente diferente a las de única respuesta (por ejemplo con viñetas) para diferenciarlas más rápido en consola.

## 🎯 Nivel de Dificultad
**Sencilla** (Ajuste visual).

## ✅ Criterios de Aceptación
1. Ir a la implementación de preguntas de opción múltiple (Ej. `MultipleChoiceQuestion`).
2. Ajustar el método `displayFormat()` para que al imprimir las posibles opciones, en vez de letras o números consecutivos estándar, imprima un carácter como `[ ]` o astéricos `*` como pseudo-viñeta.
3. Asegurarse de que el comportamiento de la respuesta esperada no se vea afectado, solo la visualización en consola. 

## 🛠️ Pistas de Implementación
* Busca dentro del paquete de modelos (`domain.model`) si hay una clase que representa estas opciones.
* Modifica la implementación de `displayFormat()` o donde se sobreescriba en el subtipo correspondiente.
