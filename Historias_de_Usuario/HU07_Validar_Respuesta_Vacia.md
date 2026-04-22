# Historia de Usuario 7: Prevención de Respuestas Vacías

## 📖 Descripción General
Como evaluador, quiero asegurar que ningún estudiante pueda enviar una respuesta completamente en blanco por error, para forzarlo a que al menos complete algo.

## 🎯 Nivel de Dificultad
**Medio** (Validaciones encapsuladas en Domain Driven Design).

## ✅ Criterios de Aceptación
1. Ir al Value Object `AnswerText`.
2. Agregar lógica en el constructor que verifique si el texto suministrado es nulo, está vacío (`""`) o solo contiene espacios en blanco.
3. Si ocurre esta situación, lanzar una `IllegalArgumentException` indicando "La respuesta no puede estar vacía".
4. Garantizar que la interfaz de usuario capture y muestre la excepción sin que el programa se deteriore (crash).

## 🛠️ Pistas de Implementación
* Modifica la clase `AnswerText.java` ubicada en `vo/ValueObjects.java` (o equivalente).
* En Java puedes usar métodos como `trim().isEmpty()` o `isBlank()` para detectar cadenas conformadas solo por espacios.
