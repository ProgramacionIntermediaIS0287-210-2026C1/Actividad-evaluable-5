# Historia de Usuario 11: Nuevo Tipo de Pregunta (Ordenamiento)

## 📖 Descripción General
Como docente, quiero crear preguntas tipo "Ordene los siguientes elementos de mayor a menor", para poder evaluar capacidades secuenciales en mis alumnos y potenciar el banco de preguntas.

## 🎯 Nivel de Dificultad
**Difícil** (Agregar funcionalidad extendiendo el dominio; cumplimiento de principio OCP).

## ✅ Criterios de Aceptación
1. Crear una nueva clase `OrderingQuestion` que extienda la base `Question` en el dominio.
2. Esta pregunta debe tener una lógica particular en su método `esCorrecta(AnswerText)` donde valide secuencias (ej. que la respuesta recibida sea `3,1,4,2`).
3. Modificar la factoría de preguntas (`QuestionFactory`) para que sea capaz de interpretar una letra como "O" en el CSV y fabricar adecuadamente este tipo de pregunta.
4. Incluir datos de prueba en `banco_preguntas_test.csv` para probar su viabilidad.

## 🛠️ Pistas de Implementación
* Revisa las clases vecinas `TrueFalseQuestion` o `MultipleChoiceQuestion` para guiarte en el cascarón de código, notando la sobrecarga obligatoria de métodos de la clase abstracta padre.
* Modifica la clase de infraestructura encargada de instanciar las preguntas (donde está el factory lógico).
