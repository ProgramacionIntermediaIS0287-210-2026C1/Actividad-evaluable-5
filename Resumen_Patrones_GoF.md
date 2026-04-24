# Resumen de Patrones de Diseño GoF Implementados

Durante la revisión y análisis del código fuente alojado en el repositorio actual, se ha identificado la implementación de los siguientes patrones de diseño establecidos por la "Gang of Four" (GoF). Estos patrones se han utilizado de manera estratégica para mantener el código desacoplado, modular y con responsabilidades claras, siguiendo principios del Diseño Orientado al Dominio (DDD) y SOLID.

A continuación, se detalla explicativamente cada patrón encontrado, así como la sección de código específica donde se aplica su lógica.

---

## 1. Patrón Factory / Simple Factory
**Categoría:** Creacional

El patrón *Factory* es utilizado para centralizar y encapsular la lógica de creación de objetos en un solo lugar. En este repositorio, ha sido vital para el aislamiento entre la capa de presentación (UI) y la lógica de negocio (Dominio).

### 📍 Ubicación en el código:
**Archivo:** `src/com/exam/presentation/SwingUI.java`
**Clase interna:** `QuestionRendererFactory`

### 🔍 Análisis de la implementación:
En el desarrollo de la interfaz visual con Swing, es necesario renderizar diferentes componentes de UI dinámicamente según la naturaleza de la pregunta (si es Falso/Verdadero, Opción Múltiple, Llenar espacios, etc.). 

En lugar de que la vista principal llene su código de bloque de decisiones (`if/else` o `switch`) sobre los Tipos de Preguntas y que construya paneles mezclando la lógica, se declara una fábrica:
```java
static class QuestionRendererFactory {
    public static QuestionPanel crearPanelPregunta(Question q) {
        // ... Lógica de contenedor base compartida ...

        if (q instanceof QuestionTypes.TrueFalseQuestion) {
            return new TrueFalsePanel(container, optionsPanel);
        } else if (q instanceof QuestionTypes.FillBlankQuestion) {
            return new FillBlankPanel(container, optionsPanel);
        } else if (q instanceof QuestionTypes.SingleChoiceQuestion) {
            return new SingleChoicePanel(container, optionsPanel, extractOptionsFromDomain(q));
        } else if (q instanceof QuestionTypes.MultipleChoiceQuestion) {
            return new MultipleChoicePanel(container, optionsPanel, extractOptionsFromDomain(q));
        }
        return new FillBlankPanel(container, optionsPanel); // Fallback predeterminado
    }
}
```
**Conclusión:** Este patrón permite que el flujo visual sea agnóstico respecto a la forma en que el sub-componente visual fue construido.

---

## 2. Patrón Facade (Fachada)
**Categoría:** Estructural

El patrón *Facade* proporciona una interfaz unificada, simplificada y de alto nivel para acceder a un subsistema complejo lleno de múltiples servicios y responsabilidades aisladas.

### 📍 Ubicación en el código:
**Archivo:** `src/com/exam/application/ExamApplicationService.java`
**Clase:** `ExamApplicationService`

### 🔍 Análisis de la implementación:
La arquitectura del sistema incluye conceptos separados como: `QuestionBankRepository` (obtener preguntas), `AttemptManager` (verificar pre-condiciones para intentos de examen), `ExamAttemptRepository` (persistencia del historial) y `GradingService` (calificar el examen). 

Si la capa de Presentación interactuara con cada uno de ellos individualmente, se generaría un alto nivel de acoplamiento. La clase `ExamApplicationService` entra como una *fachada* que envuelve todos estos servicios y expone tres únicos métodos limpios de interacción:
1. `iniciarExamen(StudentId studentId)`
2. `responderPregunta(StudentId studentId, QuestionId qId, AnswerText answer)`
3. `finalizarExamen(StudentId studentId)`

```java
public CalificacionDTO finalizarExamen(StudentId studentId) {
    ExamAttempt attempt = attemptRepo.findActiveByStudent(studentId)
        .orElseThrow(() -> new IllegalStateException("No se encontró intento activo."));
    // Llamado al GradingService (Complejidad abstraída)
    Calificacion calificacion = gradingService.calificar(attempt);
    attempt.finalizar(calificacion);
    attemptRepo.save(attempt); // Abstracción de persistencia

    return new CalificacionDTO(calificacion.puntaje(), calificacion.total());
}
```
**Conclusión:** La capa superior se comunica únicamente a través de la fachada sin conocer las tripas profundas del sistema, lo que reduce las dependencias.

---

## 3. Patrón Strategy (Estrategia Polimórfica)
**Categoría:** Comportamiento

Este patrón permite agrupar una familia de algoritmos y hacerlos intercambiables para un cliente, permitiendo que el algoritmo varíe de manera independiente dependiendo del contexto, a menudo usando el polimorfismo.

### 📍 Ubicación en el código:
**Achivos:** `src/com/exam/domain/model/Question.java` y `src/com/exam/domain/model/QuestionTypes.java`
**Relación:** `Question` (Abstracta) implementada por estáticas dentro de `QuestionTypes`

### 🔍 Análisis de la implementación:
El sistema requiere comprobar las respuestas a distintos tipos de preguntas. El método `isCorrect(...)` varía algorítmicamente dependiendo si es un campo de arrastrar, verdadero y falso, o de rellenar texto (donde es preferible usar un parseador para no tomar en cuenta las minúsculas o los espacios). 

El modelo `Question` declara un contrato abstracto:
```java
public abstract boolean isCorrect(AnswerText studentAnswer);
```

Luego, las subclases dentro de `QuestionTypes.java` implementan la **estrategia particular** de verificación. Ejemplos de variaciones estratégicas:
- **`SingleChoiceQuestion.isCorrect()`**: Evalúa igualdad cruda después de quitar los espacios extremos en blanco: `trim().equalsIgnoreCase(studentAnswer.value().trim())`.
- **`MultipleChoiceQuestion.isCorrect()`**: Emplea una matriz de respuestas separada por comas, conviertiédolo a colecciones tipo `Set<String>` y usando un comparador lógico de equitabilidad de sets en crudo, haciendo que el orden de respuestas insertado por el estudiante no importe:
  ```java
  Set<String> correctSet = new HashSet<>(Arrays.asList(correctAnswer.value().split(",")));
  Set<String> studentSet = new HashSet<>(Arrays.asList(studentAnswer.value().split(",")));
  return correctSet.equals(studentSet);
  ```
- **`FillBlankQuestion.isCorrect()`**: Se encarga de hacer saneamiento robusto con RegEx (`replaceAll("\\s+", " ")`) de ambos lados para prevenir falsos errores por doble espacio introducido por el estudiante en los recuadros.

**Conclusión:** En lugar de ensuciar un único mega-método de evaluación con instrucciones `switch` que identifiquen los diversos algoritmos, el uso de herencia propicia un comportamiento tipo Strategy que asume y ejecuta la correcta implementación técnica per-pregunta durante la ejecución del `GradingService`.
